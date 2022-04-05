package com.android.cart.ui.croom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.cart.ui.pages.ItemResponse;

import java.util.List;

public class CartRepository {
    //below line is the create a variable for dao and list for all Carts.
    private Dao dao;
    private LiveData<List<ItemResponse.ItemResult>> allCarts;

    //creating a constructor for our variables and passing the variables to it.
    public CartRepository(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        dao = database.Dao();
        allCarts = dao.getAllCarts();
    }

    //creating a method to insert the data to our database.
    public void insert(ItemResponse.ItemResult model) {
        new InsertCartAsyncTask(dao).execute(model);
    }

    //creating a method to update data in database.
    public void update(ItemResponse.ItemResult model) {
        new UpdateCartAsyncTask(dao).execute(model);
    }

    //creating a method to delete the data in our database.
    public void delete(ItemResponse.ItemResult model) {
        new DeleteCartAsyncTask(dao).execute(model);
    }

    //below is the method to delete all the Carts.
    public void deleteAllCarts() {
        new DeleteAllCartsAsyncTask(dao).execute();
    }

    //below method is to read all the Carts.
    public LiveData<List<ItemResponse.ItemResult>> getCarts() {
        return allCarts;
    }

    //we are creating a async task method to insert new Cart.
    private static class InsertCartAsyncTask extends AsyncTask<ItemResponse.ItemResult, Void, Void> {
        private Dao dao;

        private InsertCartAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemResponse.ItemResult... model) {
            //below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    //we are creating a async task method to update our Cart.
    private static class UpdateCartAsyncTask extends AsyncTask<ItemResponse.ItemResult, Void, Void> {
        private Dao dao;

        private UpdateCartAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemResponse.ItemResult... models) {
            //below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete Cart.
    private static class DeleteCartAsyncTask extends AsyncTask<ItemResponse.ItemResult, Void, Void> {
        private Dao dao;

        private DeleteCartAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ItemResponse.ItemResult... models) {
            //below line is use to delete our Cart modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    //we are creating a async task method to delete all Carts.
    private static class DeleteAllCartsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllCartsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //on below line calling method to delete all Carts.
            dao.deleteAllCarts();
            return null;
        }
    }
}
