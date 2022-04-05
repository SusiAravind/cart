package com.android.cart.ui.cart;


import androidx.databinding.ObservableField;

import com.android.cart.ui.pages.ItemResponse;


public class CartItemViewModel {


    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> image = new ObservableField<>();
    public final ObservableField<String> aPrice = new ObservableField<>();
    public final ObservableField<String> oPrice = new ObservableField<>();
    public final ObservableField<String> cartCount = new ObservableField<>();
    public final ObservableField<Boolean> showCart = new ObservableField<>();

    private final ItemResponse.ItemResult itemResult;
    private final ItemViewModelListener mListener;

    public CartItemViewModel(ItemViewModelListener mListener, ItemResponse.ItemResult result) {
        this.mListener = mListener;
        this.itemResult = result;
        title.set(result.getTitle());
        image.set(result.getImage());
        aPrice.set("\u20B9"+ String.valueOf(Integer.parseInt(itemResult.getCartItem())*Integer.parseInt(result.getOfferPrice())));
        cartCount.set(result.getCartItem());
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) <1));




    }


    public void insertItemClick() {
        cartCount.set("1");
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) <1));
        mListener.insertItemClick(itemResult);
    }

    public void addItemClick() {
        itemResult.setCartItem(String.valueOf(Integer.parseInt(itemResult.getCartItem())+1));
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) <1));
        cartCount.set(itemResult.getCartItem());
        mListener.addItemClick(itemResult);
    }

    public void removeItemClick() {
        itemResult.setCartItem(String.valueOf(Integer.parseInt(itemResult.getCartItem())-1));
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) <1));
        cartCount.set(itemResult.getCartItem());
        mListener.removeItemClick(itemResult);
    }


    public interface ItemViewModelListener {
        void addItemClick(ItemResponse.ItemResult model);

        void removeItemClick(ItemResponse.ItemResult model);
        void insertItemClick(ItemResponse.ItemResult model);

    }

}
