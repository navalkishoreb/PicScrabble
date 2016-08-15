package edu.navalkishoreb.picscramble.play;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FetchError extends BaseFragment implements View.OnClickListener {

  public static Fragment newInstance(String errorMessage) {
    Fragment fragment = new FetchError();
    Bundle bundle = new Bundle();
    bundle.putString("errorMessage", errorMessage);
    fragment.setArguments(bundle);
    return fragment;
  }

  public FetchError() {
    // Required empty public constructor
  }

  interface Callback {
    void retry();
  }

  private String errorMessage;
  private Callback callback;

  @SuppressWarnings("deprecation") @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof FetchError.Callback)) {
      throw new IllegalArgumentException("Activity need to implement FetchError.Callback");
    }
    callback = (Callback) activity;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = getArguments();
    if (bundle == null) {
      throw new IllegalArgumentException("error message bundle not passed");
    }
    errorMessage = bundle.getString("errorMessage");
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_fetch_error, container, false);
  }

  @Override public void onStart() {
    super.onStart();
    if (getView() == null) {
      throw new IllegalArgumentException("Root view can not be NULL");
    }
    TextView textView = (TextView) getView().findViewById(R.id.error_message);
    textView.setText(errorMessage);
    Button button = (Button) getView().findViewById(R.id.retry);
    button.setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.retry:
        callback.retry();
        break;
      default:
        throw new IllegalArgumentException("On click not implemented");
    }
  }
}
