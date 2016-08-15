package edu.navalkishoreb.data.model;

import java.util.List;

/**
 * Created by navalb on 15-08-2016.
 */

public class FlickerFeed {
  private String title;

  private List<Items> items;

  private String description;

  private String link;

  private String generator;

  private String modified;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Items> getItems() {
    return items;
  }

  public void setItems(List<Items> items) {
    this.items = items;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getGenerator() {
    return generator;
  }

  public void setGenerator(String generator) {
    this.generator = generator;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  @Override public String toString() {
    return "ClassPojo [title = " + title + ", items = " + items + ", description = " + description
        + ", link = " + link + ", generator = " + generator + ", modified = " + modified + "]";
  }
}
