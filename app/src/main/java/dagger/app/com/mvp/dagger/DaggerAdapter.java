package dagger.app.com.mvp.dagger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.app.com.mvp.R;

/**
 * Created by 611399999 on 04/10/2017.
 */

public class DaggerAdapter extends RecyclerView.Adapter<DaggerAdapter.DaggerViewHolder> {

    private static final String TAG = DaggerAdapter.class.getSimpleName();
    private final RepositoryListPresenter presenter;
    private final Context context;

    public DaggerAdapter(Context context, RepositoryListPresenter presenter) {
        this.context = context;
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


        @BindView(R.id.feed_item_name)
        TextView titleTextView;

        @BindView(R.id.feed_item_descption)
        TextView descriptionTextView;

        @BindView(R.id.feed_item_iv)
        ImageView itemImageView;

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

        @Override
        public void setItemImage(String url) {
            Log.i(TAG, "setItemImage: "+url);
            Picasso.with(context).load(url).into(itemImageView);
        }
    }
}
