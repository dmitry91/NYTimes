package com.dmitry.nytimes.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.dmitry.nytimes.ui.fragments.FragmentMostPopular;
import com.dmitry.nytimes.ui.fragments.FragmentSaved;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int size =4;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentMostPopular.newInstance(0);
            case 1:
                return FragmentMostPopular.newInstance(1);
            case 2:
                return FragmentMostPopular.newInstance(2);
            case 3:
                return new FragmentSaved();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Most Emailed";
            case 1:
                return "Most Shared";
            case 2:
                return "Most Viewed";
            case 3:
                return "Saved";
            default:
                return null;
        }
    }

}