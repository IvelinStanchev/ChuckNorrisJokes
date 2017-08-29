package com.chucknorrisjokes.istanchev.chucknorrisjokes;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by istanchev on 8/29/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLibs();
    }

    private void initLibs() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ComicSansMSFont.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
