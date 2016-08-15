package edu.navalkishoreb.picscramble.play;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.picscramble.R;
import java.util.List;

/**
 * Created by navalb on 14-08-2016.
 */

class PuzzleGridAdapter extends RecyclerView.Adapter<PuzzleGridAdapter.PuzzleViewHolder> {

  private List<Image> images;

  PuzzleGridAdapter() {
  }

  void setData(List<Image> images) {
    if (images == null) {
      throw new IllegalArgumentException("Cannot set NULL image list");
    }
    this.images = images;
  }

  @Override public PuzzleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_view, parent, false);
    return new PuzzleViewHolder(itemView);
  }

  @Override public void onBindViewHolder(PuzzleViewHolder holder, int position) {
    holder.setData(images.get(position));
  }

  @Override public int getItemCount() {
    if (images != null) {
      return images.size();
    } else {
      return 0;
    }
  }

  class PuzzleViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    PuzzleViewHolder(View rootView) {
      super(rootView);
      imageView = (ImageView) rootView.findViewById(R.id.puzzle_item);
    }

    public void setData(Image image) {
      Picasso.with(imageView.getContext())
          .load(image.getUrl())
          .error(R.mipmap.ic_launcher)
          .placeholder(R.drawable.question_mark)
          .into(imageView);
    }
  }
}
