package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by istanchev on 8/29/17.
 */

public interface ApiInterface {

    @GET
    Call<JokeResponseModel> getRandomJoke(@Url String endpoint);

    @GET(ApiConsts.END_POINT_GET_CATEGORIES)
    Call<List<String>> getCategories();
}
