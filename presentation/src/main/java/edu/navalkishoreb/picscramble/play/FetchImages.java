package edu.navalkishoreb.picscramble.play;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.squareup.picasso.Picasso;
import edu.navalkishoreb.data.base.PuzzleRepoFactory;
import edu.navalkishoreb.domain.model.Image;
import edu.navalkishoreb.domain.model.Puzzle;
import edu.navalkishoreb.domain.model.Response;
import edu.navalkishoreb.domain.usecase.Requirement;
import edu.navalkishoreb.domain.usecase.UseCase;
import edu.navalkishoreb.domain.usecase.UseCaseFactory;

/**
 * Created by navalb on 13-08-2016.
 */

public class FetchImages extends Fragment {

  public static Fragment newInstance() {
    return new FetchImages();
  }

  void clearData() {
    FetchImages.this.response = null;
  }

  interface Callback {
    void onResult(Puzzle puzzle);

    void onError(String errorMessage);
  }

  private Response<Puzzle> response;
  private UseCase<Puzzle> fetchPuzzle;
  private Callback callback;
  private AsyncTask<Void, Void, Response> asyncTask;

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Callback)) {
      throw new IllegalArgumentException("Parent activity need to implement Callback interface");
    }
    callback = (Callback) activity;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
    fetchPuzzle = UseCaseFactory.get(Requirement.FETCH_PUZZLE, PuzzleRepoFactory.get());
  }

  void fetchData() {
    if (response == null && asyncTask == null) {
      Log.d("Puzzle", "execute asynctask");
      asyncTask = new FetchImagesOnThread();
      asyncTask.execute();
    } else if (asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
      Log.d("Puzzle", "waiting for async task to finish");
    } else {
      Log.d("Puzzle", "sending callback");
      sendCallback();
    }
  }

  boolean isSuccessful() {
    return response != null && response.isSuccessful();
  }

  Puzzle getPuzzle() {
    if (isSuccessful()) {
      return response.getData();
    }
    return null;
  }

  private void setResponse(Response response) {
    if (response == null) {
      throw new IllegalArgumentException("Response can not be NULL");
    }
    FetchImages.this.response = response;
  }

  private void sendCallback() {
    if (isAdded()) {
      if (response.isSuccessful()) {
        if (!(response.getData() instanceof Puzzle)) {
          throw new IllegalArgumentException("Need puzzle data type");
        }
        callback.onResult(response.getData());
      } else {
        callback.onError(response.getErrorMessage());
      }
    }
  }

  private class FetchImagesOnThread extends AsyncTask<Void, Void, Response> {

    @Override protected Response doInBackground(Void... voids) {
      Log.d("Puzzle", "response started");
      Response<Puzzle> response = fetchPuzzle.execute();
      for (Image image : response.getData().getImageGrid()) {
        Picasso.with(getContext()).load(image.getUrl()).priority(Picasso.Priority.HIGH).fetch();
      }
      Log.d("Puzzle", "response received");
      return response;
    }

    @Override protected void onPostExecute(Response response) {
      super.onPostExecute(response);
      Log.d("Puzzle", "onPostExecute");
      asyncTask = null;
      setResponse(response);
      sendCallback();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Log.d("Puzzle", "headless fragment destroyed");
  }

  @Override public void onDetach() {
    super.onDetach();
    Log.d("Puzzle", "headless fragment detached");
  }
}
