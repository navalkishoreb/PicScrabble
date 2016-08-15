package edu.navalkishoreb.domain.model;

/**
 * Created by navalb on 13-08-2016.
 */

public abstract class ErrorResponse<T> implements Response<T> {
  @Override public boolean isSuccessful() {
    return false;
  }

  public abstract String getMessage();
}
