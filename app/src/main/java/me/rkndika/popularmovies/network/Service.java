package me.rkndika.popularmovies.network;

import me.rkndika.popularmovies.model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    // get popular movie from API with api_key query
    @GET(Config.POPULAR)
    Call<Movies> getPopularMovie(@Query("api_key") String apiKey);

    // get top rated movie from API with api_key query
    @GET(Config.TOP_RATED)
    Call<Movies> getTopRatedMovie(@Query("api_key") String apiKey);
}
