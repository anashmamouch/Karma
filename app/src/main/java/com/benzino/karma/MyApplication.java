package com.benzino.karma;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;

/**
 * Created on 23/2/16.
 *
 * @author Anas
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);
    }
}
