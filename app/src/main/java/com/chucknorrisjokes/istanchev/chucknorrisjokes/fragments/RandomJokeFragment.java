package com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by istanchev on 8/29/17.
 */

public class RandomJokeFragment extends BaseFragment {

    @BindView(R.id.txt_random_joke_joke)
    TextView txtJoke;

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
        txtJoke.setMovementMethod(new ScrollingMovementMethod());
    }
}
