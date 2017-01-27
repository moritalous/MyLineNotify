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

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class LineNotifyAuthorizeCallbackHandler {

	private static final String TABLE_NAME = System.getenv("TABLE_NAME");

	private static final String CLIENT_ID = System.getenv("CLIENT_ID");
	private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
	private static final String REDIRECT_URI = System.getenv("REDIRECT_URI");
	private static final String SUCCESS_REDIRECT_URI = System.getenv("SUCCESS_REDIRECT_URI");

	public Object handleRequest(CallbackRequestInput input, Context context) throws Exception {

		String code = input.queryParameters.get("code");
		String state = input.queryParameters.get("state");

		AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
		dynamoDB.withRegion(Regions.AP_NORTHEAST_1);

		// Query
		Map<String, AttributeValue> queryItem = queryItem(dynamoDB, state);
		// PutItem
		PutItem(dynamoDB, state, code);

		// Get ACCESS_TOKEN
		TokenResponse token = getToken(code);

		System.out.println("status " + token.status);
		System.out.println("message " + token.message);
		System.out.println("access_token " + token.accessToken);

		// PutItem
		PutItem(dynamoDB, state, code, token.accessToken);

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

	private void PutItem(AmazonDynamoDBClient dynamoDB, String state, String code) {
		PutItem(dynamoDB, state, code, "NEW2");
	}

	private void PutItem(AmazonDynamoDBClient dynamoDB, String state, String code, String token) {
		Map<String, AttributeValue> putItem = new HashMap<>();

		putItem.put("access_token", new AttributeValue(token));
		putItem.put("state", new AttributeValue(state));
		putItem.put("code", new AttributeValue(code));

		System.out.println(putItem.toString());

		PutItemRequest putItemRequest = new PutItemRequest(TABLE_NAME, putItem);

		PutItemResult result = dynamoDB.putItem(putItemRequest);

		return;
	}

	public TokenResponse getToken(String code) {
		try {
			OkHttpClient client = new OkHttpClient();
			RequestBody body = new FormBody.Builder().add("grant_type", "authorization_code").add("code", code)
					.add("redirect_uri", REDIRECT_URI).add("client_id", CLIENT_ID).add("client_secret", CLIENT_SECRET)
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

	public class ResponseFound extends Throwable {
		public ResponseFound(String uri) {
			super(uri);
		}
	}
}
