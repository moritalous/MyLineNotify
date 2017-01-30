package forest.rice.field.k.linenotify;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import forest.rice.field.k.linenotify.db.IdTable;
import forest.rice.field.k.linenotify.db.IdTableModel;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class LineNotifyAuthorizeCallbackHandler {

	private static final String TABLE_NAME = System.getenv("TABLE_NAME");

	private static final String REDIRECT_URI = System.getenv("REDIRECT_URI");
	private static final String SUCCESS_REDIRECT_URI = System.getenv("SUCCESS_REDIRECT_URI");

	enum TYPE {
		MOCO("moco"), DANSHI("danshi"), NONE("");

		private final String type;

		private TYPE(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public static TYPE getType(String str) {

			TYPE.MOCO.getType();

			if (str == null) {
				return TYPE.NONE;
			}

			switch (str) {
			case "moco":
				return MOCO;
			case "danshi":
				return DANSHI;
			}

			return TYPE.NONE;
		}

	}

	public Object handleRequest(LambdaRequestInput2 input2, Context context) throws Exception {

		TYPE type = TYPE.getType(this.getType(input2));
		System.out.println("type = " + type.getType());

		String body = String.class.cast(input2.getBodyJson());
		System.out.println(String.format("body=%s", body));

		Map<String, String> query = queryStriing(body);
		String code = query.get("code");
		String state = query.get("state");
		System.out.println(String.format("code=%s, state=%s", code, state));

		AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
		dynamoDB.withRegion(Regions.AP_NORTHEAST_1);

		// Query
		Map<String, AttributeValue> queryItem = queryItem(dynamoDB, state);
		// PutItem
		PutItem(dynamoDB, state, code, type);

		// CLIENT_ID
		String clientId = getClientId(dynamoDB, type.getType());
		// CLIENT_SECRET
		String clientSecret = getClientSecret(dynamoDB, type.getType());

		// Get ACCESS_TOKEN
		TokenResponse token = getToken(clientId, clientSecret, code, type);

		System.out.println("status " + token.status);
		System.out.println("message " + token.message);
		System.out.println("access_token " + token.accessToken);

		// PutItem
		PutItem(dynamoDB, state, code, token.accessToken, type);

		throw new Exception(new ResponseFound(SUCCESS_REDIRECT_URI));
	}

	private Map<String, AttributeValue> queryItem(AmazonDynamoDBClient dynamoDB, String state) {

		Map<String, Condition> keyConditions = new HashMap<>();
		keyConditions.put("access_token", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
				.withAttributeValueList(new AttributeValue().withS("NEW")));
		keyConditions.put("state", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
				.withAttributeValueList(new AttributeValue().withS(state)));

		QueryRequest request = new QueryRequest().withTableName(TABLE_NAME).withKeyConditions(keyConditions);

		QueryResult result = dynamoDB.query(request);

		if (result.getCount() == 1) {
			return result.getItems().get(0);
		} else {
			return new HashMap<>();
		}
	}

	private void PutItem(AmazonDynamoDBClient dynamoDB, String state, String code, TYPE type) {
		PutItem(dynamoDB, state, code, "NEW2", type);
	}

	private void PutItem(AmazonDynamoDBClient dynamoDB, String state, String code, String token, TYPE type) {
		Map<String, AttributeValue> putItem = new HashMap<>();

		putItem.put("access_token", new AttributeValue(token));
		putItem.put("state", new AttributeValue(state));
		putItem.put("code", new AttributeValue(code));
		putItem.put("type", new AttributeValue(type.getType()));

		System.out.println(putItem.toString());

		PutItemRequest putItemRequest = new PutItemRequest(TABLE_NAME, putItem);

		PutItemResult result = dynamoDB.putItem(putItemRequest);

		return;
	}

	public TokenResponse getToken(String clientId, String clientSecret, String code, TYPE type) {
		try {
			OkHttpClient client = new OkHttpClient();
			String redirectUri = String.join("/", REDIRECT_URI, type.getType());
			RequestBody body = new FormBody.Builder().add("grant_type", "authorization_code").add("code", code)
					.add("redirect_uri", redirectUri).add("client_id", clientId).add("client_secret", clientSecret)
					.build();

			okhttp3.Request request = new okhttp3.Request.Builder().url("https://notify-bot.line.me/oauth/token")
					.post(body).build();
			okhttp3.Response response = client.newCall(request).execute();

			String bodyString = response.body().string();

			System.out.println(bodyString);

			return parseResponseJson(bodyString);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected TokenResponse parseResponseJson(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper.readValue(json, TokenResponse.class);
	}

	private String getType(LambdaRequestInput2 input) {
		String str = "";
		try {
			str = input.getPath().get("type");
		} catch (Exception e) {
			e.printStackTrace();
			str = "";
		}
		return str;
	}

	private String getClientId(AmazonDynamoDBClient dynamoDB, String id) {
		IdTable idTable = new IdTable();
		IdTableModel record = idTable.getRecord(dynamoDB, "LINE_NOTIFY_CLIENT_ID", id);
		return record.value;
	}

	private String getClientSecret(AmazonDynamoDBClient dynamoDB, String id) {
		IdTable idTable = new IdTable();
		IdTableModel record = idTable.getRecord(dynamoDB, "LINE_NOTIFY_CLIENT_SECRET", id);
		return record.value;
	}

	private Map<String, String> queryStriing(String bodyJson) {
		Map<String, String> result = new HashMap<>();
		String[] strs = bodyJson.split("&");
		for (String str : strs) {
			String[] entry = str.split("=");
			if (entry.length > 1) {
				result.put(entry[0], entry[1]);
			}
		}
		return result;
	}

	public class ResponseFound extends Throwable {
		public ResponseFound(String uri) {
			super(uri);
		}
	}
}
