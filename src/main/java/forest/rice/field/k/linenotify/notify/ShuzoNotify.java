package forest.rice.field.k.linenotify.notify;

import forest.rice.field.k.linenotify.api.meigen.IMeigenManager;
import forest.rice.field.k.linenotify.api.meigen.ShuzoManager;

public class ShuzoNotify extends AbstractMeigenNotify {

	@Override
	IMeigenManager manager() {
		return new ShuzoManager();
	}
}
