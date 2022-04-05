package com.android.cart.ui.pages;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.cart.BR;
import com.android.cart.R;
import com.android.cart.databinding.FragmentPageBinding;
import com.android.cart.ui.base.BaseFragment;
import com.android.cart.ui.cart.CartActivity;
import com.android.cart.ui.main.MainActivity;

import javax.inject.Inject;

public class PageFragment extends BaseFragment<FragmentPageBinding, PageViewModel> implements PageNavigator, ItemAdapter.ItemAdapterListener {


    @Inject
    PageViewModel mPageViewModel;

    FragmentPageBinding mFragmentPageBinding;

    @Inject
    ItemAdapter mItemAdapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    CartImageItemAdapter mCartImageItemAdapter;


    public static PageFragment newInstance(int pos) {
        Bundle args = new Bundle();
        args.putInt("POS", pos);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.dashboardViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_page;
    }

    @Override
    public PageViewModel getViewModel() {
        return mPageViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageViewModel.setNavigator(this);
        mItemAdapter.setListener(this);
        subscribeToLiveData();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPageBinding = getViewDataBinding();
        setupRecyclerView();

        mFragmentPageBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPageViewModel.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1)
                    mPageViewModel.filter(newText);
                else mPageViewModel.clearSearch();
                return false;

            }
        });
        mFragmentPageBinding.searchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mPageViewModel.clearSearch();
                return false;
            }
        });
    }

    private void subscribeToLiveData() {
        mPageViewModel.getCartItemLivedata().observe(this,
                imageitemViewModel -> {
                    mPageViewModel.addCartItemsToList(imageitemViewModel);
                   // if (imageitemViewModel.size() > 1)
                       mPageViewModel.loadData();
                });
        mPageViewModel.getItemLivedata().observe(this,
                itemViewModel -> mPageViewModel.addItems(itemViewModel));
    }

    private void setupRecyclerView() {
        mFragmentPageBinding.recyclerItem.setAdapter(mItemAdapter);
        mFragmentPageBinding.recyclerItem.setLayoutManager(new GridLayoutManager(getContext(), 2));

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFragmentPageBinding.recyclerCartItem.setAdapter(mCartImageItemAdapter);
        mFragmentPageBinding.recyclerCartItem.setLayoutManager(layoutManager);

    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void gotoCart() {
        startActivity(CartActivity.newIntent(getContext()));
    }

    @Override
    public void syncCartCount(int count, int amt) {

        ((MainActivity) getActivity()).refreshCart(count, amt);

    }


    @Override
    public void addItemClick(ItemResponse.ItemResult model) {
        // model.setCartItem(String.valueOf(Integer.parseInt(model.getCartItem())+1));
        mPageViewModel.update(model);
        mPageViewModel.refreshCart();
    }

    @Override
    public void removeItemClick(ItemResponse.ItemResult model) {

        //  model.setCartItem(String.valueOf(Integer.parseInt(model.getCartItem())+1));
        mPageViewModel.update(model);
        mPageViewModel.refreshCart();

    }

    @Override
    public void insertItemClick(ItemResponse.ItemResult model) {
        if (Integer.parseInt(model.getCartItem()) == 1)
            mPageViewModel.insert(model);
        else
            mPageViewModel.update(model);
        mPageViewModel.refreshCart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
