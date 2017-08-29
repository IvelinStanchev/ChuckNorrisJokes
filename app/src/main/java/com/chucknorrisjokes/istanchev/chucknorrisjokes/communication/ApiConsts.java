package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.BuildConfig;

/**
 * Created by istanchev on 8/29/17.
 */

public class ApiConsts {

    public static final String BASE_URL = BuildConfig.baseUrl;//ends with "/"

    //Endpoints
    public static final String END_POINT_RANDOM_JOKE = BASE_URL + "jokes/random";
}
