package forest.rice.field.k.linenotify.api.danshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DanshiManager {

	private final static String URL = "http://www.tv-tokyo.co.jp/danshigohan/recipe/";

	public static void main(String[] args) {

	}

	public List<DanshiRecipe> get() {
		List<DanshiRecipe> recipes = new ArrayList<>();
		try {
			Document document = Jsoup.connect(URL).get();
			Element recipeIndex = document.getElementById("recipe_index");
			Elements e = recipeIndex.getElementsByClass("item");

			for (Element element : e) {
				recipes.add(DanshiRecipe.convert(element));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return recipes;
	}

}
