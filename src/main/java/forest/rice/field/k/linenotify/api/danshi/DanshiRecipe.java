package forest.rice.field.k.linenotify.api.danshi;

import org.jsoup.nodes.Element;

public class DanshiRecipe {

	public String link;
	public String imgSrc;
	public String title;
	public String date;

	static DanshiRecipe convert(Element element) {
		DanshiRecipe recipe = new DanshiRecipe();

		try {
			recipe.link = element.getElementsByTag("a").get(0).attr("href");
			recipe.imgSrc = element.getElementsByTag("img").get(0).attr("src");
			recipe.title = element.getElementsByTag("h4").get(0).text();
			recipe.date = element.getElementsByClass("date").get(0).text();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recipe;
	}

}
