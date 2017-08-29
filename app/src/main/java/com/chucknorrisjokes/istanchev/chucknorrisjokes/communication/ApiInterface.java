package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by istanchev on 8/29/17.
 */

public interface ApiInterface {

    @GET(ApiConsts.END_POINT_RANDOM_JOKE)
    Call<JokeResponseModel> getRandomJoke();
}
