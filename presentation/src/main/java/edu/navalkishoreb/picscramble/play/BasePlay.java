package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseActivity;
import edu.navalkishoreb.picscramble.base.GameState;

import static edu.navalkishoreb.picscramble.base.GameState.LOAD_IMAGE;

/**
 * Created by navalb on 12-08-2016.
 */

abstract class BasePlay extends BaseActivity implements PlayArea, GameSequence {

  private GameState currentGameState;

  private Image randomImage;
  private Puzzle currentPuzzle;
  private boolean answeredCorrectly;

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    saveGameState(outState);
  }

  private void saveGameState(Bundle outState) {
    if (currentGameState != null) {
      outState.putInt(getString(R.string.key_game_state), currentGameState.ordinal());
      outState.putParcelable("shownImage", randomImage);
      outState.putParcelable("currentPuzzle", currentPuzzle);
      outState.putByte("correctAnswer", (byte) (answeredCorrectly ? 1 : 0));
    }
  }

  private void setSavedGameState(Bundle savedInstanceState) {
    int stateOrdinal = GameState.LOAD_IMAGE.ordinal();
    if (savedInstanceState != null) {
      stateOrdinal =
          savedInstanceState.getInt(getString(R.string.key_game_state), LOAD_IMAGE.ordinal());
      randomImage = savedInstanceState.getParcelable("shownImage");
      currentPuzzle = savedInstanceState.getParcelable("currentPuzzle");
      answeredCorrectly = savedInstanceState.getByte("correctAnswer") == 1;
    }
    currentGameState = GameState.from(stateOrdinal);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSavedGameState(savedInstanceState);
  }

  @Override protected void onStart() {
    super.onStart();
    viewCurrentGameState();
  }

  private void viewCurrentGameState() {
    switch (currentGameState) {
      case LOAD_IMAGE:
        loadImages();
        break;
      case NEW_GAME:
        loadNewGame();
        break;
      case RANDOM_IMAGE:
        showRandomImage();
        break;
      case PUZZLE:
        solvePuzzle();
        break;
      default:
        throw new IllegalArgumentException("No such game state exist or implemented");
    }
  }

  @Override public void performBottomBarAction() {
    nextGameState();
  }

  @Override public void nextGameState() {
    currentGameState = nextState(currentGameState);
    viewCurrentGameState();
  }

  private GameState nextState(GameState currentGameState) {
    switch (currentGameState) {
      case LOAD_IMAGE:
        return GameState.NEW_GAME;
      case NEW_GAME:
        return GameState.RANDOM_IMAGE;
      case RANDOM_IMAGE:
        return GameState.PUZZLE;
      case PUZZLE:
        return LOAD_IMAGE;
      default:
        throw new IllegalArgumentException("No such game state exist or implemented");
    }
  }

  @Override public void loadImages() {
    hideBottomBar();
    hideActionBar();
    resetValues();
    setFragment(R.string.tag_loading_images);
    addHeadLessFragment(R.string.tag_FetchImages);
  }

  private void resetValues() {
    randomImage = null;
    currentPuzzle = null;
    answeredCorrectly = false;
  }

  private void addHeadLessFragment(int tag_fetchImages) {
    Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(tag_fetchImages));
    if (fragment == null) {
      fragment = FetchImages.newInstance();
    }
    FetchImages fetchImages = (FetchImages) fragment;
    if (!fragment.isAdded()) {
      getSupportFragmentManager().beginTransaction()
          .add(fragment, getString(tag_fetchImages))
          .commitNow();
      fetchImages.fetchData();
    } else {
      fetchImages.clearData();
      fetchImages.fetchData();
    }
  }

  private void hideActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }
  }

  private void showActionBar() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().show();
    }
  }

  @Override public void loadNewGame() {
    hideBottomBar();
    showActionBar();
    setActionBarText();
    setBottomBarText(R.string.action_puzzle_me);
    BaseNewGame fragment = (BaseNewGame) getTargetFragment(R.string.tag_new_game);
    fragment.setData(currentPuzzle);
    setFragment(fragment, getString(R.string.tag_new_game));
  }

  private void setActionBarText() {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(R.string.remember);
    }
  }

  @Override public void showRandomImage() {
    showBottomBar();
    hideActionBar();
    BaseRandomImage fragment = (BaseRandomImage) getTargetFragment(R.string.tag_RandomImage);
    if (randomImage == null) {
      randomImage = currentPuzzle.getRandomImage();
    }
    fragment.setImage(randomImage);
    setBottomBarText(R.string.action_ready);
    setFragment(fragment, getString(R.string.tag_RandomImage));
  }

  @Override public void solvePuzzle() {
    if (answeredCorrectly) {
      showBottomBar();
    } else {
      hideBottomBar();
    }
    hideActionBar();
    BaseMarkAnswer fragment = (BaseMarkAnswer) getTargetFragment(R.string.tag_MarkAnswer);
    fragment.setData(randomImage, currentPuzzle);
    setBottomBarText(R.string.action_restart);
    setFragment(fragment, getString(R.string.tag_MarkAnswer));
  }

  @Override public void onResult(Puzzle puzzle) {
    currentPuzzle = puzzle;
    nextGameState();
  }

  @Override public void onError(String errorMessage) {

  }

  @Override public void correctAnswer() {
    answeredCorrectly = true;
    showBottomBar();
  }
}
