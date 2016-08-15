package edu.navalkishoreb.domain.model;

/**
 * Created by navalb on 13-08-2016.
 */

public interface Response<T> {

  String getErrorMessage();

  void setErrorMessage(String errorString);

  boolean isSuccessful();

  void setData(T data);

  T getData();
}
