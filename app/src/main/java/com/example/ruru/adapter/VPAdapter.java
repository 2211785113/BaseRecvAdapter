package com.example.ruru.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ruru.data.DataModel;

import java.util.List;

public class VPAdapter extends FragmentPagerAdapter {

    private final List<Fragment> list;

    public VPAdapter(FragmentManager fm) {
        super(fm);
        list = DataModel.getFragmentList();
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return DataModel.getTitlesList().get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
