package dagger.app.com.mvp.network;

import android.util.Log;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import dagger.app.com.mvp.model.Feed;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by 611399999 on 06/10/2017.
 */

public class ServiceExecuter {

    private NetworkService service;
    private final String BASE_URL = "https://refind.com/feed/";
    private String TAG = ServiceExecuter.class.getSimpleName();

    public void fetchFeeds(final OnFetchCompleteListener listener) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
//                                .removeHeader("Pragma")
//                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME)) //Do caching related work.
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                }).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(SimpleXmlConverterFactory.create(new Persister(new AnnotationStrategy()))).addConverterFactory(ScalarsConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        service = retrofit.create(NetworkService.class);

        service.getPopularPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Feed>>() {
            @Override
            public ObservableSource<? extends Feed> apply(@NonNull Throwable throwable) throws Exception {
                return Observable.error(throwable);
            }
        }).subscribe(new Observer<Feed>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull Feed Feed) {
                Log.i(TAG, "onNext: "+Feed.getChannel().getTitle());
                listener.fetchCompleted(Feed);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                listener.fetchFailed();
                Log.e(TAG, "onError: "+ e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

    }

    public interface OnFetchCompleteListener {
        void fetchCompleted(Feed feed);

        void fetchFailed();
    }
}
