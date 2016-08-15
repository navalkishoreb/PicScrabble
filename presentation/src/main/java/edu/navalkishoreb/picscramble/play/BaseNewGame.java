package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * Created by navalb on 14-08-2016.
 */

abstract class BaseNewGame extends BaseFragment {

  private Puzzle puzzle;
  static final int MAX_TIME = 15 * 1000;
  long timeUntil = MAX_TIME;

  PuzzleGridAdapter gridAdapter;

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable("puzzle", puzzle);
    outState.putLong("timeUntil", timeUntil);
  }

  private void onRestoreSavedInstance(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      puzzle = savedInstanceState.getParcelable("puzzle");
      timeUntil = savedInstanceState.getLong("timeUntil");
    }
    if (puzzle == null) {
      throw new IllegalArgumentException("Puzzle data need to set before attaching");
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onRestoreSavedInstance(savedInstanceState);
    gridAdapter = new PuzzleGridAdapter();
    gridAdapter.setData(puzzle.getImageGrid());
  }

  void setData(Puzzle puzzle) {
    if (puzzle == null) {
      throw new IllegalArgumentException("puzzle cannot be NULL");
    }

    this.puzzle = puzzle;
  }
}
