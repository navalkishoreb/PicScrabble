package edu.navalkishoreb.picscramble.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import edu.navalkishoreb.picscramble.R;

/**
 * Created by navalb on 12-08-2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityView {

  private Button button;
  private FragmentManager fragmentManager;
  private Fragment currentFragment;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (fragmentManager == null) {
      fragmentManager = getSupportFragmentManager();
    }
  }

  private View.OnClickListener bottomBarListener = new View.OnClickListener() {
    @Override public void onClick(View view) {
      performBottomBarAction();
    }
  };

  private Button getBottomBar() {
    if (button == null) {
      View view = findViewById(R.id.btn_bottom_bar);
      if (view == null) {
        throw new IllegalArgumentException(
            "BottomBar button with id btn_bottom_bar need to be implemented in XML");
      }
      view.setOnClickListener(bottomBarListener);
      button = (Button) view;
    }
    return button;
  }

  @Override public void setBottomBarText(int textResourceId) {
    button = getBottomBar();
    button.setText(getString(textResourceId));
  }

  private View getContainerFrame() {
    View view = findViewById(R.id.fragment_container);
    if (view == null) {
      throw new IllegalArgumentException(
          "Your Activity must implement fragment_container frame in XML");
    }
    return view;
  }

  @Override public void setFragment(int fragmentTag) {
    String tag = getString(fragmentTag);
    Fragment fragment = getTargetFragment(fragmentTag);
    setFragment(fragment, tag);
  }

  @Override public Fragment getTargetFragment(int fragmentTag) {
    Fragment fragment = fragmentManager.findFragmentByTag(getString(fragmentTag));
    if (fragment == null) {
      fragment = FragmentFactory.get(fragmentTag);
      Log.d("Puzzle", "creating new fragment " + getString(fragmentTag));
    } else {
      Log.d("Puzzle", "reusing fragment " + getString(fragmentTag));
    }
    return fragment;
  }

  @Override public void setFragment(Fragment fragment, String fragmentTag) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(getContainerFrame().getId(), fragment, fragmentTag);
    transaction.commitAllowingStateLoss();
    currentFragment = fragment;
  }
/*  @Override public void setFragment(Fragment fragment, String fragmentTag) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    if (!fragment.isAdded()) {
      transaction.add(getContainerFrame().getId(), fragment, fragmentTag);
    }
    transaction.show(fragment);
    if (currentFragment != null) {
      transaction.hide(currentFragment);
    }
    transaction.commitAllowingStateLoss();
    currentFragment = fragment;
  }*/

  @Override public void hideBottomBar() {
    button = getBottomBar();
    button.setVisibility(View.GONE);
  }

  @Override public void showBottomBar() {
    button = getBottomBar();
    button.setVisibility(View.VISIBLE);
  }

  @Override protected void onResume() {
    super.onResume();
    showCurrentFragmentIfHidden();
  }

  private void showCurrentFragmentIfHidden() {
    if (currentFragment.isHidden()) {
      FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.show(currentFragment).commitNow();
    }
  }
}
