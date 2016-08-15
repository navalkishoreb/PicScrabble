package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

public class LoadingImages extends BaseFragment {

  public static BaseFragment newInstance() {
    return new LoadingImages();
  }

  public LoadingImages() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_loading_images, container, false);
  }
}
