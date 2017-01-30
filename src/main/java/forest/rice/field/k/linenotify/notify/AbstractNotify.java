package forest.rice.field.k.linenotify.notify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.embedly.DisplayResizeApi;

public abstract class AbstractNotify {

	static final String TABLE_NAME = System.getenv("TABLE_NAME");

	abstract TYPE getType();

	abstract int sendMessage(String token);

	abstract void init();

	public Object handleRequest(Map<Object, Object> input, Context context) throws Exception {
		AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
		dynamoDB.withRegion(Regions.AP_NORTHEAST_1);

		init();

		List<Map<String, AttributeValue>> items = scanItem(dynamoDB);

		items.stream().filter(p -> p.containsKey("access_token") && p.get("access_token") != null
				&& !"".equals(p.get("access_token").getS())).forEach(c -> {
					String token = c.get("access_token").getS();

					int responseCode = sendMessage(token);

					if (responseCode == 401) {
						deleteItem(dynamoDB, token, c.get("state").getS());
					}
				});

		return null;
	}

	private List<Map<String, AttributeValue>> scanItem(AmazonDynamoDBClient dynamoDB) {

		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		expressionAttributeValues.put(":access_token", new AttributeValue().withS("NEW"));
		expressionAttributeValues.put(":typeVal", new AttributeValue().withS(getType().getType()));

		Map<String, String> expressionAttributeNames = new HashMap<>();
		expressionAttributeNames.put("#t", "type");

		ScanRequest scanRequest = new ScanRequest().withTableName(TABLE_NAME)
				.withFilterExpression("#t = :typeVal AND NOT contains (access_token, :access_token)")
				.withExpressionAttributeNames(expressionAttributeNames)
				.withExpressionAttributeValues(expressionAttributeValues);

		ScanResult result = dynamoDB.scan(scanRequest);

		return result.getItems();

	}

	private void deleteItem(AmazonDynamoDBClient dynamoDB, String access_token, String state) {
		System.out.println("Delete access_token :" + access_token);

		DynamoDB db = new DynamoDB(dynamoDB);
		Table table = db.getTable(TABLE_NAME);

		table.deleteItem("access_token", access_token, "state", state);
	}

	String resizedImgsrc(String url) {
		return DisplayResizeApi.resize(url, 240, 240);
	}

}
