package edu.navalkishoreb.picscramble.base;

import android.support.v4.app.Fragment;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.launch.HowToPlayInfo;
import edu.navalkishoreb.picscramble.play.FetchImages;
import edu.navalkishoreb.picscramble.play.LoadingImages;
import edu.navalkishoreb.picscramble.play.MarkAnswer;
import edu.navalkishoreb.picscramble.play.NewGame;
import edu.navalkishoreb.picscramble.play.RandomImage;

/**
 * Created by navalb on 12-08-2016.
 */

abstract class FragmentFactory {

  static Fragment get(int fragmentTagId) {
    Fragment fragment;
    switch (fragmentTagId) {
      case R.string.tag_how_to_play_info:
        fragment = HowToPlayInfo.newInstance();
        break;
      case R.string.tag_loading_images:
        fragment = LoadingImages.newInstance();
        break;
      case R.string.tag_new_game:
        fragment = NewGame.newInstance();
        break;
      case R.string.tag_RandomImage:
        fragment = RandomImage.newInstance();
        break;
      case R.string.tag_MarkAnswer:
        fragment = MarkAnswer.newInstance();
        break;

      case R.string.tag_FetchImages:
        fragment = FetchImages.newInstance();
        break;
      default:
        throw new IllegalArgumentException("Requested fragment id is not implemented");
    }

    return fragment;
  }
}
