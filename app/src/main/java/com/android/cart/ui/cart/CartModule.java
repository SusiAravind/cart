package com.android.cart.ui.cart;


import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CartModule {

    @Provides
    CartViewModel provideMyAccViewModel() {
        return new CartViewModel();
    }

    @Provides
    CartItemAdapter provideListAdapter() {
        return new CartItemAdapter(new ArrayList<>());
    }



    @Provides
    LinearLayoutManager provideLinearLayoutManager(CartActivity fragment) {
        return new LinearLayoutManager(fragment);
    }
}
