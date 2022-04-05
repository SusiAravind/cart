package com.android.cart.ui.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {



    @Provides
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }

}
