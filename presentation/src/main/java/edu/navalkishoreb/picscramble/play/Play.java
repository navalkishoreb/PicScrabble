package edu.navalkishoreb.picscramble.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import edu.navalkishoreb.picscramble.R;

public class Play extends BasePlay {

  public static void launch(Context context) {
    Intent intent = new Intent(context, Play.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wireframe);
    FragmentManager.enableDebugLogging(true);
  }
}
