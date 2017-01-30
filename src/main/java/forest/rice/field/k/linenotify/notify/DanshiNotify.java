package forest.rice.field.k.linenotify.notify;

import java.util.List;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.danshi.DanshiManager;
import forest.rice.field.k.linenotify.api.danshi.DanshiRecipe;
import forest.rice.field.k.linenotify.api.line.LineNotify;

public class DanshiNotify extends AbstractNotify {

	DanshiManager manager;
	List<DanshiRecipe> recipies;

	@Override
	TYPE getType() {
		return TYPE.DANSHI;
	}

	@Override
	int sendMessage(String token) {

		DanshiRecipe recipe = recipies.get(0);
		String message = String.format("\r\n%s\r\n%s", recipe.title, recipe.link);

		return LineNotify.sendMessage(token, message, resizedImgsrc(recipe.imgSrc));
	}

	@Override
	void init() {
		manager = new DanshiManager();
		recipies = manager.get();
	}

}
