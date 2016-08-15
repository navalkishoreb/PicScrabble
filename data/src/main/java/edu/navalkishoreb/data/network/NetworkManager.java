package edu.navalkishoreb.data.network;

import android.net.ConnectivityManager;
import edu.navalkishoreb.data.model.Feed;
import edu.navalkishoreb.data.model.FlickerFeed;
import java.io.IOException;
import retrofit.Response;

/**
 * Created by navalb on 15-08-2016.
 */

public class NetworkManager implements NetworkAPI {

  private static final String TAG = NetworkManager.class.getSimpleName();
  private static NetworkAPI networkManager;
  private RetrofitServices retrofitServices;
  private ConnectivityManager connectivityManager;

  public static NetworkAPI getInstance() {
    if (networkManager == null) {
      synchronized (NetworkManager.class) {
        if (networkManager == null) {
          networkManager = new NetworkManager();
        }
      }
    }
    return networkManager;
  }

  private NetworkManager() {
    this.retrofitServices = new ServiceGenerator().getService();
  }

  @Override public Feed fetchFlickerFeed() {
    Feed feed = new Feed();
    try {
      Response<FlickerFeed> response = retrofitServices.fetchFeed().execute();
      if (response.isSuccess()) {
        feed.setData(response.body());
        feed.setErrorMessage(null);
      } else {
        feed.setData(null);
        feed.setErrorMessage(response.message());
      }
    } catch (IOException e) {
      e.printStackTrace();
      feed.setData(null);
      feed.setErrorMessage(e.getMessage());
    }
    return feed;
  }
}
