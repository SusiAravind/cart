package com.android.cart.ui.main;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.android.cart.ui.base.BaseViewModel;


public class MainViewModel extends BaseViewModel<MainNavigator> {


    public final ObservableBoolean isHome = new ObservableBoolean();
    public final ObservableBoolean isSettings = new ObservableBoolean();
    public final ObservableBoolean isMail = new ObservableBoolean();
    public final ObservableBoolean isAcc = new ObservableBoolean();

    public final ObservableField<String> cartCount = new ObservableField<>();
    public final ObservableField<Boolean> showCart = new ObservableField<>();
    public final ObservableField<String> cartAmount = new ObservableField<>();



    public MainViewModel() {
        super();
    }


    public void gotoCart() {
        getNavigator().gotoCart();

    }

}
