package com.error.grrravity.mynews.Utils;

import com.error.grrravity.mynews.Models.APIReturns.APIReturnMostPopular;
import com.error.grrravity.mynews.Models.APIReturns.APIReturnSearch;
import com.error.grrravity.mynews.Models.APIReturns.APIReturnTopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTServices {

    String API_KEY = "bab032fe-89c3-4c51-b84e-7ad0c350cca8";

    // Top Stories
    @GET("topstories/v2/home.json?api-key="+API_KEY)
    Observable<APIReturnTopStories> fetchTopStoriesArticles();

    // Most Popular
    @GET("mostpopular/v2/viewed/7.json?api-key="+API_KEY)
    Observable<APIReturnMostPopular> fetchMostPopularArticles();

    // Categories
    @GET("search/v2/articlesearch.json?sort=newest&fl=web_url,headline,multimedia,news_desk,pub_date&api-key="+API_KEY)
    Observable<APIReturnSearch> fetchCategoryArticles(@Query("fq") String section);

    // Search and Notifications activity
    @GET("search/v2/articlesearch.json?sort=newest&fl=web_url,headline,multimedia,news_desk,pub_date&api-key="+API_KEY)
    Observable<APIReturnSearch> fetchSearchArticles(@Query("q") String query,
                                                    @Query("fq") String section,
                                                    @Query("begin_date") String beginDate,
                                                    @Query("ebd_date") String endDate);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

}