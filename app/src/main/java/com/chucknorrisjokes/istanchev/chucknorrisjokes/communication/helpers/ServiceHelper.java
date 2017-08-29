package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers;

import android.content.Context;
import android.text.TextUtils;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorResponseModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorStatusModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.utils.AppUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by istanchev on 8/29/17.
 */

public class ServiceHelper {

    private static ServiceHelper sInstance;
    private Context context;

    public ServiceHelper(Context context) {
        this.context = context;
    }

    public static synchronized ServiceHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ServiceHelper(context);
        }

        return sInstance;
    }

    public <T> void createAsynchronousRequest(Call<T> retrofitCall, final ServiceCallback<T> responseCallback) {
        final String serverError = context.getString(R.string.serverErrorGeneral);

        //First check for Internet connection
        if (!AppUtils.isNetworkAvailable(context)) {
            responseCallback.connectionFailure();
            responseCallback.failure(createError(context.getString(R.string.internetConnectionProblem), 0));
        } else {
            // make asynchronous request using enqueue
            retrofitCall.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    // check for success
                    if (response.isSuccessful()) {
                        responseCallback.success(response.body(), new Gson().toJson(response.body()), call, response);
                    } else {
                        // parse the error id if it is possible
                        Converter<ResponseBody, ErrorResponseModel> converter =
                                RestClientHelper.getRetrofit().responseBodyConverter(ErrorResponseModel.class, new Annotation[0]);
                        ErrorResponseModel error = null;
                        int responseCode = response.code();
                        try {
                            error = converter.convert(response.errorBody());

                            if (error != null && !TextUtils.isEmpty(error.getServerErrorDescription())) {
                                responseCallback.failure(createError(error.getServerErrorDescription(), responseCode));
                            } else {
                                responseCallback.failure(createError(serverError, responseCode));
                            }

                        } catch (IOException | JsonSyntaxException e) {
                            responseCallback.failure(createError(serverError, responseCode));
                        }
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    // set the error message of failure
                    responseCallback.failure(createError(serverError, 0));
                }
            });
        }
    }

    /**
     * Helper for creating the final Error Status model
     *
     * @param msg
     * @param code
     * @return
     */
    private static ErrorStatusModel createError(String msg, Object code) {
        int status = 0;

        try {
            if (code instanceof Integer)
                status = (int) code;
            else if (code instanceof String) {
                status = Integer.parseInt((String) code);
            }
        } catch (NullPointerException | NumberFormatException exception) {
            status = 0;
        }

        return new ErrorStatusModel(msg, status);
    }
}
