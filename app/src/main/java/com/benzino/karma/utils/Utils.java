package com.benzino.karma.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created on 23/2/16.
 *
 * @author Anas
 */
public class Utils {

    public static void changeTabsFont(TabLayout tabLayout, Context context) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "ProximaNova-Regular.otf");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(font);
                }
            }
        }
    }

    public static void changeTextViewFont(TextView textView, Context context){

        Typeface font = Typeface.createFromAsset(context.getAssets(), "ProximaNova-Regular.otf");
        textView.setTypeface(font);

    }

    public static void changeEditTextFont(EditText editText, Context context){

        Typeface font = Typeface.createFromAsset(context.getAssets(), "ProximaNova-Regular.otf");
        editText.setTypeface(font);

    }

    public static void changeButtonTextFont(Button button, Context context){
        Typeface font = Typeface.createFromAsset(context.getAssets(), "ProximaNova-Regular.otf");
        button.setTypeface(font);
    }
}
