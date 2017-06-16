package forest.rice.field.k.linenotify.notify;

import forest.rice.field.k.linenotify.api.meigen.IMeigenManager;
import forest.rice.field.k.linenotify.api.meigen.IchiroManager;

public class IchiroNotify extends AbstractMeigenNotify {

	@Override
	IMeigenManager manager() {
		return new IchiroManager();
	}

}
