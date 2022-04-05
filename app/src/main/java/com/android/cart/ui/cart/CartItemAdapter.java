package com.android.cart.ui.cart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.cart.databinding.ListItemCartBinding;
import com.android.cart.databinding.ListItemsBinding;
import com.android.cart.ui.base.BaseViewHolder;
import com.android.cart.ui.pages.ItemResponse;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ItemResponse.ItemResult> item_list;
    String color = "";
    private ItemAdapterListener mItemAdapterListener;

    public CartItemAdapter(List<ItemResponse.ItemResult> item_list) {
        this.item_list = item_list;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ListItemCartBinding cartBinding = ListItemCartBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemListViewHolder(cartBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }


    public void clearItems() {
        item_list.clear();
    }

    public void colorCode(String cc) {
        color = cc;
    }


    public void addItems(List<ItemResponse.ItemResult> blogList) {
        item_list.addAll(blogList);
        notifyDataSetChanged();
    }

    public void setListener(ItemAdapterListener listener) {
        this.mItemAdapterListener = listener;
    }

    public interface ItemAdapterListener {

        void addItemClick(ItemResponse.ItemResult model);

        void removeItemClick(ItemResponse.ItemResult model);
        void insertItemClick(ItemResponse.ItemResult model);


    }


    public class ItemListViewHolder extends BaseViewHolder implements CartItemViewModel.ItemViewModelListener {

        ListItemCartBinding mListItemTypeBinding;
        CartItemViewModel mItemViewModel;

        public ItemListViewHolder(ListItemCartBinding binding) {
            super(binding.getRoot());
            this.mListItemTypeBinding = binding;
        }

        @Override
        public void onBind(int position) {


            if (item_list.isEmpty()) return;


            final ItemResponse.ItemResult blog = item_list.get(position);
            mItemViewModel = new CartItemViewModel(this, blog);
            mListItemTypeBinding.setCartItemViewModel(mItemViewModel);
            mListItemTypeBinding.executePendingBindings();



        }

        @Override
        public void addItemClick(ItemResponse.ItemResult model) {
            mItemAdapterListener.addItemClick(model);
        }

        @Override
        public void removeItemClick(ItemResponse.ItemResult model) {
            mItemAdapterListener.removeItemClick(model);
        }

        @Override
        public void insertItemClick(ItemResponse.ItemResult model) {
            mItemAdapterListener.insertItemClick(model);
        }
    }

}
