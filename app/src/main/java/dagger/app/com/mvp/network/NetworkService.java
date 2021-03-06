package dagger.app.com.mvp.network;


import dagger.app.com.mvp.model.Feed;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 611399999 on 05/10/2017.
 */

public interface NetworkService {

    @GET("popular.rss")
    Observable<Feed> getPopularPosts();
}
