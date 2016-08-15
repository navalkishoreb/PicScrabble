package edu.navalkishoreb.domain.model;

/**
 * Created by navalb on 15-08-2016.
 */

public class PuzzleFeed implements Response<Puzzle> {

  private Puzzle puzzle;
  private String errorMessage;

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  @Override public boolean isSuccessful() {
    return errorMessage == null;
  }

  @Override public void setData(Puzzle data) {
    this.puzzle = data;
  }

  @Override public Puzzle getData() {
    return puzzle;
  }
}
