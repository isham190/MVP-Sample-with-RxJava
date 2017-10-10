package dagger.app.com.dagger.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.app.com.dagger.R;
import dagger.app.com.dagger.model.Repository;
import dagger.app.com.dagger.network.ServiceExecuter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recyelerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showRecyclerCiew();
    }

    private void showRecyclerCiew() {
        List dataList = new ArrayList();
        for(int i=0; i< 10; i++){
            dataList.add(new Repository("Name: "+i, "Description: "+i));
        }

        RepositoryListPresenter presenter = new RepositoryListPresenter(dataList);
        presenter.fetchData();

        DaggerAdapter adapter = new DaggerAdapter(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}
