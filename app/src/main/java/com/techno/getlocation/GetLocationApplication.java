package com.techno.getlocation;

import android.app.Application;

/**
 * Created by Arbaz.
 * Date: 8/5/18
 * Time: 5:40 PM
 */
public class GetLocationApplication extends Application {
    public static double latitude = 0.0;
    public static double longitude = 0.0;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
