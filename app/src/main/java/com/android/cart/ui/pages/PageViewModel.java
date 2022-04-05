package com.android.cart.ui.pages;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.cart.ui.base.BaseViewModel;
import com.android.cart.ui.croom.CartRepository;
import com.android.cart.utilities.CartApp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

@Module
public class PageViewModel extends BaseViewModel<PageNavigator> {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableBoolean dataNotAvailable = new ObservableBoolean();
    public final ObservableField<Boolean> showCart = new ObservableField<>();
    private final MutableLiveData<List<ItemResponse.ItemResult>> itemLivedata;
    private final CartRepository repository;
    public ObservableList<ItemResponse.ItemResult> observableItemList = new ObservableArrayList<>();
    public ObservableList<ItemResponse.ItemResult> observableCartItemList = new ObservableArrayList<>();
    boolean loadOnce = false;
    ArrayList<ItemResponse.ItemResult> fulllist = new ArrayList<>();
    private LiveData<List<ItemResponse.ItemResult>> cartitemLivedata;

    public PageViewModel() {
        super();
        itemLivedata = new MutableLiveData<>();
        repository = new CartRepository(CartApp.getInstance());
        refreshCart();
        // loadData();
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


    public void refreshCart() {
        cartitemLivedata = repository.getCarts();
    }

    public MutableLiveData<List<ItemResponse.ItemResult>> getItemLivedata() {
        return itemLivedata;
    }

    public void addItems(List<ItemResponse.ItemResult> itemResults) {
        observableItemList.clear();
        observableItemList.addAll(itemResults);
    }


    public LiveData<List<ItemResponse.ItemResult>> getCartItemLivedata() {
        return cartitemLivedata;
    }

    public void addCartItemsToList(List<ItemResponse.ItemResult> cartitemResults) {
        observableCartItemList.clear();
        observableCartItemList.addAll(cartitemResults);
        int amt = 0;
        if (getNavigator() != null) {

            for (ItemResponse.ItemResult itemResult : observableCartItemList) {
                amt = amt + Integer.parseInt(itemResult.getCartItem()) * Integer.parseInt(itemResult.getOfferPrice());
            }
            getNavigator().syncCartCount(observableCartItemList.size(), amt);
        }

        showCart.set(amt > 0);


    }

    public void loadData() {

        String sample = "{\"items\":[{\"id\":\"1\",\"type\":\"fruits\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/265px-Red_Apple.jpg\",\"title\":\"Apple\",\"actualPrice\":\"100\",\"offerPrice\":\"80\"},{\"id\":\"2\",\"type\":\"fruits\",\"image\":\"https://media.istockphoto.com/photos/orange-picture-id185284489?k=20&m=185284489&s=612x612&w=0&h=LLY2os0YTG2uAzpBKpQZOAC4DGiXBt1jJrltErTJTKI=\",\"title\":\"Orange\",\"actualPrice\":\"150\",\"offerPrice\":\"100\"},{\"id\":\"3\",\"type\":\"fruits\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Pineapple_and_cross_section.jpg/286px-Pineapple_and_cross_section.jpg\",\"title\":\"Pineapple\",\"actualPrice\":\"100\",\"offerPrice\":\"6\"},{\"id\":\"4\",\"type\":\"fruits\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Bananas_white_background_DS.jpg/320px-Bananas_white_background_DS.jpg\",\"title\":\"Banana\",\"actualPrice\":\"200\",\"offerPrice\":\"170\"},{\"id\":\"5\",\"type\":\"fruits\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Table_grapes_on_white.jpg/320px-Table_grapes_on_white.jpg\",\"title\":\"Grapes\",\"actualPrice\":\"110\",\"offerPrice\":\"100\"},{\"id\":\"6\",\"title\":\"Brown eggs\",\"type\":\"dairy\",\"image\":\"https://media.istockphoto.com/photos/set-of-egg-isolated-picture-id1028690210?k=20&m=1028690210&s=612x612&w=0&h=bHEOKHGkw_gy0pwJF0_qoOONVvEEobCM8TsL3w0nbeU=\",\"actualPrice\":\"100\",\"offerPrice\":\"70\"},{\"id\":\"7\",\"title\":\"Asparagus\",\"type\":\"vegetable\",\"image\":\"https://media.istockphoto.com/photos/fresh-vegitables-picture-id172862539?s=612x612\",\"actualPrice\":\"120\",\"offerPrice\":\"100\"}]}";


        ItemResponse itemResponse = new Gson().fromJson(sample, ItemResponse.class);

       // if (observableCartItemList.size() < 1) return;
        if (loadOnce)
            return;
        loadOnce = true;

        fulllist.addAll(itemResponse.getItems());
        for (int i = 0; i < itemResponse.getItems().size(); i++) {
            for (ItemResponse.ItemResult itemResponse2 : observableCartItemList) {
                if (itemResponse.getItems().get(i).getId().equals(itemResponse2.getId())) {
                    itemResponse.getItems().get(i).setCartItem(itemResponse2.getCartItem());
                }
            }
        }

        itemLivedata.setValue(itemResponse.getItems());
    }


    public void gotoCart() {
        getNavigator().gotoCart();
    }

    public void clearSearch() {
        itemLivedata.setValue(fulllist);
    }


    public void filter(String searchText) {

        ArrayList<ItemResponse.ItemResult> filteredlist = new ArrayList<>();
        for (ItemResponse.ItemResult item : observableItemList)
            if (item.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                filteredlist.add(item);
            else if (item.getType().toLowerCase().contains(searchText.toLowerCase()))
                filteredlist.add(item);
        if (filteredlist.isEmpty())
            dataNotAvailable.set(true);
        else {
            dataNotAvailable.set(false);
            itemLivedata.setValue(filteredlist);
        }


    }


}
