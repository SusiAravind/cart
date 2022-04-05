package com.android.cart.ui.pages;


public interface PageNavigator {

    void handleError(Throwable throwable);
    void gotoCart();
    void syncCartCount(int count,int amt);

}
