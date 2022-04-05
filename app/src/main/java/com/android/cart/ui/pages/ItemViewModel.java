package com.android.cart.ui.pages;


import androidx.databinding.ObservableField;


public class ItemViewModel {


    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> image = new ObservableField<>();
    public final ObservableField<String> aPrice = new ObservableField<>();
    public final ObservableField<String> oPrice = new ObservableField<>();
    public final ObservableField<String> cartCount = new ObservableField<>();
    public final ObservableField<Boolean> showCart = new ObservableField<>();

    private final ItemResponse.ItemResult itemResult;
    private final ItemViewModelListener mListener;

    public ItemViewModel(ItemViewModelListener mListener, ItemResponse.ItemResult result) {
        this.mListener = mListener;
        this.itemResult = result;
        title.set(result.getTitle());
        image.set(result.getImage());
        aPrice.set("\u20B9" + result.getActualPrice());
        oPrice.set("\u20B9" + result.getOfferPrice());
        cartCount.set(result.getCartItem());
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) < 1));

    }


    public void insertItemClick() {

        itemResult.setCartItem(String.valueOf(Integer.parseInt(itemResult.getCartItem()) + 1));
        showCart.set(true);
        cartCount.set(itemResult.getCartItem());
        mListener.insertItemClick(itemResult);
    }

    public void addItemClick() {
        itemResult.setCartItem(String.valueOf(Integer.parseInt(itemResult.getCartItem()) + 1));
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) < 1));
        cartCount.set(itemResult.getCartItem());
        mListener.addItemClick(itemResult);
    }

    public void removeItemClick() {
        itemResult.setCartItem(String.valueOf(Integer.parseInt(itemResult.getCartItem()) - 1));
        showCart.set(!(Integer.parseInt(itemResult.getCartItem()) < 1));
        cartCount.set(itemResult.getCartItem());
        mListener.removeItemClick(itemResult);
    }


    public interface ItemViewModelListener {
        void addItemClick(ItemResponse.ItemResult model);

        void removeItemClick(ItemResponse.ItemResult model);

        void insertItemClick(ItemResponse.ItemResult model);

    }

}
