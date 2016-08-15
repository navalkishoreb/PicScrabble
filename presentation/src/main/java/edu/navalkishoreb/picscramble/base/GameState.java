package edu.navalkishoreb.picscramble.base;

/**
 * Created by navalb on 12-08-2016.
 */

public enum GameState {
  LOAD_IMAGE,
  NEW_GAME,
  RANDOM_IMAGE,
  PUZZLE;

  public static GameState from(int ordinal) {
    switch (ordinal) {
      case 0:
        return LOAD_IMAGE;
      case 1:
        return NEW_GAME;
      case 2:
        return RANDOM_IMAGE;
      case 3:
        return PUZZLE;
      default:
        throw new IllegalArgumentException("this ordinal state is not implemented");
    }
  }
}
