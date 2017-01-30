package forest.rice.field.k.linenotify.api.mocos;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import okhttp3.OkHttpClient;

public class MocosManager {

	public static void main(String[] args) {
		List<MocosRecipe> list = new MocosManager().getRecipeList();

		System.out.println(list.size());
	}

	public List<MocosRecipe> getRecipeList() {
		return parseHtml(getHtml());
	}

	protected String getHtml() {
		String result = "";

		try {
			OkHttpClient client = new OkHttpClient();
			okhttp3.Request request = new okhttp3.Request.Builder().url(getRequestUrl()).build();
			okhttp3.Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	protected String getRequestUrl() {
		return "http://www.ntv.co.jp/zip/mokomichi/";
	}

	protected List<MocosRecipe> parseHtml(String html) {
		Document document = Jsoup.parse(html);
		Elements entry = document.getElementsByClass("entry");

		return entry.stream().map(e -> {
			Elements a = e.getElementsByTag("a");
			String href = a.first().attributes().get("href").toString();

			Elements img = e.getElementsByTag("img");
			String src = img.first().attributes().get("src").toString();

			Elements time = e.getElementsByTag("time");
			String timeValue = time.first().text();

			Elements h3 = e.getElementsByTag("h3");
			String name = h3.first().text();

			String url = String.join("", getRequestUrl(), href);
			String imgSrc = String.join("", getRequestUrl(), src);

			return new MocosRecipe(url, name, imgSrc, timeValue);
		}).filter(r -> r.getName() != null && r.getUrl() != null).collect(Collectors.toList());
	}

}
