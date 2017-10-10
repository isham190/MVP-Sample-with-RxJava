package dagger.app.com.dagger.dagger;

import android.util.Log;

import java.util.List;

import dagger.app.com.dagger.model.Channel;
import dagger.app.com.dagger.model.Repository;
import dagger.app.com.dagger.network.ServiceExecuter;

/**
 * Created by 611399999 on 05/10/2017.
 */

public class RepositoryListPresenter {
    private static final String TAG = "RepositoryListPresenter";
    private final List<Repository> repositoryList;

    static RepositoryListPresenter instance = null;

//    public static RepositoryListPresenter getInstance(){
//        return instance == null? new RepositoryListPresenter() : instance;
//    }

    public RepositoryListPresenter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public void bindViewFromHolder(int position, RepositoryRowView rowView){
        Repository repository =repositoryList.get(position);
        rowView.setName(repository.getRepositoryName());
        rowView.setDescription(repository.getRepositoryDescription());
        Log.d(TAG, "bindViewFromHolder() called with: position = [" + position + "], rowView = [" + rowView + "]");
    }

    public int getrepositoryCount(){
        return repositoryList.size();
    }

    public void fetchData(){
        ServiceExecuter executer = new ServiceExecuter();
        executer.fetchFeeds(new ServiceExecuter.OnFetchCompleteListener() {
            @Override
            public void fetchCompleted(Channel channel) {

            }

            @Override
            public void fetchFailed() {

            }
        });
    }
}
