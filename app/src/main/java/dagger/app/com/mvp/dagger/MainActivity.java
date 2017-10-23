package dagger.app.com.mvp.dagger;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.app.com.mvp.R;

public class MainActivity extends AppCompatActivity implements RepositoryListPresenter.ViewObserver {

    @BindView(R.id.main_recyclerView) RecyclerView recyclerView;
    @BindView(R.id.loading_progress)ContentLoadingProgressBar progressBar;
    @BindView(R.id.feed_header_iv)ImageView headerImageView;
    @BindView(R.id.loading_error)
    TextView errorTextView;

    private DaggerAdapter adapter = null;
    private RepositoryListPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showRecyclerView();
    }

    private void showRecyclerView() {
        progressBar.show();
        progressBar.onAttachedToWindow();
        presenter = new RepositoryListPresenter(this);
        presenter.fetchData();

        adapter = new DaggerAdapter(this, presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDataUpdated(String headerUrl) {
        progressBar.hide();
        headerImageView.setVisibility(View.VISIBLE);
        Picasso.with(this).load(headerUrl).into(headerImageView);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDatafetchFailed() {
        progressBar.hide();
        errorTextView.setVisibility(View.VISIBLE);
    }
}
