package com.android.cart.ui.croom;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.cart.ui.pages.ItemResponse;

import java.util.List;

//Adding annotation to our Dao class
@androidx.room.Dao
public interface Dao {

    //below method is use to add data to database.
    @Insert
    void insert(ItemResponse.ItemResult model);

    //below method is use to update the data in our database.
    @Update
    void update(ItemResponse.ItemResult model);

    //below line is use to delete a specific Cart in our database.
    @Delete
    void delete(ItemResponse.ItemResult model);

    //on below line we are making query to delete all Carts from our databse.
    @Query("DELETE FROM cart_items")
    void deleteAllCarts();

    //beloe line is to read all the Carts from our database.
    @Query("SELECT * FROM cart_items")
    LiveData<List<ItemResponse.ItemResult>> getAllCarts();

}
