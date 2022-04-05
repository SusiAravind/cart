package com.android.cart.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.cart.ui.pages.PageFragment;

public class PagePagerAdapter extends FragmentStatePagerAdapter {
    public PagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return  PageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 1;
    }
}