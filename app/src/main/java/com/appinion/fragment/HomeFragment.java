package com.appinion.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appinion.app.MainActivity;
import com.appinion.app.R;
import com.appinion.utils.MaterialUtils;
import com.google.zxing.BarcodeFormat;

/**
 * Created by Rishabh Srivastava on 13-Jan-16.
 */
public class HomeFragment extends BaseFragment {
    View view;
    private ImageView mUserBarcodeIV;
    private String userBarcode="aliahmed";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

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
        mUserBarcodeIV=(ImageView)view.findViewById(R.id.imageViewUserBarcode);
    }

    private void prepareView() {
        try {
            mUserBarcodeIV.setImageBitmap(MaterialUtils.encodeAsBitmap(userBarcode, BarcodeFormat.CODE_128,300,100));
        }
        catch (Exception e){}

    }

    private void setActionListener() {
    }
}
