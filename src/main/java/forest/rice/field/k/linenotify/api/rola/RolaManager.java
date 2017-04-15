package forest.rice.field.k.linenotify.api.rola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RolaManager {

	private final static String URL = "http://www.fujitv.co.jp/meza/rola/";

	public static void main(String[] args) {
		new RolaManager().get();
	}

	public List<RolaRecipe> get() {
		List<RolaRecipe> recipes = new ArrayList<>();
		try {
			Document document = Jsoup.connect(URL).get();
			Element element = document.getElementById("fp-l");
			recipes.add(RolaRecipe.convert(element));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return recipes;
	}

}
