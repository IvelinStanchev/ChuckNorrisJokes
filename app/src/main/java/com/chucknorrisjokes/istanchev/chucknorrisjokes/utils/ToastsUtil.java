package com.chucknorrisjokes.istanchev.chucknorrisjokes.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chucknorrisjokes.istanchev.chucknorrisjokes.R;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;

/**
 * Created by istanchev on 8/29/17.
 */

public class ToastsUtil {

    public static void showErrorToast(Context context, String error, int duration){
        if (context != null && !TextUtils.isEmpty(error)) {
            SuperToast superToast = new SuperToast(context);
            superToast.setAnimations(Style.ANIMATIONS_SCALE);
            superToast.setDuration(duration);
            superToast.setColor(ContextCompat.getColor(context, R.color.toastErrorColor));
            superToast.setTextSize(Style.TEXTSIZE_MEDIUM);
            superToast.setTextColor(ContextCompat.getColor(context, R.color.white));
            superToast.setText(error);
            superToast.show();
        }
    }

    public static void showSuccessMessageToast(Context context, String successMessage, int duration){
        if (context != null && !TextUtils.isEmpty(successMessage)) {
            SuperToast superToast = new SuperToast(context);
            superToast.setAnimations(Style.ANIMATIONS_SCALE);
            superToast.setDuration(duration);
            superToast.setColor(ContextCompat.getColor(context, R.color.toastSuccessColor));
            superToast.setTextSize(Style.TEXTSIZE_MEDIUM);
            superToast.setTextColor(ContextCompat.getColor(context, R.color.white));
            superToast.setText(successMessage);
            superToast.show();
        }
    }
}
