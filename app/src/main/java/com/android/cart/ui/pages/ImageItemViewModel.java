package com.android.cart.ui.pages;


import androidx.databinding.ObservableField;


public class ImageItemViewModel {


    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> image = new ObservableField<>();
    public final ObservableField<String> aPrice = new ObservableField<>();
    public final ObservableField<String> oPrice = new ObservableField<>();
    public final ObservableField<String> cartCount = new ObservableField<>();
    public final ObservableField<Boolean> showCart = new ObservableField<>();

    private final ItemResponse.ItemResult itemResult;
    private final ItemViewModelListener mListener;

    public ImageItemViewModel(ItemViewModelListener mListener, ItemResponse.ItemResult result) {
        this.mListener = mListener;
        this.itemResult = result;
        title.set(result.getTitle());
        image.set(result.getImage());
        aPrice.set(result.getActualPrice());
        oPrice.set(result.getOfferPrice());
        cartCount.set(result.getCartItem());
        showCart.set(!result.getCartItem().equals("0"));

    }


    public void onItemClick() {
        mListener.typeItemClick(itemResult);
    }


    public interface ItemViewModelListener {
        void typeItemClick(ItemResponse.ItemResult model);

    }

}
