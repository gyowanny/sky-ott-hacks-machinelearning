package com.sky.hackday.utils;

import com.sky.hackday.io.FileHelper;
import com.sky.hackday.model.Trends;

/**
 * Created by MKH25 on 24/03/2017.
 */
public class GenericHelper {

    public static Trends getTrends(String title) {
    return FileHelper.trendsMap.get((title.toLowerCase().replace(" ","")));
    }
}
