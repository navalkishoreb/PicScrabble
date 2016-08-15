package edu.navalkishoreb.picscramble.play;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

public class NewGame extends BaseNewGame {

  public static BaseFragment newInstance() {
    return new NewGame();
  }

  private ProgressBar progressBar;
  private CountDownTimer timer;
  private PlayArea playArea;

  public NewGame() {
    // Required empty public constructor
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof PlayArea)) {
      throw new IllegalArgumentException("Activity need to implement PlayArea");
    }
    playArea = (PlayArea) activity;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_new_game, container, false);
  }

  @Override public void onStart() {
    super.onStart();
    if (getView() == null) {
      throw new IllegalArgumentException("Root View cannot be null");
    }
    progressBar = (ProgressBar) getView().findViewById(R.id.counter_view);
    progressBar.setMax(MAX_TIME);
    progressBar.setProgress((int) timeUntil);
    RecyclerView puzzleGrid = (RecyclerView) getView().findViewById(R.id.puzzle_view);
    puzzleGrid.setLayoutManager(
        new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_column)));
    puzzleGrid.setAdapter(gridAdapter);
    gridAdapter.notifyDataSetChanged();
  }

  @Override public void onResume() {
    super.onResume();
    timer = getTimer();
    timer.start();
  }

  @Override public void onPause() {
    super.onPause();
    timer.cancel();
  }

  private CountDownTimer getTimer() {
    return new CountDownTimer(timeUntil, 1) {
      @Override public void onTick(long l) {
        timeUntil = (int) l;
        progressBar.setProgress((int) timeUntil);
      }

      @Override public void onFinish() {
        playArea.nextGameState();
      }
    };
  }
}
