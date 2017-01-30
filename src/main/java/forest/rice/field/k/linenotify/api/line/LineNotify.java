package forest.rice.field.k.linenotify.api.line;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LineNotify {

	private static final String LINE_NOTIFY_URL = "https://notify-api.line.me/api/notify";

	public static void main(String[] args) {
		String token = "4l2R7vC4cKpJ4Vz3llAAkdhxKQL3LMhxbAWnarzQV7m";
		LineNotify.sendMessage(token, "Hello, World!", Sticker.POSITIVE);
		LineNotify.sendMessage(token, "Hello, World! 2");
	}

	public static int sendMessage(String token, String message, String imageSrc) {
		try {
			OkHttpClient client = new OkHttpClient();
			okhttp3.Response response = client
					.newCall(createRequest(LINE_NOTIFY_URL, token, createRequestBody(message, imageSrc))).execute();

			String str = response.body().string();
			System.out.println(str);
			LineNotifyResponse notifyResponse = convertJson2Pojo(str);
			System.out.println(notifyResponse.toString());

			return notifyResponse.getStatus();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return 999;
	}

	public static int sendMessage(String token, String message, Sticker sticker) {
		try {
			OkHttpClient client = new OkHttpClient();
			okhttp3.Response response = client
					.newCall(createRequest(LINE_NOTIFY_URL, token, createRequestBody(message, sticker))).execute();

			String str = response.body().string();
			System.out.println(str);
			LineNotifyResponse notifyResponse = convertJson2Pojo(str);
			System.out.println(notifyResponse.toString());

			return notifyResponse.getStatus();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return 999;

	}

	public static int sendMessage(String token, String message) {
		return sendMessage(token, message, Sticker.NONE);
	}

	private static RequestBody createRequestBody(String message, String image) {

		return new FormBody.Builder().add("message", message).add("imageThumbnail", image).add("imageFullsize", image)
				.build();
	}

	private static RequestBody createRequestBody(String message, Sticker sticker) {

		if (sticker.equals(Sticker.NONE)) {
			return new FormBody.Builder().add("message", message).build();
		} else {
			return new FormBody.Builder().add("message", message)
					.add("stickerPackageId", Integer.toString(sticker.stickerPackageId))
					.add("stickerId", Integer.toString(sticker.stickerId)).build();
		}

	}

	private static Request createRequest(String url, String token, RequestBody postBody) {
		return new okhttp3.Request.Builder().url(url).header("Authorization", String.format("Bearer %s", token))
				.post(postBody).build();
	}

	private static LineNotifyResponse convertJson2Pojo(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, LineNotifyResponse.class);
	}

	public enum Sticker {
		POSITIVE(1, 106), NEGATIVE(1, 111), NONE(999, 999);

		final int stickerPackageId, stickerId;

		private Sticker(final int stickerPackageId, final int stickerId) {
			this.stickerPackageId = stickerPackageId;
			this.stickerId = stickerId;
		}

	}
}
