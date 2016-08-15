package edu.navalkishoreb.picscramble.play;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RandomImage extends BaseRandomImage {

  public static BaseFragment newInstance() {
    return new RandomImage();
  }

  public RandomImage() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_random_image, container, false);
  }

  @Override public void onStart() {
    super.onStart();
    if (getView() == null) {
      throw new IllegalArgumentException("Root view cannot be NULL");
    }
    ImageView imageView = (ImageView) getView().findViewById(R.id.random_image);
    if (imageView == null) {
      throw new IllegalArgumentException("random image view is not provided");
    }
    Picasso.with(getContext()).load(randomImage.getUrl()).into(imageView);
  }
}
