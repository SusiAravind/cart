package com.android.cart.ui.pages;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.cart.databinding.ListItemsBinding;
import com.android.cart.ui.base.BaseViewHolder;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ItemResponse.ItemResult> item_list;
    String color = "";
    private ItemAdapterListener mItemAdapterListener;

    public ItemAdapter(List<ItemResponse.ItemResult> item_list) {
        this.item_list = item_list;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ListItemsBinding typeBinding = ListItemsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemListViewHolder(typeBinding);

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


    public class ItemListViewHolder extends BaseViewHolder implements ItemViewModel.ItemViewModelListener {

        ListItemsBinding mListItemTypeBinding;
        ItemViewModel mItemViewModel;

        public ItemListViewHolder(ListItemsBinding binding) {
            super(binding.getRoot());
            this.mListItemTypeBinding = binding;
        }

        @Override
        public void onBind(int position) {


            if (item_list.isEmpty()) return;


            final ItemResponse.ItemResult blog = item_list.get(position);
            mItemViewModel = new ItemViewModel(this, blog);
            mListItemTypeBinding.setItemViewModel(mItemViewModel);
            mListItemTypeBinding.executePendingBindings();

            mListItemTypeBinding.aPrice.setPaintFlags(mListItemTypeBinding.aPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



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
