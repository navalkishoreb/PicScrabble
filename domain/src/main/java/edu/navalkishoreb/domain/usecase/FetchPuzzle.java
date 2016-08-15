package edu.navalkishoreb.domain.usecase;

import edu.navalkishoreb.domain.PuzzleRepository;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.domain.model.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by navalb on 13-08-2016.
 */

class FetchPuzzle implements UseCase<Puzzle> {
  private PuzzleRepository puzzleRepo;

  FetchPuzzle(PuzzleRepository repository) {
    puzzleRepo = repository;
  }

  @Override public Response<Puzzle> execute() {
    return puzzleRepo.getPuzzle();
  }

  private Puzzle mockPuzzle() {
    List<Image> imageList = new ArrayList<>();
    imageList.add(new Image("https://farm8.staticflickr.com/7783/28950059535_ae9e4e0d49_m.jpg"));
    imageList.add(new Image("https://farm9.staticflickr.com/8119/28950059835_6fb136967b_m.jpg"));
    imageList.add(new Image("https://farm9.staticflickr.com/8396/28950060445_b65322315e_m.jpg"));

    imageList.add(new Image("https://farm9.staticflickr.com/8720/28333959603_c47df51821_m.jpg"));
    imageList.add(new Image("https://farm8.staticflickr.com/7475/28333963513_ab4268bb20_m.jpg"));
    imageList.add(new Image("https://farm9.staticflickr.com/8010/28333963923_1f989fd856_m.jpg"));

    imageList.add(new Image("https://farm9.staticflickr.com/8577/28664642740_d3783f12bc_m.jpg"));
    imageList.add(new Image("https://farm9.staticflickr.com/8770/28664643300_e6d0b88675_m.jpg"));
    imageList.add(new Image("https://farm9.staticflickr.com/8093/28664644520_7d5fd7ef9a_m.jpg"));
    return new Puzzle(imageList);
  }
}
