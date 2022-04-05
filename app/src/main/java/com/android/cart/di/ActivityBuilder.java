package com.android.cart.di;


import com.android.cart.ui.cart.CartActivity;
import com.android.cart.ui.cart.CartModule;
import com.android.cart.ui.main.MainActivity;
import com.android.cart.ui.main.MainActivityModule;
import com.android.cart.ui.pages.PageProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = {
            MainActivityModule.class, PageProvider.class

    })
    abstract MainActivity bindMainActivity();


    @ContributesAndroidInjector(modules = {
            CartModule.class

    })
    abstract CartActivity bindCartActivity();


}
