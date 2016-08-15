package edu.navalkishoreb.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by navalb on 15-08-2016.
 */

class ServiceGenerator {

  private OkHttpClient okHttpClient;
  private RetrofitServices service;

  ServiceGenerator() {
    okHttpClient = createClient();
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(NetworkAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson));
    service = createService(RetrofitServices.class, builder);
  }

  private RetrofitServices createService(Class<RetrofitServices> serviceClass,
      Retrofit.Builder builder) {
    retrofit.Retrofit retrofit = builder.client(okHttpClient).build();
    return retrofit.create(serviceClass);
  }

  private OkHttpClient createClient() {
    final OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
    okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
    return okHttpClient;
  }

  public RetrofitServices getService() {
    return service;
  }
}
