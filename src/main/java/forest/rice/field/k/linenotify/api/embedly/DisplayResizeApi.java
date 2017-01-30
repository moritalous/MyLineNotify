package forest.rice.field.k.linenotify.api.embedly;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.util.StringUtils;

public class DisplayResizeApi {

	private static final String KEY = System.getenv("EMBEDLY_KEY");
	private static final String BASE_URL = "https://i.embed.ly/1/display/resize";

	public static String resize(String url, int width, int height) {
		return resize(url, "", width, height, false, true, false, -1);
	}

	public static String resize(String url, String errorurl, int width, int height, boolean grow, boolean animate,
			boolean compresspng, int quality) {

		if (StringUtils.isNullOrEmpty(url)) {
			throw new IllegalArgumentException("url is required");
		}
		Map<String, String> params = new HashMap<>();
		try {
			params.put("url", URLEncoder.encode(url, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!StringUtils.isNullOrEmpty(url)) {
			params.put("errorurl", errorurl);
		}
		params.put("width", Integer.toString(width));
		params.put("height", Integer.toString(height));
		params.put("grow", (grow ? "true" : "false"));
		params.put("animate", (animate ? "true" : "false"));
		params.put("compresspng", (compresspng ? "true" : "false"));
		if (quality != -1) {
			params.put("quality", Integer.toString(quality));
		}
		params.put("key", KEY);

		List<String> paramList = params.entrySet().stream().map(f -> String.join("=", f.getKey(), f.getValue()))
				.collect(Collectors.toList());

		return String.join("?", BASE_URL, String.join("&", paramList));

	}

}
