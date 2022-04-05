package com.android.cart.utilities;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.cart.R;
import com.android.cart.ui.cart.CartItemAdapter;
import com.android.cart.ui.pages.CartImageItemAdapter;
import com.android.cart.ui.pages.ItemResponse;
import com.android.cart.ui.pages.ItemAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);


    }

    @BindingAdapter({"itemAdapter"})
    public static void addListItems(RecyclerView recyclerView, List<ItemResponse.ItemResult> results) {
        ItemAdapter adapter = (ItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(results);
        }
    }

     @BindingAdapter({"itemImageAdapter"})
    public static void addListImageItems(RecyclerView recyclerView, List<ItemResponse.ItemResult> results) {
        CartImageItemAdapter adapter = (CartImageItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(results);
        }
    }
  @BindingAdapter({"itemCartAdapter"})
    public static void addListCartItems(RecyclerView recyclerView, List<ItemResponse.ItemResult> results) {
        CartItemAdapter adapter = (CartItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(results);
        }
    }



    @BindingAdapter("menuImages")
    public static void setMenuImage(ImageView imageView, String option) {
        Context context = imageView.getContext();
        switch (option) {

            case "1":
                imageView.setImageResource(R.drawable.home);
                break;
            case "2":
                Glide.with(context)
                        .load(R.raw.settings)
                        .into(imageView);
                break;
            case "3":
                Glide.with(context)
                        .load(R.raw.mail)
                        .into(imageView);

                break;
            case "4":
                imageView.setImageResource(R.drawable.user);
                break;

        }


    }

}


