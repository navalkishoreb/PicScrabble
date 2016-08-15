package edu.navalkishoreb.domain.usecase;

import edu.navalkishoreb.domain.PuzzleRepository;

/**
 * Created by navalb on 13-08-2016.
 */

public abstract class UseCaseFactory {

  public static UseCase get(Requirement requirement, PuzzleRepository repository) {
    switch (requirement) {
      case FETCH_PUZZLE:
        return new FetchPuzzle(repository);
      default:
        throw new IllegalArgumentException("No such requirement is implemented");
    }
  }
}
