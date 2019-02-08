package com.error.grrravity.mynews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.error.grrravity.mynews.Models.APIReturns.ApiReturnMostPopuplar;
import com.error.grrravity.mynews.Models.APIReturns.ApiReturnSearch;
import com.error.grrravity.mynews.Models.APIReturns.ApiReturnTopStories;
import com.error.grrravity.mynews.Utils.Helper;
import com.error.grrravity.mynews.Utils.NYTimesStreams;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    Context context = InstrumentationRegistry.getTargetContext();

    // Top Stories test
    @Test
    public void fetchTopStoriesArticlesTest() throws Exception{
        Observable<ApiReturnTopStories> observableArticles =
                NYTimesStreams.streamFetchTopStoriesArticles();

        TestObserver<ApiReturnTopStories> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        ApiReturnTopStories articlesFetched = testObserver.values().get(0);

        // Verify if there's at least 1 article
        assertNotEquals(articlesFetched.getResults().size(),0);

        ApiReturnTopStories.Result article = articlesFetched.getResults().get(0);
        // Verify if article has Title, date, section and a URL.
        assertNotNull ("Article has title", article.getTitle());
        assertNotNull ("Article has date", article.getPublishedDate());
        assertNotNull ("Article has section", article.getSection());
        assertNotNull ("Article has URL", article.getUrl());
    }

    // Most Popular test)
    @Test
    public void fetchMostPopularArticlesTest() throws Exception{
        Observable<ApiReturnMostPopuplar> observableArticles =
                NYTimesStreams.streamFetchMostPopularArticles();

        TestObserver<ApiReturnMostPopuplar> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        ApiReturnMostPopuplar articlesFetched = testObserver.values().get(0);

        // Verify if there's at least 1 article
        assertNotEquals(articlesFetched.getResults().size(),0);

        ApiReturnMostPopuplar.Result article = articlesFetched.getResults().get(0);
        // Verify if article has Title, date, section and a URL.
        assertNotNull ("Article has title", article.getTitle());
        assertNotNull ("Article has date", article.getPublishedDate());
        assertNotNull ("Article has section", article.getSection());
        assertNotNull ("Article has URL", article.getUrl());
    }

    // Search for categories test (tested with Arts category)
    @Test
    public void fetchCategoryTest() throws Exception{
        Observable<ApiReturnSearch> observableArticles =
                NYTimesStreams.streamFetchCategoryArticles("news_desk:(\"Arts\")");

        TestObserver<ApiReturnSearch> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        ApiReturnSearch articlesFetched = testObserver.values().get(0);

        // Verify if there's at least 1 article
        assertNotEquals(articlesFetched.getResponse().getDocs().size(),0);

        ApiReturnSearch.Doc article = articlesFetched.getResponse().getDocs().get(0);
        // Verify if article has Title, date and a URL.
        assertNotNull ("Article has title", article.getHeadline().getMain());
        assertNotNull ("Article has date", article.getPubDate());
        assertNotNull ("Article has URL", article.getWebUrl());
        // Verify if the article is in the good section
        assertEquals("Arts", article.getNewsDesk());
    }

    @Test
    public void fetchSearchTest() throws Exception{
        Observable<ApiReturnSearch> observableArticles =
                NYTimesStreams.streamFetchSearchArticles("trump"
                        ,"news_desk:(\"Politics\")", null, null);

        TestObserver<ApiReturnSearch> testObserver = new TestObserver<>();

        observableArticles.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        ApiReturnSearch articlesFetched = testObserver.values().get(0);

        // Verify if there's at least 1 article
        assertNotEquals(articlesFetched.getResponse().getDocs().size(),0);

        ApiReturnSearch.Doc article = articlesFetched.getResponse().getDocs().get(0);
        // Verify if article has Title, date and a URL.
        assertNotNull ("Article has title", article.getHeadline().getMain());
        assertNotNull ("Article has date", article.getPubDate());
        assertNotNull ("Article has URL", article.getWebUrl());
        assertEquals("Politics", article.getNewsDesk());
        // Verify if the article is in the good section
    }

    // Put date in DD/MM/YYYY and YYYYMMDD (for NYT API calls)
    @Test
    public void dateFormatTest(){

        TextView spinner = new TextView(context);
        int year = 2019;
        int month = 1;
        int day = 1;
        String date;

        date = Helper.processDate(year, month, day, spinner);

        assertEquals("01/02/2019", spinner.getText());
        assertEquals("20190201", date);
    }

    // Test if the begin date is anterior to the end date when using "Search" option
    @Test
    public void dateComparisonTest(){
        // Needed for the Toast
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                String beginDate = "20190201";
                String endDate = "20190301";

                assertTrue(Helper.datesAreValid(context, beginDate, endDate));

                beginDate = "20190201";
                endDate = "20190101";

                assertFalse(Helper.datesAreValid(context, beginDate, endDate));
            }
        });
    }

    // Test for the form validation
    @Test
    public void parametersValidationTest(){
        // Needed for the Toast
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText searchField = new EditText(context);
                CheckBox cbArts = new CheckBox(context);
                CheckBox cbBusiness = new CheckBox(context);
                CheckBox cbPolitics = new CheckBox(context);
                CheckBox cbTravel = new CheckBox(context);

                // No text and no checkbox
                assertFalse(Helper.parametersAreValid(context, searchField,
                        cbArts, cbBusiness, cbPolitics, cbTravel));

                // Text but no checkbox
                searchField.setText("Macron");
                assertFalse(Helper.parametersAreValid(context, searchField,
                        cbArts, cbBusiness, cbPolitics, cbTravel));

                // Text and checkbox
                cbArts.setChecked(true);
                assertTrue(Helper.parametersAreValid(context, searchField,
                        cbArts, cbBusiness, cbPolitics, cbTravel));

                // Checkbox but no text
                searchField.setText("");
                assertFalse(Helper.parametersAreValid(context, searchField,
                        cbArts, cbBusiness, cbPolitics, cbTravel));
            }
        });
    }
}