package edu.navalkishoreb.picscramble.launch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToPlayInfo extends BaseFragment implements GameRulesView {

  public static BaseFragment newInstance() {
    return new HowToPlayInfo();
  }

  public HowToPlayInfo() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_how_to_play_info, container, false);
  }

/*  @Override public void onStart() {
    super.onStart();
    if (getView() == null) {
      throw new IllegalArgumentException("RootView should not be NULL");
    }
    TextView textView = (TextView) getView().findViewById(R.id.text_how_to_play_info);
    textView.setMovementMethod(ScrollingMovementMethod.getInstance());
  }*/
}
