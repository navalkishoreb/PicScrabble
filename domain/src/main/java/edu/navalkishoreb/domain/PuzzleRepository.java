package edu.navalkishoreb.domain;

import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.domain.model.Response;

/**
 * Created by navalb on 15-08-2016.
 */

public interface PuzzleRepository {
  Response<Puzzle> getPuzzle();
}
