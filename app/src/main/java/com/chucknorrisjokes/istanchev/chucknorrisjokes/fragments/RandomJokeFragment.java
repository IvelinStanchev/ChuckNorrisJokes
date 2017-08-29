package com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    @BindView(R.id.random_joke_loading_container_progress_bar)
    RotateLoading loadingProgressBar;
    @BindView(R.id.txt_random_joke_previous)
    TextView txtPrevious;
    @BindView(R.id.rl_random_joke_chosen_category)
    RelativeLayout categoryContainer;
    @BindView(R.id.txt_random_joke_category)
    TextView txtCategory;
    @BindView(R.id.txt_random_joke_joke_category)
    TextView txtJokeCategory;

    private List<JokeResponseModel> previousJokes;
    private int currentDisplayedJokePosition = -1;
    private String chosenCategory;

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
        updateNextAndPreviousButtons();

        txtJoke.setMovementMethod(new ScrollingMovementMethod());

        if (previousJokes == null) {
            previousJokes = new ArrayList<>();
            fetchRandomJoke();
        } else if (currentDisplayedJokePosition > -1) {
            updateJokeText(previousJokes.get(currentDisplayedJokePosition));
        }

        updateCategoryView();
    }

    @OnClick({ R.id.txt_random_joke_previous, R.id.txt_random_joke_next,
                R.id.txt_random_joke_categories, R.id.txt_random_joke_category_remove })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_random_joke_previous:
                clickedPrevious();
                break;
            case R.id.txt_random_joke_next:
                clickedNext();
                break;
            case R.id.txt_random_joke_categories:
                showFragmentAndAddToBackStack(new CategoriesFragment(), "CategoriesFragment", R.id.main_activity_fragments_container);
                break;
            case R.id.txt_random_joke_category_remove:
                chosenCategory = null;
                updateCategoryView();
                break;
        }
    }

    private void updateCategoryView() {
        if (!TextUtils.isEmpty(chosenCategory)) {
            txtCategory.setText(getString(R.string.category) + ": " + chosenCategory);
            categoryContainer.setVisibility(View.VISIBLE);
        } else {
            categoryContainer.setVisibility(View.GONE);
        }
    }

    private void updateNextAndPreviousButtons() {
        if (previousJokes == null || previousJokes.size() <= 1) {
            txtPrevious.setVisibility(View.GONE);
            return;
        }
        if (currentDisplayedJokePosition == 0 && previousJokes.size() > 1) {
            txtPrevious.setVisibility(View.GONE);
            return;
        }
        if (currentDisplayedJokePosition >= 1) {
            txtPrevious.setVisibility(View.VISIBLE);
            return;
        }
    }

    private void clickedPrevious() {
        if (currentDisplayedJokePosition > 0) {
            currentDisplayedJokePosition--;
            updateNextAndPreviousButtons();
            updateJokeText(previousJokes.get(currentDisplayedJokePosition));
        }
    }

    private void clickedNext() {
        if (previousJokes.size() <= 1) {
            fetchRandomJoke();
        } else {
            if (currentDisplayedJokePosition < previousJokes.size() - 1) {
                currentDisplayedJokePosition++;
                updateNextAndPreviousButtons();
                updateJokeText(previousJokes.get(currentDisplayedJokePosition));
            } else {
                fetchRandomJoke();
            }
        }
    }

    private void updateJokeText(JokeResponseModel joke) {
        if (joke != null && !TextUtils.isEmpty(joke.getJokeText())) {
            if (!TextUtils.isEmpty(joke.getCategory())) {
                txtJokeCategory.setText(getString(R.string.category) + ": " + joke.getCategory());
                txtJokeCategory.setVisibility(View.VISIBLE);
            } else {
                txtJokeCategory.setVisibility(View.GONE);
            }

            txtJoke.setText("“" + joke.getJokeText() + "“");
        }
    }

    private void fetchRandomJoke() {
        loadingProgressBar.start();
        new JokeRequests(getContext()).getRandomJoke(chosenCategory, new ServiceCallback<JokeResponseModel>() {
            @Override
            public void success(JokeResponseModel responseModel, String rawJsonResponse,
                                Call<JokeResponseModel> callRetrofit, Response<JokeResponseModel> responseRetrofit) {
                if (isAdded() && responseModel != null && !TextUtils.isEmpty(responseModel.getJokeText())) {
                    loadingProgressBar.stop();

                    previousJokes.add(responseModel);
                    currentDisplayedJokePosition = previousJokes.size() - 1;
                    updateNextAndPreviousButtons();

                    updateJokeText(responseModel);
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

    public void setCategory(String category) {
        this.chosenCategory = category;
        updateCategoryView();
    }
}
