package edu.navalkishoreb.picscramble.play;

/**
 * Created by navalb on 12-08-2016.
 */

interface PlayArea extends FetchImages.Callback, BaseMarkAnswer.Callback {
  void nextGameState();
}
