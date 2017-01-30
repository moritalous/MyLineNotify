package forest.rice.field.k.linenotify.notify;

import java.util.List;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.line.LineNotify;
import forest.rice.field.k.linenotify.api.mocos.MocosManager;
import forest.rice.field.k.linenotify.api.mocos.MocosRecipe;

public class MocosNotify extends AbstractNotify {

	List<MocosRecipe> recipes;

	@Override
	void init() {
		MocosManager manager = new MocosManager();
		recipes = manager.getRecipeList();
	}

	@Override
	TYPE getType() {
		return TYPE.MOCO;
	}

	@Override
	int sendMessage(String token) {
		MocosRecipe recipe = recipes.get(0);
		String message = String.format("\r\n%s\r\n%s", recipe.getName(), recipe.getUrl());

		return LineNotify.sendMessage(token, message, resizedImgsrc(recipe.getImgSrc()));
	}
}
