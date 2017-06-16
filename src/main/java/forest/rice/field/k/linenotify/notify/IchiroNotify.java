package forest.rice.field.k.linenotify.notify;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.meigen.IMeigenManager;
import forest.rice.field.k.linenotify.api.meigen.IchiroManager;

public class IchiroNotify extends AbstractMeigenNotify {

	@Override
	TYPE getType() {
		return TYPE.ICHIRO;
	}

	@Override
	IMeigenManager manager() {
		return new IchiroManager();
	}

}
