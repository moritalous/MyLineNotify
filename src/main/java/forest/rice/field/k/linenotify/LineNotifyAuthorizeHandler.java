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

public class LineNotifyAuthorizeHandler {

	private static final String TABLE_NAME = System.getenv("TABLE_NAME");

	private static final String CLIENT_ID = System.getenv("CLIENT_ID");
	private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
	private static final String REDIRECT_URI = System.getenv("REDIRECT_URI");

	public Object handleRequest(Map<Object, Object> input, Context context) throws Exception {

		String requestId = context.getAwsRequestId();

		{
			AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
			dynamoDB.withRegion(Regions.AP_NORTHEAST_1);

			Map<String, AttributeValue> item = new HashMap<>();

			item.put("access_token", new AttributeValue("NEW"));
			item.put("state", new AttributeValue(requestId));

			PutItemRequest putItemRequest = new PutItemRequest(TABLE_NAME, item);

			dynamoDB.putItem(putItemRequest);
		}

		String authorizeUri = String.join("?", "https://notify-bot.line.me/oauth/authorize", query(requestId));
		throw new Exception(new ResponseFound(authorizeUri));
	}

	private String query(String state) {
		Map<String, String> map = new HashMap<>();

		map.put("response_type", "code");
		map.put("client_id", CLIENT_ID);
		try {
			map.put("redirect_uri", URLEncoder.encode(REDIRECT_URI, "UTF-8"));
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

	public class ResponseFound extends Throwable {
		public ResponseFound(String uri) {
			super(uri);
		}
	}

}
