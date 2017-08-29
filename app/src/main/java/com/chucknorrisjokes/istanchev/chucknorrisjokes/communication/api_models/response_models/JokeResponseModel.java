package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by istanchev on 8/29/17.
 */

public class JokeResponseModel {

    @SerializedName("value")
    private String jokeText;

    public JokeResponseModel(String jokeText) {
        this.jokeText = jokeText;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }
}
