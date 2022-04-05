package com.android.cart.ui.pages;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("items")
    @Expose
    private List<ItemResult> items = null;

    public List<ItemResult> getItems() {
        return items;
    }

    public void setItems(List<ItemResult> items) {
        this.items = items;
    }

    @Entity(tableName = "cart_items")
    public static class ItemResult {
        @PrimaryKey()
        @NonNull
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("type")
        @Expose
        private String type;


        @SerializedName("actualPrice")
        @Expose
        private String actualPrice;
        @SerializedName("offerPrice")
        @Expose
        private String offerPrice;
        @SerializedName("cartItem")
        @Expose
        private String cartItem;

        @SerializedName("image")
        @Expose
        private String image;

        public ItemResult(String id, String title, String type, String actualPrice, String offerPrice, String cartItem, String image) {
            this.id = id;
            this.title = title;
            this.type = type;
            this.actualPrice = actualPrice;
            this.offerPrice = offerPrice;
            this.cartItem = cartItem;
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public String getCartItem() {
            if (cartItem == null) return "0";
            return cartItem;
        }

        public void setCartItem(String cartItem) {
            this.cartItem = cartItem;
        }

    }

}