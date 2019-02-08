package com.error.grrravity.mynews;

import android.widget.TextView;

import com.error.grrravity.mynews.Utils.Helper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    // Put the date in DD/MM/YY format
    @Test
    public void dateFormattingTest(){
        String date = "2019-02-07T16:56:00-05:00";
        String result = Helper.formatDate(date);

        assertEquals("07/02/19", result);
    }

}