package forest.rice.field.k.linenotify.notify;

import forest.rice.field.k.linenotify.TYPE;
import forest.rice.field.k.linenotify.api.line.LineNotify;
import forest.rice.field.k.linenotify.api.rakutenbooks.RakutenBooksManager;
import forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.RakutenBooksService;
import forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.response.Item;

public class SwitchArrivalNotify extends AbstractNotify {

	String applicationId = System.getenv("applicationId");
	String ismbs = System.getenv("items");

	@Override
	void init() {

	}

	@Override
	TYPE getType() {
		return TYPE.SWITCH;
	}

	@Override
	int sendMessage(String token) {
		sendCustomMessage(token);
		return 0;
	}

	public int sendCustomMessage(final String token) {
		System.out.println("sendCustomMessage");

		RakutenBooksManager manager = new RakutenBooksManager();

		// ExecutorService exec = Executors.newCachedThreadPool();
		RakutenBooksService service = manager.createRakutenBooksService();

		String[] isbnList = ismbs.split(",");

		for (String isbn : isbnList) {
			System.out.println(isbn);
			// exec.execute(createRunnable(manager, service, applicationId,
			// isbn, token));
			runTask(manager, service, applicationId, isbn, token);
		}
		return 0;
	}

	// private Runnable createRunnable(final RakutenBooksManager manager, final
	// RakutenBooksService service,
	// final String applicationId, final String isbnjan, final String token) {
	//
	// return new Runnable() {
	//
	// @Override
	// public void run() {
	// System.out.println("run!");
	// runTask(manager, service, applicationId, isbnjan, token);
	// System.out.println("run! finish");
	// }
	// };
	// }

	private void runTask(final RakutenBooksManager manager, final RakutenBooksService service,
			final String applicationId, final String isbnjan, final String token) {
		System.out.println("run2!");
		try {
			Item result = manager.getItem(service, applicationId, isbnjan);

			System.out.println("在庫：" + result.getItems().get(0).getItem().getAvailability());

			result.getItems().stream().filter(item -> {
				System.out.println("Availability :" + item.getItem().getAvailability());
				return !"6".equals(item.getItem().getAvailability());
			}).forEach(item -> {
				System.out.println(item.getItem().getItemUrl());
				String message = "在庫あり!\r\n" + item.getItem().getItemUrl();

				LineNotify.sendMessage(token, message, resizedImgsrc(item.getItem().getLargeImageUrl()));
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
