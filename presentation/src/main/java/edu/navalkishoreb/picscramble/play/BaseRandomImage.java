package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * Created by navalb on 14-08-2016.
 */

abstract class BaseRandomImage extends BaseFragment {

  Image randomImage;

  void setImage(Image image) {
    this.randomImage = image;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable("randomImage", randomImage);
  }

  private void onRestoreSavedInstance(Bundle savedInstance) {
    if (savedInstance != null) {
      this.randomImage = savedInstance.getParcelable("randomImage");
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onRestoreSavedInstance(savedInstanceState);
  }
}
