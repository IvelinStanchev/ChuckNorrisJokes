package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by istanchev on 8/29/17.
 */

public class JokeResponseModel {

    @SerializedName("value")
    private String jokeText;
    @SerializedName("category")
    private List<String> categories;

    public JokeResponseModel(String jokeText) {
        this.jokeText = jokeText;
    }

    public JokeResponseModel(String jokeText, List<String> categories) {
        this.jokeText = jokeText;
        this.categories = categories;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCategory() {
        if (this.categories != null && !this.categories.isEmpty()) {
            return this.categories.get(0);
        }

        return null;
    }
}
