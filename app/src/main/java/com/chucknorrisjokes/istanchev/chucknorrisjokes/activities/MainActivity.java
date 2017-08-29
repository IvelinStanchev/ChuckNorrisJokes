package com.chucknorrisjokes.istanchev.chucknorrisjokes.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.callbacks.ChosenCategoryCallback;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments.RandomJokeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ChosenCategoryCallback {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.toolbar_general_title)
    TextView txtToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolBar(toolbar, txtToolBarTitle, ContextCompat.getColor(this, R.color.statusBarColor));

        replaceFragment(new RandomJokeFragment(), RandomJokeFragment.class.getSimpleName(), R.id.main_activity_fragments_container);
    }

    @Override
    public void onCategoryChosen(String chosenCategory) {
        //when choosing category, the random joke fragment should be notified
        RandomJokeFragment randomJokeFragment = (RandomJokeFragment) getSupportFragmentManager()
                .findFragmentByTag(RandomJokeFragment.class.getSimpleName());

        if (randomJokeFragment != null) {
            popBackFragment();
            randomJokeFragment.setCategory(chosenCategory);
        }
    }
}