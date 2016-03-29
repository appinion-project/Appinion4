package com.appinion.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;

import com.appinion.app.BuyGiftCard;
import com.appinion.app.GiftCardUse;
import com.appinion.app.MainActivity;
import com.appinion.app.R;
import com.appinion.utils.MaterialUtils;

/**
 * Created by ali on 22-Mar-16.
 */
public class GiftCards extends BaseFragment {
    View view;
    private Activity mActivity;
    private Button btnGiftUse,
            btnGiftNotify,
            btnGiftBuy,
            btnGiftSend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_giftcards, container, false);
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
        //btnGiftUse=(Button)
        btnGiftUse=(Button)view.findViewById(R.id.btn_gift_use);
        btnGiftNotify=(Button)view.findViewById(R.id.btn_gift_notify);
        btnGiftBuy=(Button)view.findViewById(R.id.btn_gift_buy);
        btnGiftSend=(Button)view.findViewById(R.id.btn_gift_send);
    }

    private void prepareView() {
        btnGiftUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MaterialUtils.showLongToast(getContext(),"Hello");
                Intent intent=new Intent(getContext(), GiftCardUse.class);
                startActivity(intent);
            }
        });
        btnGiftNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialUtils.showLongToast(getContext(),"Hello");
            }
        });
        btnGiftBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MaterialUtils.showLongToast(getContext(),"Hello");
                Intent intent = new Intent(getContext(), BuyGiftCard.class);
                startActivity(intent);
            }
        });
        btnGiftSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialUtils.showLongToast(getContext(),"Hello");
            }
        });

    }

    private void setActionListener() {
    }
}
