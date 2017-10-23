package dagger.app.com.mvp.dagger;

import android.util.Log;

import dagger.app.com.mvp.model.Feed;
import dagger.app.com.mvp.model.FeedItem;
import dagger.app.com.mvp.network.ServiceExecuter;

/**
 * Created by 611399999 on 05/10/2017.
 */

public class RepositoryListPresenter {
    private static final String TAG = "RepositoryListPresenter";
    private final ViewObserver viewObserver;

    private Feed feedObject = null;

    public RepositoryListPresenter(ViewObserver viewObserver) {
        this.viewObserver = viewObserver;
    }

    public void bindViewFromHolder(int position, RepositoryRowView rowView){
        FeedItem repository =feedObject.getChannel().getFeedItems().get(position);
        rowView.setName(repository.getTitle());
        rowView.setDescription(repository.getDescription());
        rowView.setItemImage(repository.getImageUrl());
        Log.d(TAG, "bindViewFromHolder() called with: position = [" + position + "], rowView = [" + rowView + "]");
    }

    public int getrepositoryCount(){
        return feedObject == null ? 0 : feedObject.getChannel().getFeedItems().size();
    }

    public void fetchData(){
        ServiceExecuter executer = new ServiceExecuter();
        executer.fetchFeeds(new ServiceExecuter.OnFetchCompleteListener() {
            @Override
            public void fetchCompleted(Feed feed) {
                Log.i(TAG, "fetchCompleted: "+feed);
                feedObject = feed;
                viewObserver.onDataUpdated(feed.getChannel().getImage().getUrl());
            }

            @Override
            public void fetchFailed() {
                viewObserver.onDatafetchFailed();
            }
        });
    }

    public interface ViewObserver {
        void onDataUpdated(String headerUrl);

        void onDatafetchFailed();
    }
}
