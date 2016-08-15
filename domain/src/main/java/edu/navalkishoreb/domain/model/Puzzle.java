package edu.navalkishoreb.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by navalb on 12-08-2016.
 */

public class Puzzle implements Parcelable {
  public static final int GRID_SIZE = 9;
  private static final String TAG = Puzzle.class.getSimpleName();
  private List<Image> imageGrid;

  public Puzzle(List<Image> imageGrid) {
    this.imageGrid = imageGrid;
    check();
    removeExtraImages();
  }

  private Puzzle(Parcel in) {
    imageGrid = new ArrayList<>(0);
    in.readList(imageGrid, Image.class.getClassLoader());
  }

  public static final Creator<Puzzle> CREATOR = new Creator<Puzzle>() {
    @Override public Puzzle createFromParcel(Parcel in) {
      return new Puzzle(in);
    }

    @Override public Puzzle[] newArray(int size) {
      return new Puzzle[size];
    }
  };

  private void check() {
    if (imageGrid == null) {
      throw new NullPointerException("Puzzle grid can not be NULL");
    } else if (imageGrid.size() < GRID_SIZE) {
      throw new IllegalArgumentException("Puzzle grid can not be less than " + GRID_SIZE);
    } else {
      Log.d(TAG, "puzzle within grid range");
    }
  }

  private void removeExtraImages() {
    if (imageGrid.size() > GRID_SIZE) {
      Log.d(TAG, "removing extra images");
      for (int position = imageGrid.size() - 1; position >= GRID_SIZE; position--) {
        imageGrid.remove(position);
      }
    }
  }

  public Image getRandomImage() {
    Random random = new Random(SystemClock.currentThreadTimeMillis());
    return imageGrid.get(random.nextInt(GRID_SIZE));
  }

  public List<Image> getImageGrid() {
    return imageGrid;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeList(imageGrid);
  }
}
