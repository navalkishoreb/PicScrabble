package edu.navalkishoreb.data.model;

import edu.navalkishoreb.domain.model.Response;

/**
 * Created by navalb on 15-08-2016.
 */

public class Feed implements Response<FlickerFeed> {

  private FlickerFeed flickerFeed;
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

  @Override public void setData(FlickerFeed data) {
    this.flickerFeed = data;
  }

  @Override public FlickerFeed getData() {
    return flickerFeed;
  }
}
