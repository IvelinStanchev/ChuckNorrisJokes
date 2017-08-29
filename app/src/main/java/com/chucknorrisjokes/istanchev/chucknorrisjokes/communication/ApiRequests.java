package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication;

import android.content.Context;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers.RestClientHelper;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers.ServiceHelper;

import java.util.List;

/**
 * Created by istanchev on 8/29/17.
 */

public class ApiRequests {

    public static void getRandomJoke(Context context, ServiceCallback<JokeResponseModel> callback) {
        ServiceHelper.getInstance(context).createAsynchronousRequest(RestClientHelper.getClient(context)
                .getRandomJoke(), callback);
    }

    public static void getCategories(Context context, ServiceCallback<List<String>> callback) {
        ServiceHelper.getInstance(context).createAsynchronousRequest(RestClientHelper.getClient(context)
                .getCategories(), callback);
    }
}
