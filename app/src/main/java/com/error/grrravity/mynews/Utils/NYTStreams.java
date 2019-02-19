package com.error.grrravity.mynews.Utils;

import com.error.grrravity.mynews.Models.APIReturns.APIArticles;
import com.error.grrravity.mynews.Models.APIReturns.APISearch;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {

    // getting articles
    public static Observable<APIArticles> streamFetchArticles (String section, String api_key, boolean isMostPopular){
        NYTServices nytService = NYTServices.retrofit.get().create(NYTServices.class);
        return nytService.getBySection(getPath(section, isMostPopular), api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    private static String getPath(String section, boolean isMostPopular) {
        if (isMostPopular){
            return "mostpopular/v2/mostviewed/" + section + "/1";
        }
        else {
            return "topstories/v2/" + section;
        }

    }

    // public static Observable<APIArticles>
    // streamFetchMostPopularArticles(String section, String api_key){
    //     NYTServices nytService = NYTServices.retrofit.get().create(NYTServices.class);
    //     return nytService.getBySectionMp(section, api_key)
    //             .subscribeOn(Schedulers.io())
    //             .observeOn(AndroidSchedulers.mainThread())
    //             .timeout(10, TimeUnit.SECONDS);
    // }

    // getting searched articles
    public static Observable<APISearch> streamFetchSearchArticles
    (String api_key, String search, List<String> category, String beginDate, String endDate){
        NYTServices nytService = NYTServices.retrofit.get().create(NYTServices.class);
        return nytService.getSearch(api_key, search, category, beginDate, endDate, "interest")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}