package forest.rice.field.k.linenotify.notify;

import java.util.List;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.line.LineNotify;
import forest.rice.field.k.linenotify.api.rola.RolaManager;
import forest.rice.field.k.linenotify.api.rola.RolaRecipe;

public class RolaNotify extends AbstractNotify {

	RolaManager manager;
	List<RolaRecipe> recipies;

	@Override
	TYPE getType() {
		return TYPE.ROLA;
	}

	@Override
	int sendMessage(String token) {

		RolaRecipe recipe = recipies.get(0);
		String message = String.format("\r\n%s\r\n%s", recipe.title, recipe.link);

		return LineNotify.sendMessage(token, message, resizedImgsrc(recipe.imgSrc));
	}

	@Override
	void init() {
		manager = new RolaManager();
		recipies = manager.get();

	}

}
