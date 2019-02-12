package com.error.grrravity.mynews.Utils;

import com.error.grrravity.mynews.Models.APIReturns.APIReturnMostPopular;
import com.error.grrravity.mynews.Models.APIReturns.APIReturnSearch;
import com.error.grrravity.mynews.Models.APIReturns.APIReturnTopStories;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {

    // Top Stories API
    public static Observable<APIReturnTopStories> streamFetchTopStoriesArticles(){
        NYTServices nytService = NYTServices.retrofit.create(NYTServices.class);
        return nytService.fetchTopStoriesArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // Most Popular API
    public static Observable<APIReturnMostPopular> streamFetchMostPopularArticles(){
        NYTServices nytService = NYTServices.retrofit.create(NYTServices.class);
        return nytService.fetchMostPopularArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // Search API for categories
    public static Observable<APIReturnSearch> streamFetchCategoryArticles(String section){
        NYTServices nytService = NYTServices.retrofit.create(NYTServices.class);
        return nytService.fetchCategoryArticles(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // Search API for searches and notifications
    public static Observable<APIReturnSearch>
    streamFetchSearchArticles(String query, String category, String beginDate, String endDate){
        NYTServices nytService = NYTServices.retrofit.create(NYTServices.class);
        return nytService.fetchSearchArticles(query, category, beginDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}