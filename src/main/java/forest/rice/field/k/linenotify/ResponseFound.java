package forest.rice.field.k.linenotify;

public class ResponseFound extends Throwable {

	private static final long serialVersionUID = -5040465224380295568L;

	public ResponseFound(String uri) {
		super(uri);
	}
}