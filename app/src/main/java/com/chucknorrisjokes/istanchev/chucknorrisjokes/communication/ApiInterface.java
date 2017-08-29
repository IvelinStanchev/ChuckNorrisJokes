package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by istanchev on 8/29/17.
 */

public interface ApiInterface {

    @GET(ApiConsts.END_POINT_GET_RANDOM_JOKE)
    Call<JokeResponseModel> getRandomJoke();

    @GET(ApiConsts.END_POINT_GET_CATEGORIES)
    Call<List<String>> getCategories();
}
