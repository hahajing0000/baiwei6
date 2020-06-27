package com.zy.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author:zhangyue
 * @date:2020/6/27
 */
public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public FragmentViewPagerAdapter(@NonNull FragmentManager fm,List<Fragment> _fragments) {
        super(fm);
        this.fragmentList=_fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}
