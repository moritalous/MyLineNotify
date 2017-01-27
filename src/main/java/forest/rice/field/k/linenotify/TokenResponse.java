package forest.rice.field.k.linenotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
	@JsonProperty("status")
	public int status;

	@JsonProperty("message")
	public String message;

	@JsonProperty("access_token")
	public String accessToken;
}