package com.android.cart.ui.pages;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PageProvider {

    @ContributesAndroidInjector(modules = PageModule.class)
    abstract PageFragment providePageFragmentFactory();

}
