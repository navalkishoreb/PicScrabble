package edu.navalkishoreb.picscramble.play;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.picscramble.base.BaseFragment;
import java.util.ArrayList;

/**
 * Created by navalb on 14-08-2016.
 */

abstract class BaseMarkAnswer extends BaseFragment implements MarkAnswerAdapter.Callback {

  private Image shownImage;
  private Puzzle puzzle;
  MarkAnswerAdapter gridAdapter;
  private ArrayList<Image> markedImage;
  private BaseMarkAnswer.Callback callback;

  interface Callback {
    void correctAnswer();
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof BaseMarkAnswer.Callback)) {
      throw new IllegalArgumentException("Activity has to implement Answer callback");
    }
    callback = (Callback) activity;
  }

  void setData(Image shownImage, Puzzle puzzle) {
    if (puzzle == null || puzzle.getImageGrid() == null) {
      throw new IllegalArgumentException("Puzzle can not be NULL");
    }
    this.shownImage = shownImage;
    this.puzzle = puzzle;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable("shownImage", shownImage);
    outState.putParcelable("puzzle", puzzle);
    outState.putParcelableArrayList("marked", markedImage);
  }

  private void onRestoreSavedInstance(Bundle savedInstance) {
    if (savedInstance != null) {
      shownImage = savedInstance.getParcelable("shownImage");
      puzzle = savedInstance.getParcelable("puzzle");
      markedImage = savedInstance.getParcelableArrayList("marked");
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridAdapter = new MarkAnswerAdapter();
    markedImage = new ArrayList<>();
    onRestoreSavedInstance(savedInstanceState);

    gridAdapter.setData(puzzle.getImageGrid(), shownImage, markedImage);
    gridAdapter.setCallback(this);
  }

  @Override public void onItemClicked(MarkAnswerAdapter.MarkAnswerHolder holder) {
    markedImage.add(holder.getImage());
    if (holder.mark(shownImage)) {
      callback.correctAnswer();
    }
  }
}
