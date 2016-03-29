package com.appinion.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.appinion.adapter.PagerAdapter;
import com.appinion.utils.MaterialUtils;
import com.appinion.utils.Pref;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    public ImageView mCouponIv, mHomeIv, mPromotionsIv, mGiftCardsIv,mProfileContentIv,mSearchIv;
    private Intent intent;
    private ViewPager mViewPager;
    LinearLayout layoutSearch;
    View lineCoupon,lineGiftCard,lineLoyalty,lineSurvey;
   // private Spinner spinnerProfileActivity;
    PagerAdapter adapter;
    private EditText mSearchEt;
    public Button mHomebtn,mCouponbtn,mgiftcardbtn,mpromotionbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            initComponenets();
            prepareView();
            setActionListener();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    private void initComponenets() {
      //  mToolbar = (Toolbar) findViewById(R.id.toolbars);
        mCouponIv = (ImageView) findViewById(R.id.acti_main_iv_coupon);
        mHomeIv = (ImageView) findViewById(R.id.acti_main_iv_home);
        mPromotionsIv = (ImageView) findViewById(R.id.acti_main_iv_promotion);
        mGiftCardsIv = (ImageView) findViewById(R.id.acti_main_iv_giftcards);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mHomebtn=(Button)findViewById(R.id.acti_main_btn_home);
        mCouponbtn=(Button)findViewById(R.id.acti_main_btn_coupon);
        mpromotionbtn=(Button)findViewById(R.id.acti_main_btn_promotion);
        mgiftcardbtn=(Button)findViewById(R.id.acti_main_btn_giftcard);

      //  spinnerProfileActivity=(Spinner)findViewById(R.id.spinner_profile);
        adapter = new PagerAdapter(getSupportFragmentManager(), 4);
        mViewPager.setAdapter(adapter);
        mProfileContentIv=(ImageView)findViewById(R.id.imgProflieContent);
        mSearchIv=(ImageView)findViewById(R.id.imgSearch);
        mSearchEt=(EditText)findViewById(R.id.editText_search);

        layoutSearch=(LinearLayout)findViewById(R.id.layout_searchcoupon);

        //@ali initialize line
        lineCoupon=(View)findViewById(R.id.line_coupon);
        lineGiftCard=(View)findViewById(R.id.line_giftCard);
        lineLoyalty=(View)findViewById(R.id.line_loyalty);
        lineSurvey=(View)findViewById(R.id.line_survey);
    }

    private void prepareView() {
        ArrayAdapter<CharSequence>menu=ArrayAdapter.createFromResource(this,R.array.profileactivity,R.layout.support_simple_spinner_dropdown_item);
        menu.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        layoutSearch.setVisibility(View.INVISIBLE);
    }

    private void setActionListener() {
        mCouponIv.setOnClickListener(this);
        mHomeIv.setOnClickListener(this);
        mPromotionsIv.setOnClickListener(this);
        mGiftCardsIv.setOnClickListener(this);
        mHomebtn.setOnClickListener(this);
        mpromotionbtn.setOnClickListener(this);
        mCouponbtn.setOnClickListener(this);
        mgiftcardbtn.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
                if(mViewPager.getCurrentItem()==0){
                    changeColorHome();
                }
                else if(mViewPager.getCurrentItem()==1){
                    changeColorCoupon();
                }
                else if(mViewPager.getCurrentItem()==2){
                    changeColorPromotion();
                }
                else if(mViewPager.getCurrentItem()==3){
                    changeColorGiftCard();
                }
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acti_main_iv_home :
            case R.id.acti_main_btn_home:

                layoutSearch.setVisibility(View.INVISIBLE);

               changeColorHome();
                mViewPager.setCurrentItem(0);
                break;

            case R.id.acti_main_iv_coupon:
            case R.id.acti_main_btn_coupon:
                layoutSearch.setVisibility(View.VISIBLE);
               changeColorCoupon();
                mViewPager.setCurrentItem(1);
                break;
            case R.id.acti_main_iv_promotion:
            case R.id.acti_main_btn_promotion:
                layoutSearch.setVisibility(View.VISIBLE);
                changeColorPromotion();
                mViewPager.setCurrentItem(2);
                break;
            case R.id.acti_main_iv_giftcards:
            case R.id.acti_main_btn_giftcard:
                layoutSearch.setVisibility(View.INVISIBLE);
                changeColorGiftCard();
                mViewPager.setCurrentItem(3);
                break;
            case R.id.imgProflieContent:
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
            case R.id.imgSearch:
               // mSearchEt.setBackgroundColor(Color.BLUE);
                break;

            default:
                break;
        }
    }
    protected  void changeColorHome(){
        layoutSearch.setVisibility(View.INVISIBLE);
        //Line Color
        lineCoupon.setBackgroundColor(Color.parseColor("#333333"));
        lineGiftCard.setBackgroundColor(Color.parseColor("#333333"));
        lineSurvey.setBackgroundColor(Color.WHITE);
        lineLoyalty.setBackgroundColor(Color.parseColor("#333333"));

        //Image and button color
        mCouponIv.setBackgroundColor(Color.parseColor("#777777"));
        mCouponbtn.setTextColor(Color.parseColor("#777777"));
        mHomebtn.setTextColor(Color.parseColor("#ffffff"));
        mHomeIv.setBackgroundColor(Color.parseColor("#ffffff"));
        mpromotionbtn.setTextColor(Color.parseColor("#777777"));
        mPromotionsIv.setBackgroundColor(Color.parseColor("#777777"));
        mGiftCardsIv.setBackgroundColor(Color.parseColor("#777777"));
        mgiftcardbtn.setTextColor(Color.parseColor("#777777"));


        //  lineSurvey.set
        lineLoyalty.setBackgroundColor(Color.parseColor("#333333"));
    }
    protected  void changeColorCoupon(){
        layoutSearch.setVisibility(View.VISIBLE);
        lineCoupon.setBackgroundColor(Color.WHITE);
        lineGiftCard.setBackgroundColor(Color.parseColor("#333333"));
        lineSurvey.setBackgroundColor(Color.parseColor("#333333"));
        lineLoyalty.setBackgroundColor(Color.parseColor("#333333"));

        //Image and button color
        mCouponIv.setBackgroundColor(Color.parseColor("#ffffff"));
        mCouponbtn.setTextColor(Color.parseColor("#ffffff"));
        mHomebtn.setTextColor(Color.parseColor("#777777"));
        mHomeIv.setBackgroundColor(Color.parseColor("#777777"));
        mpromotionbtn.setTextColor(Color.parseColor("#777777"));
        mPromotionsIv.setBackgroundColor(Color.parseColor("#777777"));
        mGiftCardsIv.setBackgroundColor(Color.parseColor("#777777"));
        mgiftcardbtn.setTextColor(Color.parseColor("#777777"));

    }
    protected  void changeColorPromotion(){
        layoutSearch.setVisibility(View.VISIBLE);
        lineCoupon.setBackgroundColor(Color.parseColor("#333333"));
        lineGiftCard.setBackgroundColor(Color.parseColor("#333333"));
        lineSurvey.setBackgroundColor(Color.parseColor("#333333"));
        lineLoyalty.setBackgroundColor(Color.parseColor("#ffffff"));

        //Image and button color
        mCouponIv.setBackgroundColor(Color.parseColor("#777777"));
        mCouponbtn.setTextColor(Color.parseColor("#777777"));
        mHomebtn.setTextColor(Color.parseColor("#777777"));
        mHomeIv.setBackgroundColor(Color.parseColor("#777777"));
        mpromotionbtn.setTextColor(Color.parseColor("#ffffff"));
        mPromotionsIv.setBackgroundColor(Color.parseColor("#ffffff"));
        mGiftCardsIv.setBackgroundColor(Color.parseColor("#777777"));
        mgiftcardbtn.setTextColor(Color.parseColor("#777777"));

    }
    protected  void changeColorGiftCard(){
        layoutSearch.setVisibility(View.INVISIBLE);
        lineCoupon.setBackgroundColor(Color.parseColor("#333333"));
        lineGiftCard.setBackgroundColor(Color.WHITE);
        lineSurvey.setBackgroundColor(Color.parseColor("#333333"));
        lineLoyalty.setBackgroundColor(Color.parseColor("#333333"));

        //Image and button color
        mCouponIv.setBackgroundColor(Color.parseColor("#777777"));
        mCouponbtn.setTextColor(Color.parseColor("#777777"));
        mHomebtn.setTextColor(Color.parseColor("#777777"));
        mHomeIv.setBackgroundColor(Color.parseColor("#777777"));
        mpromotionbtn.setTextColor(Color.parseColor("#777777"));
        mPromotionsIv.setBackgroundColor(Color.parseColor("#777777"));
        mGiftCardsIv.setBackgroundColor(Color.parseColor("#ffffff"));
        mgiftcardbtn.setTextColor(Color.parseColor("#ffffff"));

    }


}
