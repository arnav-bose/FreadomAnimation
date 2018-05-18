package com.arnav.freadomanimation;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by Arnav on 26/04/2018 at 12:49.
 */
public class Utils {

    public static int[] getScreenWidthHeight(Context context){
        if(context instanceof Activity){
            Activity activity = (Activity) context;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            return new int[] {width, height};
        }
        return new int[] {-1, -1};
    }

    public static int randomNumber(int min, int max){
        return new Random().nextInt((max - min) + 1) + min;
    }

}
