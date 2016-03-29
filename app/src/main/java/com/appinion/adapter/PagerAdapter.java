package com.appinion.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.Button;

import com.appinion.app.MainActivity;
import com.appinion.app.R;
import com.appinion.fragment.CouponsFragment;
import com.appinion.fragment.GiftCards;
import com.appinion.fragment.HomeFragment;
import com.appinion.fragment.PromotionsFragment;
import com.appinion.utils.ColorChange;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Returns the Fragments in the view pager
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CouponsFragment();
            case 2:
                return new PromotionsFragment();
            case 3:
                return new GiftCards() ;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}