package com.chucknorrisjokes.istanchev.chucknorrisjokes.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.activities.BaseActivity;
import com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments.RandomJokeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setToolbar(toolbar);
        changeStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));

        replaceFragment(new RandomJokeFragment(), R.id.main_activity_fragments_container);
    }
}