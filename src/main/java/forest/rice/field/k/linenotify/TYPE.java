package forest.rice.field.k.linenotify;

import forest.rice.field.k.linenotify.notify.DanshiNotify;
import forest.rice.field.k.linenotify.notify.MocosNotify;
import forest.rice.field.k.linenotify.notify.RolaNotify;
import forest.rice.field.k.linenotify.notify.ShuzoNotify;

public enum TYPE {
	MOCO("moco"), DANSHI("danshi"), ROLA("rola"), SHUZO("shuzo"), NONE("");

	private final String type;

	private TYPE(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static TYPE getType(String str) {

		if (str == null) {
			return TYPE.NONE;
		}

		switch (str) {
		case "moco":
			return MOCO;
		case "danshi":
			return DANSHI;
		case "rola":
			return ROLA;
		case "shuzo":
			return SHUZO;
		}

		return TYPE.NONE;
	}

	public static String getType(RequestInput input) {
		String str = "";
		try {
			str = input.getPath().get("type");
		} catch (Exception e) {
			e.printStackTrace();
			str = "";
		}
		return str;
	}

	public void sendOneTimeMessage(String token) {
		switch (this) {
		case MOCO: {
			MocosNotify notify = new MocosNotify();
			notify.sendOnetimeMessage(token);
		}
			break;
		case DANSHI: {
			DanshiNotify notify = new DanshiNotify();
			notify.sendOnetimeMessage(token);
		}
			break;
		case ROLA: {
			RolaNotify notify = new RolaNotify();
			notify.sendOnetimeMessage(token);
		}
			break;
		case SHUZO: {
			ShuzoNotify notify = new ShuzoNotify();
			notify.sendOnetimeMessage(token);
		}

		default:
			break;
		}
	}
}
