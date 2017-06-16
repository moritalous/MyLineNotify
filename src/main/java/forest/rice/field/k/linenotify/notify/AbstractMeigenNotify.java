package forest.rice.field.k.linenotify.notify;

import java.util.Calendar;

import forest.rice.field.k.linenotify.api.line.LineNotify;
import forest.rice.field.k.linenotify.api.line.LineNotify.Sticker;
import forest.rice.field.k.linenotify.api.meigen.IMeigenManager;

public abstract class AbstractMeigenNotify extends AbstractNotify {

	private IMeigenManager manager;

	@Override
	void init() {
		manager = manager();
	}

	@Override
	int sendMessage(String token) {
		String message = manager.getMessage(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		return LineNotify.sendMessage(token, message, Sticker.POSITIVE);
	}

	abstract IMeigenManager manager();
}
