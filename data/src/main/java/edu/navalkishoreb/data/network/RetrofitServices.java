package edu.navalkishoreb.data.network;

import edu.navalkishoreb.data.model.FlickerFeed;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by navalb on 15-08-2016.
 */

interface RetrofitServices {

  @GET(NetworkAPI.FEED_API) Call<FlickerFeed> fetchFeed();
}
