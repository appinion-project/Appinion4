package com.appinion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appinion.app.R;

/**
 * Created by Rishabh Srivastava on 13-Jan-16.
 */
public class PromotionsFragment extends BaseFragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_promotions, container, false);
        try {
            initComponenets();
            prepareView();
            setActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void initComponenets() {

    }

    private void prepareView() {
    }

    private void setActionListener() {
    }
}
