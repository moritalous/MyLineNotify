package forest.rice.field.k.linenotify;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class LambdaRequestInput2 extends HashMap<Object, Object> {

	private static final long serialVersionUID = 5395805938975302591L;

	public Map<String, String> getContext() {
		return HashMap.class.cast(this.get("context"));
	}

	public Object getBodyJson() {
		return this.get("body-json");
	}

	public Map<String, Object> getParams() {
		return HashMap.class.cast(this.get("params"));
	}

	public Map<String, String> getPath() {

		if (this.getParams().get("path") != null) {
			return HashMap.class.cast(this.getParams().get("path"));
		}
		return new HashMap();
	}

	public Map<String, String> getHeader() {
		if (this.getParams().get("path") != null) {
			return HashMap.class.cast(this.getParams().get("path"));
		}
		return new HashMap();
	}

	public Map<String, String> getQuerystring() {
		if (this.getParams().get("querystring") != null) {
			return HashMap.class.cast(this.getParams().get("querystring"));
		}
		return new HashMap();
	}

	public Map<String, Object> getStageVariables() {
		if (this.get("stage-variables") != null) {
			return HashMap.class.cast(this.get("stage-variables"));
		}
		return new HashMap();
	}

	@Override
	public String toString() {
		String str = super.toString();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}

}