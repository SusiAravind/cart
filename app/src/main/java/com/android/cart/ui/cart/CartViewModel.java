package com.android.cart.ui.cart;

import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;

import com.android.cart.ui.base.BaseViewModel;
import com.android.cart.ui.croom.CartRepository;
import com.android.cart.ui.pages.ItemResponse;
import com.android.cart.utilities.CartApp;

import java.util.List;

import dagger.Module;

@Module
public class CartViewModel extends BaseViewModel<PageNavigator> {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> amount = new ObservableField<>();
    public final ObservableBoolean dataNotAvailable = new ObservableBoolean();
    public final ObservableBoolean couponApplied = new ObservableBoolean();
    private final CartRepository repository;
    public ObservableList<ItemResponse.ItemResult> observableCartItemList = new ObservableArrayList<>();
    private LiveData<List<ItemResponse.ItemResult>> cartitemLivedata;

String couponCODE="SLICEFIRST";
    int amt = 0;
    public CartViewModel() {
        super();
        repository = new CartRepository(CartApp.getInstance());
        refreshCart();
    }


    public void insert(ItemResponse.ItemResult model) {
        for (int i = 0; i < observableCartItemList.size(); i++) {
            if (model.getId().equals(observableCartItemList.get(i).getId())) {
                return;
            }
        }
        repository.insert(model);
    }


    //below line is to update data in our repository.
    public void update(ItemResponse.ItemResult model) {
        if (Integer.parseInt(model.getCartItem()) < 1) {
            repository.delete(model);
            return;
        }
        repository.update(model);
    }


    public void applyCoupon(String coupon) {
        if (couponCODE.equals(coupon)){
            couponApplied.set(true);

            amount.set("\u20B9" + String.valueOf(amt-96));
        }else {
            couponApplied.set(false);
            Toast.makeText(CartApp.getInstance(), "Invalid coupon", Toast.LENGTH_SHORT).show();
        }

    }
     public void removeCoupon() {
            couponApplied.set(false);
            amount.set("\u20B9" + String.valueOf(amt));
    }


    public void refreshCart() {
        cartitemLivedata = repository.getCarts();
    }

    public void back() {
        getNavigator().back();

    }

 public void orderPlaced() {
        repository.deleteAllCarts();
        getNavigator().orderPlaced();

    }


    public LiveData<List<ItemResponse.ItemResult>> getCartItemLivedata() {
        return cartitemLivedata;
    }

    public void addCartItemsToList(List<ItemResponse.ItemResult> cartitemResults) {
        observableCartItemList.clear();
        observableCartItemList.addAll(cartitemResults);

         amt = 0;
        for (ItemResponse.ItemResult itemResult : observableCartItemList) {
            amt =amt+ Integer.parseInt(itemResult.getCartItem()) * Integer.parseInt(itemResult.getOfferPrice());
        }
        if (couponApplied.get())
            amt=amt-96;
        amount.set("\u20B9" + String.valueOf(amt));



        if (observableCartItemList.size() < 1) {
            dataNotAvailable.set(true);
        }else {

            dataNotAvailable.set(false);
        }
    }

}
