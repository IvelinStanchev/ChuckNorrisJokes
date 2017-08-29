package com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorStatusModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.response_models.JokeResponseModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.requests.JokeRequests;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.utils.ToastsUtil;
import com.github.johnpersano.supertoasts.library.Style;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by istanchev on 8/29/17.
 */

public class RandomJokeFragment extends BaseFragment {

    @BindView(R.id.txt_random_joke_joke)
    TextView txtJoke;
    @BindView(R.id.friends_requests_loading_container_progress_bar)
    RotateLoading loadingProgressBar;
    @BindView(R.id.txt_random_joke_previous)
    TextView txtPrevious;

    private List<JokeResponseModel> previousJokes;
    private int currentDisplayedJokePosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_random_joke, container, false);
        ButterKnife.bind(this, v);

        setToolbarVisibility(false);

        init();

        return v;
    }

    private void init() {
        if (previousJokes == null) {
            previousJokes = new ArrayList<>();
        }

        txtJoke.setMovementMethod(new ScrollingMovementMethod());

        fetchRandomJoke();
    }

    @OnClick({ R.id.txt_random_joke_previous, R.id.txt_random_joke_next })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_random_joke_previous:
                clickedPrevious();
                break;
            case R.id.txt_random_joke_next:
                clickedNext();
                break;
        }
    }

    private void clickedPrevious() {
        if (currentDisplayedJokePosition > 0) {
            currentDisplayedJokePosition--;
            if (currentDisplayedJokePosition == 0) {
                txtPrevious.setVisibility(View.GONE);
            }
            updateJokeText(previousJokes.get(currentDisplayedJokePosition).getJokeText());
        }
    }

    private void clickedNext() {
        if (previousJokes.size() <= 1) {
            fetchRandomJoke();
        } else {
            if (currentDisplayedJokePosition < previousJokes.size() - 1) {
                currentDisplayedJokePosition++;
                if (currentDisplayedJokePosition >= 1) {
                    txtPrevious.setVisibility(View.VISIBLE);
                }
                updateJokeText(previousJokes.get(currentDisplayedJokePosition).getJokeText());
            } else {
                fetchRandomJoke();
            }
        }
    }

    private void updateJokeText(String joke) {
        txtJoke.setText("“" + joke + "“");
    }

    private void fetchRandomJoke() {
        loadingProgressBar.start();
        new JokeRequests(getContext()).getRandomJoke(new ServiceCallback<JokeResponseModel>() {
            @Override
            public void success(JokeResponseModel responseModel, String rawJsonResponse,
                                Call<JokeResponseModel> callRetrofit, Response<JokeResponseModel> responseRetrofit) {
                if (isAdded() && responseModel != null && !TextUtils.isEmpty(responseModel.getJokeText())) {
                    loadingProgressBar.stop();

                    previousJokes.add(responseModel);
                    currentDisplayedJokePosition = previousJokes.size() - 1;
                    if (previousJokes.size() > 1) {
                        txtPrevious.setVisibility(View.VISIBLE);
                    }

                    updateJokeText(responseModel.getJokeText());
                }
            }

            @Override
            public void failure(ErrorStatusModel error) {
                if (isAdded()) {
                    loadingProgressBar.stop();

                    ToastsUtil.showErrorToast(getContext(), error.getErrorMessage(), Style.DURATION_VERY_LONG);
                }
            }
        });
    }
}
