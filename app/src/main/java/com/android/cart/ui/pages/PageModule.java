package com.android.cart.ui.pages;


import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PageModule {

    @Provides
    PageViewModel provideMyAccViewModel() {
        return new PageViewModel();
    }

    @Provides
    ItemAdapter provideListAdapter() {
        return new ItemAdapter(new ArrayList<>());
    }

    @Provides
    CartImageItemAdapter provideCartImageItemAdapter() {
        return new CartImageItemAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PageFragment fragment) {
        return new LinearLayoutManager(fragment.getContext());
    }
}
