package forest.rice.field.k.linenotify;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;

import forest.rice.field.k.linenotify.db.IdTable;
import forest.rice.field.k.linenotify.db.IdTableModel;

public class LineNotifyAuthorizeHandler {

	private static final String TABLE_NAME = System.getenv("TABLE_NAME");

	private static final String REDIRECT_URI = System.getenv("REDIRECT_URI");
	private static final String FAILED_REDIRECT_URI = System.getenv("FAILED_REDIRECT_URI");

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

	public Object handleRequest(LambdaRequestInput2 input, Context context) throws Exception {

		TYPE type = TYPE.getType(this.getType(input));
		System.out.println("type = " + type.getType());

		String requestId = context.getAwsRequestId();

		AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
		dynamoDB.withRegion(Regions.AP_NORTHEAST_1);

		{
			Map<String, AttributeValue> item = new HashMap<>();

			item.put("access_token", new AttributeValue("NEW"));
			item.put("state", new AttributeValue(requestId));
			if (type != TYPE.NONE) {
				item.put("type", new AttributeValue(type.getType()));
			}

			PutItemRequest putItemRequest = new PutItemRequest(TABLE_NAME, item);

			dynamoDB.putItem(putItemRequest);
		}

		String redirectUri;
		if (type == TYPE.NONE) {
			redirectUri = FAILED_REDIRECT_URI;
		} else {
			String clientId = this.getClientId(dynamoDB, type.getType());
			redirectUri = String.join("?", "https://notify-bot.line.me/oauth/authorize",
					query(requestId, type, clientId));
		}

		System.out.println(redirectUri);
		throw new Exception(new ResponseFound(redirectUri));
	}

	private String getClientId(AmazonDynamoDBClient dynamoDB, String id) {
		IdTable idTable = new IdTable();
		IdTableModel record = idTable.getRecord(dynamoDB, "LINE_NOTIFY_CLIENT_ID", id);
		return record.value;
	}

	private String query(String state, TYPE type, String clientId) {
		Map<String, String> map = new HashMap<>();

		map.put("response_type", "code");
		map.put("client_id", clientId);
		try {
			if (type.equals(TYPE.NONE)) {
				map.put("redirect_uri", URLEncoder.encode(REDIRECT_URI, "UTF-8"));
			} else {
				map.put("redirect_uri", URLEncoder.encode(String.join("/", REDIRECT_URI, type.getType()), "UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("scope", "notify");
		map.put("state", state);
		map.put("response_mode", "form_post");

		List<String> q = map.entrySet().stream().map(m -> String.join("=", m.getKey(), m.getValue()))
				.collect(Collectors.toList());

		return String.join("&", q);

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

	public class ResponseFound extends Throwable {
		public ResponseFound(String uri) {
			super(uri);
		}
	}

}
