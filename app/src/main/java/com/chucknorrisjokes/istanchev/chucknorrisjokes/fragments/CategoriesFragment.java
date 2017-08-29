package com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.adapters.recycler.CategoriesAdapter;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.callbacks.ChosenCategoryCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.api_models.error_models.ErrorStatusModel;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.callbacks.ServiceCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.requests.JokeRequests;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.utils.ToastsUtil;
import com.github.johnpersano.supertoasts.library.Style;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by istanchev on 8/29/17.
 */

public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.categories_recycler)
    RecyclerView recycler;
    @BindView(R.id.categories_loading_container_progress_bar)
    RotateLoading loadingProgressBar;

    private ChosenCategoryCallback chosenCategoryCallback;
    private CategoriesAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            chosenCategoryCallback = (ChosenCategoryCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getBaseActivity().toString() + " must implement OnChosenUserCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, v);

        setToolbarVisibility(true);
        setToolbarTitle(getString(R.string.categories));

        init();

        return v;
    }

    private void init() {
        fetchCategories();
    }

    private void initAdapter(List<String> data) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CategoriesAdapter(getContext(), data, chosenCategoryCallback);
        recycler.setAdapter(adapter);
    }

    private void fetchCategories() {
        loadingProgressBar.start();
        new JokeRequests(getContext()).getCategories(new ServiceCallback<List<String>>() {
            @Override
            public void success(List<String> responseModel, String rawJsonResponse,
                                Call<List<String>> callRetrofit, Response<List<String>> responseRetrofit) {
                if (isAdded()) {
                    loadingProgressBar.stop();

                    initAdapter(responseModel);
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
