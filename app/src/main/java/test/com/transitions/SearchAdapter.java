package test.com.transitions;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by fahim on 3/10/15.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_search, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

    }


    public int getItemCount() {
        return 50;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View card;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.row_card);
            image = (ImageView) itemView.findViewById(R.id.row_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("position", getPosition());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.card.setTransitionName("card" + getPosition());
                this.image.setTransitionName("image" + getPosition());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)v.getContext(),
                        new Pair<View, String>(this.card, this.card.getTransitionName()),
                        new Pair<View, String>(this.image, this.image.getTransitionName()));
                v.getContext().startActivity(intent, options.toBundle());
            } else {
                v.getContext().startActivity(intent);
            }
        }

    }
}
