package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.requests;

import android.content.Context;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.ApiRequests;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorStatusModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by istanchev on 8/29/17.
 */

public class JokeRequests {

    private Context context;

    public JokeRequests(Context context) {
        this.context = context;
    }

    public void getRandomJoke(final ServiceCallback<JokeResponseModel> responseCallback){

        ApiRequests.getRandomJoke(context, new ServiceCallback<JokeResponseModel>() {
            @Override
            public void success(JokeResponseModel responseModel, String rawJsonResponse,
                                Call<JokeResponseModel> callRetrofit, Response<JokeResponseModel> responseRetrofit) {
                responseCallback.success(responseModel, rawJsonResponse, callRetrofit, responseRetrofit);
            }

            @Override
            public void failure(ErrorStatusModel error) {
                responseCallback.failure(error);
            }
        });
    }

    public void getCategories(final ServiceCallback<List<String>> responseCallback){

        ApiRequests.getCategories(context, new ServiceCallback<List<String>>() {
            @Override
            public void success(List<String> responseModel, String rawJsonResponse,
                                Call<List<String>> callRetrofit, Response<List<String>> responseRetrofit) {
                responseCallback.success(responseModel, rawJsonResponse, callRetrofit, responseRetrofit);
            }

            @Override
            public void failure(ErrorStatusModel error) {
                responseCallback.failure(error);
            }
        });
    }
}
