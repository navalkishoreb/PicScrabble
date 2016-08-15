package edu.navalkishoreb.picscramble.launch;

import android.os.Bundle;
import edu.navalkishoreb.picscramble.R;
import edu.navalkishoreb.picscramble.base.BaseActivity;
import edu.navalkishoreb.picscramble.play.Play;

public class Launch extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wireframe);
    setBottomBarText(R.string.action_play);
    setFragment(R.string.tag_how_to_play_info);
  }

  @Override public void performBottomBarAction() {
    Play.launch(this);
    overridePendingTransition(0, 0);
    finish();
  }
}
