package forest.rice.field.k.linenotify.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;

public class IdTable {

	private static final String TABLE_NAME = "ID_TABLE";

	public IdTableModel getRecord(AmazonDynamoDBClient dynamoDB, String type, String id) {
		List<IdTableModel> records = this.getRecords(dynamoDB, type, id);
		if (records.size() == 0) {
			return null;
		}
		return records.get(0);
	}

	public List<IdTableModel> getRecords(AmazonDynamoDBClient dynamoDB, String type, String id) {
		Map<String, Condition> keyConditions = new HashMap<>();
		keyConditions.put("TYPE", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
				.withAttributeValueList(new AttributeValue().withS(type)));
		keyConditions.put("ID", new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
				.withAttributeValueList(new AttributeValue().withS(id)));

		QueryRequest request = new QueryRequest().withTableName(TABLE_NAME).withKeyConditions(keyConditions);

		QueryResult result = dynamoDB.query(request);

		return convert(result.getItems());
	}

	private List<IdTableModel> convert(List<Map<String, AttributeValue>> items) {
		List<IdTableModel> list = new ArrayList<>();
		items.stream().forEach(a -> {
			String type = a.get("TYPE").getS();
			String id = a.get("ID").getS();
			String value = a.get("VALUE").getS();

			list.add(new IdTableModel(type, id, value));
		});
		return list;
	}
}
