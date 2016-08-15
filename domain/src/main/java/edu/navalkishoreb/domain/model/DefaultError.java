package edu.navalkishoreb.domain.model;

/**
 * Created by navalb on 13-08-2016.
 */

public class DefaultError<T> extends ErrorResponse<T> {
  @Override public String getMessage() {
    return "Unknown Error";
  }

  @Override public String getErrorMessage() {
    return null;
  }

  @Override public void setErrorMessage(String errorString) {

  }

  @Override public void setData(T data) {

  }

  @Override public T getData() {
    return null;
  }
}
