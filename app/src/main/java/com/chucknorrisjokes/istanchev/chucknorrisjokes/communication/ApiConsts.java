package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import android.text.TextUtils;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.BuildConfig;

/**
 * Created by istanchev on 8/29/17.
 */

public class ApiConsts {

    public static final String BASE_URL = BuildConfig.baseUrl;//ends with "/"

    //Endpoints
    private static final String END_POINT_GET_RANDOM_JOKE = BASE_URL + "jokes/random";
    public static final String END_POINT_GET_CATEGORIES = BASE_URL + "jokes/categories";

    public static String getRandomJokeEndpoint(String category) {
        String endpoint = END_POINT_GET_RANDOM_JOKE;

        if (!TextUtils.isEmpty(category)) {
            endpoint += "?category=" + category;
        }

        return endpoint;
    }
}
