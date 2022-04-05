package com.android.cart.ui.pages;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.cart.databinding.ListItemsBinding;
import com.android.cart.databinding.ListItemsImageBinding;
import com.android.cart.ui.base.BaseViewHolder;

import java.util.List;

public class CartImageItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ItemResponse.ItemResult> item_list;
    String color = "";
    private ItemAdapterListener mItemAdapterListener;

    public CartImageItemAdapter(List<ItemResponse.ItemResult> item_list) {
        this.item_list = item_list;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ListItemsImageBinding imageBinding = ListItemsImageBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemListViewHolder(imageBinding);

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

        void itemClick(ItemResponse.ItemResult itemResult);

    }


    public class ItemListViewHolder extends BaseViewHolder implements ImageItemViewModel.ItemViewModelListener {

        ListItemsImageBinding mListItemTypeBinding;
        ImageItemViewModel mItemViewModel;

        public ItemListViewHolder(ListItemsImageBinding binding) {
            super(binding.getRoot());
            this.mListItemTypeBinding = binding;
        }

        @Override
        public void onBind(int position) {


            if (item_list.isEmpty()) return;


            final ItemResponse.ItemResult blog = item_list.get(position);
            mItemViewModel = new ImageItemViewModel(this, blog);
            mListItemTypeBinding.setImageItemViewModel(mItemViewModel);
            mListItemTypeBinding.executePendingBindings();



        }

        @Override
        public void typeItemClick(ItemResponse.ItemResult model) {
            mItemAdapterListener.itemClick(model);
        }
    }

}
