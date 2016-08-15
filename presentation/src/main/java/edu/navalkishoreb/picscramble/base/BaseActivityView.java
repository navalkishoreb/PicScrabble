package edu.navalkishoreb.picscramble.base;

import android.support.v4.app.Fragment;

/**
 * Created by navalb on 12-08-2016.
 */

interface BaseActivityView extends BaseView {
  void setFragment(int fragmentTag);

  void setFragment(Fragment fragment, String fragmentTag);

  Fragment getTargetFragment(int fragmentTag);

  void hideBottomBar();

  void showBottomBar();
}
