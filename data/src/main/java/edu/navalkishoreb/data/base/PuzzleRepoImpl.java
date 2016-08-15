package edu.navalkishoreb.data.base;

import edu.navalkishoreb.data.model.Feed;
import edu.navalkishoreb.data.model.Items;
import edu.navalkishoreb.data.network.NetworkAPI;
import edu.navalkishoreb.data.network.NetworkManager;
import edu.navalkishoreb.domain.PuzzleRepository;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.domain.model.PuzzleFeed;
import edu.navalkishoreb.domain.model.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by navalb on 13-08-2016.
 */

class PuzzleRepoImpl implements PuzzleRepository {

  private NetworkAPI networkAPI;

  PuzzleRepoImpl() {
    networkAPI = NetworkManager.getInstance();
  }

  @Override public Response<Puzzle> getPuzzle() {
    Feed feed = networkAPI.fetchFlickerFeed();
    Response<Puzzle> puzzleFeed = new PuzzleFeed();
    transformFeedToPuzzle(feed, puzzleFeed);
    return puzzleFeed;
  }

  private void transformFeedToPuzzle(Feed feed, Response<Puzzle> puzzleFeed) {
    if (feed.isSuccessful()) {
      List<Image> imageList = new ArrayList<>(Puzzle.GRID_SIZE);
      for (Items items : feed.getData().getItems()) {
        Image image = new Image(items.getMedia().getM());
        imageList.add(image);
      }
      Puzzle puzzle = new Puzzle(imageList);
      puzzleFeed.setData(puzzle);
    } else {
      puzzleFeed.setErrorMessage(feed.getErrorMessage());
    }
  }
}
