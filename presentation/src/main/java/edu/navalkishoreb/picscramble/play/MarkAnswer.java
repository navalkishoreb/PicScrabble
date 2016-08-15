package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkAnswer extends BaseMarkAnswer {

  public static BaseFragment newInstance() {
    return new MarkAnswer();
  }

  public MarkAnswer() {
    // Required empty public constructor
  }

  private RecyclerView puzzleGrid;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_mark_answer, container, false);
  }

  @Override public void onStart() {
    super.onStart();
    if (getView() == null) {
      throw new IllegalArgumentException("Root View cannot be null");
    }
    puzzleGrid = (RecyclerView) getView().findViewById(R.id.mark_answer_grid);
    puzzleGrid.setLayoutManager(
        new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_column)));
    puzzleGrid.setAdapter(gridAdapter);
    gridAdapter.notifyDataSetChanged();
  }
}
