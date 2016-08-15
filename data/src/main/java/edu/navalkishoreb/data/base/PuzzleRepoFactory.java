package edu.navalkishoreb.data.base;

import edu.navalkishoreb.domain.PuzzleRepository;

/**
 * Created by navalb on 13-08-2016.
 */

public abstract class PuzzleRepoFactory {

  public static PuzzleRepository get() {
    return new PuzzleRepoImpl();
  }
}
