package forest.rice.field.k.linenotify.api.rola;

import org.jsoup.nodes.Element;

public class RolaRecipe {

	public String link;
	public String imgSrc;
	public String title;

	static RolaRecipe convert(Element element) {
		RolaRecipe recipe = new RolaRecipe();

		try {

			Element bg = element.getElementsByClass("recipe_bg").get(0).getElementsByTag("img").get(0);

			String baseUrl = bg.baseUri();

			String title = bg.attributes().get("alt");
			String imgSrc = bg.attributes().get("src");

			String link = element.getElementsByTag("a").get(0).attributes().get("href");

			recipe.link = String.format("%s%s", baseUrl, link);
			recipe.imgSrc = String.format("%s%s", baseUrl, imgSrc);
			recipe.title = title;

			System.out.println(recipe);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return recipe;
	}

}
