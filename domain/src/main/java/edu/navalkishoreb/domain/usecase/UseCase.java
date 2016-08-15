package edu.navalkishoreb.domain.usecase;

import edu.navalkishoreb.domain.model.Response;

/**
 * Created by navalb on 13-08-2016.
 */

public interface UseCase<T> {
  Response<T> execute();
}
