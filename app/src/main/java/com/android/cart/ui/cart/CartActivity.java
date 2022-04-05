package com.android.cart.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.cart.BR;
import com.android.cart.R;
import com.android.cart.databinding.ActivityCartBinding;
import com.android.cart.ui.base.BaseActivity;
import com.android.cart.ui.main.MainActivity;
import com.android.cart.ui.pages.ItemResponse;

import javax.inject.Inject;

public class CartActivity extends BaseActivity<ActivityCartBinding, CartViewModel> implements PageNavigator, CartItemAdapter.ItemAdapterListener {


    @Inject
    CartViewModel mCartViewModel;

    ActivityCartBinding mActivityCartBinding;

    @Inject
    CartItemAdapter mItemAdapter;

    @Inject
    LinearLayoutManager layoutManager;


    public static Intent newIntent(Context context) {
        return new Intent(context, CartActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return BR.cartViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    public CartViewModel getViewModel() {
        return mCartViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCartBinding = getViewDataBinding();
        mCartViewModel.setNavigator(this);
        mItemAdapter.setListener(this);
        subscribeToLiveData();
        setupRecyclerView();
    }


    private void subscribeToLiveData() {

        mCartViewModel.getCartItemLivedata().observe(this,
                imageitemViewModel -> mCartViewModel.addCartItemsToList(imageitemViewModel));


    }

    private void setupRecyclerView() {

        mActivityCartBinding.recyclerCartItem.setAdapter(mItemAdapter);
        mActivityCartBinding.recyclerCartItem.setLayoutManager(layoutManager);

    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void orderPlaced() {
        Toast.makeText(CartActivity.this, "Your order has been placed", Toast.LENGTH_SHORT).show();
        Intent intent = MainActivity.newIntent(CartActivity.this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void addItemClick(ItemResponse.ItemResult model) {
        // model.setCartItem(String.valueOf(Integer.parseInt(model.getCartItem())+1));
        mCartViewModel.update(model);
        mCartViewModel.refreshCart();
    }

    @Override
    public void removeItemClick(ItemResponse.ItemResult model) {

        //  model.setCartItem(String.valueOf(Integer.parseInt(model.getCartItem())+1));
        mCartViewModel.update(model);
        mCartViewModel.refreshCart();

    }

    @Override
    public void insertItemClick(ItemResponse.ItemResult model) {
        model.setCartItem("1");
        mCartViewModel.insert(model);
        mCartViewModel.refreshCart();
    }
}
