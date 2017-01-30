package forest.rice.field.k.linenotify;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaRequestInput {

	public Map<String, String> headers;
	public Map<String, String> queryParameters;

	public String sourceIp;
	public String userAgent;

	public String requestPath;

	@Override
	public String toString() {

		String str = super.toString();

		ObjectMapper mapper = new ObjectMapper();

		try {
			str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return str;
	}
}
