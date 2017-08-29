package com.chucknorrisjokes.istanchev.chucknorrisjokes.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by istanchev on 8/29/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void replaceFragment(Fragment fragment, String tag, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment, tag);
        transaction.commit();
    }

    public void replaceFragmentWithArguments(Fragment fragment, String tag, int container, Bundle arguments) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(arguments);
        transaction.replace(container, fragment, tag);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initToolBar(Toolbar toolbar, TextView toolbarTitle, int color) {
        this.toolbarTitle = toolbarTitle;
        this.toolbar = toolbar;
        this.toolbar.setTitle("");
        setSupportActionBar(this.toolbar);
        changeStatusBarColor(color);
        showActionBarBackButton(true);
    }

    public void setToolbarVisibility(boolean shouldShow) {
        if (toolbar != null) {
            if (shouldShow) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    public void setToolbarTitle(String title) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }

    public void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    public void popBackFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void showActionBarBackButton(boolean shouldShow) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(shouldShow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(shouldShow);
        }
    }
}
