package edu.navalkishoreb.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by navalb on 12-08-2016.
 */

public class Image implements Parcelable {
  String url;

  public Image(String url) {
    this.url = url;
  }

  protected Image(Parcel in) {
    url = in.readString();
  }

  public static final Creator<Image> CREATOR = new Creator<Image>() {
    @Override public Image createFromParcel(Parcel in) {
      return new Image(in);
    }

    @Override public Image[] newArray(int size) {
      return new Image[size];
    }
  };

  public String getUrl() {
    return url;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(url);
  }
}
