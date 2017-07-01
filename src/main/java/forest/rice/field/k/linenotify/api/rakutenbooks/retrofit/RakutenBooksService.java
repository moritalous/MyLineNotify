package forest.rice.field.k.linenotify.api.rakutenbooks.retrofit;

import forest.rice.field.k.linenotify.api.rakutenbooks.retrofit.response.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RakutenBooksService {
	@GET("services/api/BooksTotal/Search/20170404")
	Call<Item> search(@Query("applicationId") String applicationId, @Query("isbnjan") String isbnjan,
			@Query("outOfStockFlag") int outOfStockFlag);
}
