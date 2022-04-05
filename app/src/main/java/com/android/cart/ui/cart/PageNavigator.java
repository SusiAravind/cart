package com.android.cart.ui.cart;


public interface PageNavigator {

    void handleError(Throwable throwable);

    void back();
    void orderPlaced();
}
