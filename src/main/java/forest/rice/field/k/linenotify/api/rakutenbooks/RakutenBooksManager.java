package forest.rice.field.k.linenotify.api.rakutenbooks;

import forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.RakutenBooksService;
import forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.response.Item;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RakutenBooksManager {

	public static void main(String[] args) {
		try {
			new RakutenBooksManager().start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void start() throws Exception {

		// ExecutorService exec = Executors.newCachedThreadPool();
		// RakutenBooksService service = createRakutenBooksService();
		//
		// String[] isbnList = ismbs.split(",");
		//
		// for (String isbn : isbnList) {
		// exec.submit(createRunnable(service, applicationId, isbn));
		// }
	}

	public RakutenBooksService createRakutenBooksService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://app.rakuten.co.jp/")
				.addConverterFactory(JacksonConverterFactory.create()).build();
		return retrofit.create(RakutenBooksService.class);
	}

	public Item getItem(RakutenBooksService service, String applicationId, String isbnjan) throws Exception {
		Call<Item> calls = service.search(applicationId, isbnjan, 1);

		Response<Item> response = calls.execute();

		if (!response.isSuccessful()) {
			throw new Exception();
		}

		return response.body();
	}

}
