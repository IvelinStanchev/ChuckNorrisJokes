package com.chucknorrisjokes.istanchev.chucknorrisjokes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.activities.BaseActivity;

/**
 * Created by istanchev on 8/29/17.
 */

public class BaseFragment extends Fragment {

    private BaseActivity mBaseActivity;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mBaseActivity = (BaseActivity) context;
            this.context = context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public BaseActivity getBaseActivity() {
        return this.mBaseActivity;
    }

    public Context getContext() {
        return this.context;
    }

    public void setToolbarVisibility(boolean shouldShow) {
        getBaseActivity().setToolbarVisibility(shouldShow);
    }

    public void setToolbarTitle(String title) {
        getBaseActivity().setTitle(title);
    }
}
