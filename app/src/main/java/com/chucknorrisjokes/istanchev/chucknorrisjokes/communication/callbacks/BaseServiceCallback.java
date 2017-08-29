package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorStatusModel;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by istanchev on 8/29/17.
 */

public interface BaseServiceCallback<T> {

    // passing the model, rawJson, call and response
    void success(T responseModel, String rawJsonResponse, Call<T> callRetrofit, Response<T> responseRetrofit);

    // passing error model
    void failure(ErrorStatusModel error);

    void connectionFailure();
}