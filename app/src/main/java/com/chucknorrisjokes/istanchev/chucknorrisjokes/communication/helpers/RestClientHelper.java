package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers;

import android.content.Context;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.ApiConsts;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.ApiInterface;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.constants.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by istanchev on 8/29/17.
 */

public class RestClientHelper {
    private static ApiInterface apiInterface;
    private static Retrofit retrofitClient;

    public static ApiInterface getClient(final Context context) {
        //create the http client
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.API_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        //Request customization: add request headers
                        Request.Builder requestBuilder = chain.request().newBuilder()
                                .method(chain.request().method(), chain.request().body())
                                .headers(HeadersCustomizeHelper.getAppHeaders());

                        return chain.proceed(requestBuilder.build());

                    }
            });

        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        OkHttpClient client = okHttpClientBuilder.build();

        // create the retrofit client
        retrofitClient = new Retrofit.Builder()
                .baseUrl(ApiConsts.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofitClient.create(ApiInterface.class);

        return apiInterface;
    }

    public static Retrofit getRetrofit() {
        return retrofitClient;
    }
}
