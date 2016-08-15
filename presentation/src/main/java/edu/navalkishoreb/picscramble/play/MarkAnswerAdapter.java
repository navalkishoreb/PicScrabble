package edu.navalkishoreb.picscramble.play;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.picscramble.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by navalb on 14-08-2016.
 */

class MarkAnswerAdapter extends RecyclerView.Adapter<MarkAnswerAdapter.MarkAnswerHolder> {

  private List<Image> images;
  private MarkAnswerAdapter.Callback callback;
  private ArrayList<Image> markedImage;
  private Image shownImage;

  interface Callback {
    void onItemClicked(MarkAnswerHolder holder);
  }

  MarkAnswerAdapter() {
  }

  public void setCallback(MarkAnswerAdapter.Callback callback) {
    this.callback = callback;
  }

  void setData(List<Image> images, Image shownImage, ArrayList<Image> markedImage) {
    if (images == null) {
      throw new IllegalArgumentException("Cannot set NULL image list");
    }
    this.images = images;
    this.shownImage = shownImage;
    this.markedImage = markedImage;
  }

  void clear() {
    if (this.images != null) {
      this.images.clear();
    }
  }

  @Override
  public MarkAnswerAdapter.MarkAnswerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_view, parent, false);
    return new MarkAnswerAdapter.MarkAnswerHolder(itemView);
  }

  @Override public void onBindViewHolder(MarkAnswerAdapter.MarkAnswerHolder holder, int position) {
    holder.setData(images.get(position));

    if (markedImage != null && markedImage.contains(holder.getImage())) {
      holder.mark(shownImage);
    }
  }

  @Override public int getItemCount() {
    if (images != null) {
      return images.size();
    } else {
      return 0;
    }
  }

  class MarkAnswerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Image image;
    private ImageView imageView;

    MarkAnswerHolder(View rootView) {
      super(rootView);
      imageView = (ImageView) rootView.findViewById(R.id.puzzle_item);
      imageView.setOnClickListener(this);
    }

    public Image getImage() {
      return image;
    }

    private void setData(Image image) {
      this.image = image;
      this.imageView.setImageResource(R.drawable.question_mark);
    }

    private boolean compare(Image shownImage) {
      return image.getUrl().equalsIgnoreCase(shownImage.getUrl());
    }

    private void correctAnswer() {
      Picasso.with(imageView.getContext())
          .load(image.getUrl())
          .error(R.mipmap.ic_launcher)
          .noPlaceholder()
          .into(imageView);
      callback = null;
    }

    private void wrongAnswer() {
      imageView.setImageResource(R.drawable.wrong_answer);
      imageView.setOnClickListener(null);
    }

    @Override public void onClick(View view) {
      switch (view.getId()) {
        case R.id.puzzle_item:
          if (callback != null) {
            callback.onItemClicked(this);
          }
          break;
        default:
          throw new IllegalArgumentException("No such view has been implemented");
      }
    }

    boolean mark(Image shownImage) {
      boolean correct = compare(shownImage);
      if (correct) {
        correctAnswer();
      } else {
        wrongAnswer();
      }
      return correct;
    }
  }
}