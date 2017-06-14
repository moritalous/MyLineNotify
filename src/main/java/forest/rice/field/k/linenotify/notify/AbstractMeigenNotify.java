package forest.rice.field.k.linenotify.notify;

import java.util.Calendar;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.line.LineNotify;
import forest.rice.field.k.linenotify.api.line.LineNotify.Sticker;
import forest.rice.field.k.linenotify.api.meigen.shuzo.ShuzoManager;

public class AbstractMeigenNotify extends AbstractNotify {

	private ShuzoManager manager;

	@Override
	void init() {
		manager = new ShuzoManager();
	}

	@Override
	TYPE getType() {
		return TYPE.SHUZO;
	}

	@Override
	int sendMessage(String token) {
		String message = manager.getMessage(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		return LineNotify.sendMessage(token, message, Sticker.POSITIVE);
	}
}
