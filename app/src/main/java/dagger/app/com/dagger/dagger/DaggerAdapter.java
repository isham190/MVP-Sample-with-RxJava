package dagger.app.com.dagger.dagger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.app.com.dagger.R;

/**
 * Created by 611399999 on 04/10/2017.
 */

public class DaggerAdapter extends RecyclerView.Adapter<DaggerAdapter.DaggerViewHolder> {

    private final RepositoryListPresenter presenter;

    public DaggerAdapter(RepositoryListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public DaggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DaggerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item, null));
    }

    @Override
    public void onBindViewHolder(DaggerViewHolder holder, int position) {
        presenter.bindViewFromHolder(position,holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getrepositoryCount();
    }

    public class DaggerViewHolder extends RecyclerView.ViewHolder implements RepositoryRowView {


        @BindView(R.id.name)
        TextView titleTextView;

        @BindView(R.id.descption)
        TextView descriptionTextView;

        public DaggerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setName(String name) {
            titleTextView.setText(name);
        }

        @Override
        public void setDescription(String description) {
            descriptionTextView.setText(description);
        }
    }
}
