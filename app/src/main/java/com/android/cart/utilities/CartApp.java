
package com.android.cart.utilities;

import android.app.Activity;
import android.app.Application;


import com.android.cart.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CartApp extends Application implements HasActivityInjector {

    public static final String TAG = CartApp.class
            .getSimpleName();

    private static CartApp mInstance;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;



    public static synchronized CartApp getInstance() {
        return mInstance;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);



    }


}
