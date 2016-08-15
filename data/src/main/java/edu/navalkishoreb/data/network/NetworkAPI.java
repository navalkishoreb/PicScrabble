package edu.navalkishoreb.data.network;

import edu.navalkishoreb.data.model.Feed;

/**
 * Created by navalb on 15-08-2016.
 */

public interface NetworkAPI {
  String BASE_URL = "http://api.flickr.com";
  String FEED_API = "/services/feeds/photos_public.gne?&lang=en-us&format=json&nojsoncallback=1";

  Feed fetchFlickerFeed();
}
