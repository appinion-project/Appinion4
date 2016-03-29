package com.appinion.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BuyGiftCard extends AppCompatActivity implements View.OnClickListener {

    Button btn_QR;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_gift_card);
        initComponenets();
    }

    private void initComponenets() {
        btn_QR=(Button) findViewById(R.id.btn_qr_reader);
        btn_QR.setOnClickListener(this);
        imageView=(ImageView)findViewById(R.id.imageViewQR);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_qr_reader:
                IntentIntegrator scanintegrator=new IntentIntegrator(this);
                scanintegrator.initiateScan();
                break;

        }
    }

    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        IntentResult scanningResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanningResult !=null)
        {
            String scanContent=scanningResult.getContents();
            String scanFormat=scanningResult.getFormatName();
            Toast.makeText(getApplicationContext(),scanContent +"\n"+ scanFormat,Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_SHORT).show();
        }

    }
}
