
package com.android.cart.di.component;

import android.app.Application;


import com.android.cart.di.ActivityBuilder;
import com.android.cart.di.appmodule.AppModule;
import com.android.cart.utilities.CartApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(CartApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }


}
