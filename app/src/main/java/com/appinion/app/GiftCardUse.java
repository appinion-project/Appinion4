package com.appinion.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appinion.utils.MaterialUtils;

import org.w3c.dom.Text;

/**
 * Created by Ali on 3/22/2016.
 */
public class GiftCardUse extends Activity implements View.OnClickListener{
    private Button btnThousand,btnFive,btnHundred;
    private Button btnMinusHundred,btnMinusFive,btnMinusThousand;
    private Button btnAmountHundred,btnAmountfive,btnAmountThousand;
    private Button btnCountHundred,btnCountfive,btnCountThousand;

    private TextView txtTotal;

    private int totalTaka;

    private int hundredCount,thousandCount,fiveHundredCount;
    private int total;
    int counterH;
    int counterT;
    int counterF;


    private Button btnGiftBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftcard_use);
        try{
            initComponent();
            preparedView();
        }catch (Exception e){

        }
    }

    protected void initComponent(){
        btnGiftBack=(Button)findViewById(R.id.btn_giftUse_back);
        btnThousand=(Button)findViewById(R.id.btn_giftUse_thousand);
        btnFive=(Button)findViewById(R.id.btn_giftUse_five);
        btnHundred=(Button)findViewById(R.id.btn_giftUse_hundred);

        btnMinusFive=(Button)findViewById(R.id.btn_giftUse_plus_five);
        btnMinusHundred=(Button)findViewById(R.id.btn_giftUse_plus_hundred);
        btnMinusThousand=(Button)findViewById(R.id.btn_giftUse_plus_thousand);

        btnAmountfive=(Button)findViewById(R.id.btn_giftUse_totalamount_five);
        btnAmountHundred=(Button)findViewById(R.id.btn_giftUse_totalamount_hundred);
        btnAmountThousand=(Button)findViewById(R.id.btn_giftUse_amount_thousand);

        btnCountfive=(Button)findViewById(R.id.btn_giftUse_counttotal_five);
        btnCountHundred=(Button)findViewById(R.id.btn_giftUse_counttotal_hundred);
        btnCountThousand=(Button)findViewById(R.id.btn_giftUse_counttotal_thousand);
        txtTotal=(TextView)findViewById(R.id.textViewTotal);

        hundredCount=5;
        thousandCount=5;
        fiveHundredCount=5;

        btnCountThousand.setText("0");
        btnCountHundred.setText("0");
        btnCountfive.setText("0");

        btnAmountfive.setText(hundredCount+"");
        btnAmountHundred.setText(hundredCount+"");
        btnAmountThousand.setText(hundredCount+"");
        txtTotal.setText("0 tk");


        counterF=0;
        counterT=0;
        counterH=0;
        totalTaka=0;




    }
    protected void preparedView(){
        btnGiftBack.setOnClickListener(this);

        btnThousand.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnHundred.setOnClickListener(this);

        btnMinusFive.setOnClickListener(this);
        btnMinusHundred.setOnClickListener(this);
        btnMinusThousand.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_giftUse_thousand:
                if(thousandCount >0){
                    btnCountThousand.setText((++counterT) + "");
                    btnAmountThousand.setText( (--thousandCount)+"");
                    totalTaka+=1000;
                    txtTotal.setText(totalTaka+" tk");
                }
                else {
                    MaterialUtils.showLongToast(this,"You have no sufficient 1000 gift card.");
                }
                break;

            case R.id.btn_giftUse_hundred:
                if(hundredCount >0){
                    btnCountHundred.setText((++counterH)+"");
                    btnAmountHundred.setText( (--hundredCount)+"");
                    totalTaka+=100;
                    txtTotal.setText(totalTaka+" tk");
                }
                else {
                    MaterialUtils.showLongToast(this,"You have no sufficient 100 gift card.");
                }
                break;

            case R.id.btn_giftUse_five:
                if(fiveHundredCount >0){
                    btnCountfive.setText((++counterF)+"");
                    btnAmountfive.setText( (--fiveHundredCount)+"");
                    totalTaka+=500;
                    txtTotal.setText(totalTaka+" tk");
                }
                else {
                    MaterialUtils.showLongToast(this,"You have no sufficient 100 gift card.");
                }
                break;

            case R.id.btn_giftUse_plus_five:
                if(Integer.parseInt(btnCountfive.getText().toString())> 0 ){
                    btnCountfive.setText(--counterF+"");
                    btnAmountfive.setText( (++fiveHundredCount)+"");
                    totalTaka-=500;
                    txtTotal.setText(totalTaka+" tk");


                }
                else {
                    MaterialUtils.showLongToast(this,"Invalid");
                }
                break;
            case R.id.btn_giftUse_plus_hundred:
                if(Integer.parseInt(btnCountHundred.getText().toString())> 0 ){
                    btnCountHundred.setText(--counterH+"");
                    btnAmountHundred.setText( (++hundredCount)+"");
                    totalTaka-=100;
                    txtTotal.setText(totalTaka+" tk");

                }
                else {
                    MaterialUtils.showLongToast(this,"Invalid");
                }
                break;
            case R.id.btn_giftUse_plus_thousand:
                if(Integer.parseInt(btnCountThousand.getText().toString())> 0 ){
                    btnCountThousand.setText(--counterT+"");
                    btnAmountThousand.setText( (++thousandCount)+"");
                    totalTaka-=1000;
                    txtTotal.setText(totalTaka+" tk");

                }
                else {
                    MaterialUtils.showLongToast(this,"Invalid");
                }


                break;
            case R.id.btn_giftUse_back:
                finish();
                break;


        }

    }
}
