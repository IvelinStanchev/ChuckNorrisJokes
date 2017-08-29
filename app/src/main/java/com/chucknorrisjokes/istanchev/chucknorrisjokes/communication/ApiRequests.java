package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import android.content.Context;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers.RestClientHelper;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers.ServiceHelper;

/**
 * Created by istanchev on 8/29/17.
 */

public class ApiRequests {

    public static void getRandomJoke(Context context, ServiceCallback<JokeResponseModel> callback) {
        ServiceHelper.getInstance(context).createAsynchronousRequest(RestClientHelper.getClient(context)
                .getRandomJoke(), callback);
    }
}
