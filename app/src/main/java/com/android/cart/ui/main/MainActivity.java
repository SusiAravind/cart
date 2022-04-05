package com.android.cart.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.cart.BR;
import com.android.cart.R;
import com.android.cart.databinding.ActivityMainBinding;
import com.android.cart.ui.base.BaseActivity;
import com.android.cart.ui.cart.CartActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, HasSupportFragmentInjector {


    boolean doubleBackToExitPressedOnce = false;
    @Inject
    MainViewModel mMainViewModel;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    private ActivityMainBinding mActivityMainBinding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {

    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);


        PagePagerAdapter adapter = new PagePagerAdapter
                (getSupportFragmentManager());
        mActivityMainBinding.frame.setAdapter(adapter);


    }

    @Override
    public void gotoCart() {
        startActivity(CartActivity.newIntent(MainActivity.this));
    }

    public void refreshCart(int cartCount, int amt) {
        mMainViewModel.cartCount.set(String.valueOf(cartCount));
        mMainViewModel.cartAmount.set("\u20B9" + amt);
        mMainViewModel.showCart.set(!(cartCount < 1));

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
