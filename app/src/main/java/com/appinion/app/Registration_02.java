package com.appinion.app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appinion.utils.Const;
import com.appinion.utils.MaterialUtils;
import com.appinion.utils.Pref;
import com.appinion.utils.WebserviceCall;
import com.appinion.utils.WebserviceCall.WebserviceResponse;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Ali Ahmed on 2/12/2016.
 */
public class Registration_02 extends BaseActivity implements WebserviceResponse,View.OnFocusChangeListener{
    private EditText mFnameEdt, mLnameEdt, mEmailEdt, mPhoneEdt, mPinEdt, mAgeEdt, mLocationEdt;
    private Button mSubmitBtn;
    private View check_fname;
    private WebserviceCall webserviceCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration02);
        try {
            initComponenets();
            prepareView();
            setActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise the components
     */
    private void initComponenets() {
        check_fname=(View)findViewById(R.id.checkFname);
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffffcc00});
        mFnameEdt = (EditText) findViewById(R.id.reg_edt_fname);
        mFnameEdt.setOnFocusChangeListener(this);
      //  mFnameEdt.getOnFocusChangeListener(new )
        mLnameEdt = (EditText) findViewById(R.id.reg_edt_lname);
        mLnameEdt.setOnFocusChangeListener(this);
        mEmailEdt = (EditText) findViewById(R.id.reg_edt_email);
        mEmailEdt.setOnFocusChangeListener(this);
        mPhoneEdt = (EditText) findViewById(R.id.reg_edt_phone);
        mPhoneEdt.setOnFocusChangeListener(this);
        mPinEdt = (EditText) findViewById(R.id.reg_edt_pin);
        mPinEdt.setOnFocusChangeListener(this);
        mAgeEdt = (EditText) findViewById(R.id.reg_edt_age);
        mAgeEdt.setOnFocusChangeListener(this);
        mLocationEdt = (EditText) findViewById(R.id.reg_edt_location);
        mLocationEdt.setOnFocusChangeListener(this);
        mSubmitBtn = (Button) findViewById(R.id.reg_btn_submit);
        webserviceCall = new WebserviceCall(this);
        webserviceCall.setWebserviceResponse(this);

    }


    /**
     * Prepare the view(changing the text during runtime or the color)
     */
    private void prepareView() {

    }

    /**
     * Set action listener to the components
     */
    private void setActionListener() {
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName, lName, email, phone, pin, age, location;
                fName = mFnameEdt.getText().toString().trim();
                lName = mLnameEdt.getText().toString().trim();
                email = mEmailEdt.getText().toString().trim();
                phone = mPhoneEdt.getText().toString().trim();
                pin = mPinEdt.getText().toString().trim();
                age = mAgeEdt.getText().toString().trim();
                location = mLocationEdt.getText().toString().trim();

                if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || pin.isEmpty() || age.isEmpty() || location.isEmpty()) {
                    MaterialUtils.showShortToast(Registration_02.this, getResources().getString(R.string.registervalidationmessage));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    MaterialUtils.showShortToast(Registration_02.this, getResources().getString(R.string.invalidemail));
                } else {
                    RequestParams requestParams = new RequestParams();
                    JSONObject requestJObj = null;
                    try {
                        requestJObj = new JSONObject();
                        requestJObj.put("email", email);
                        requestJObj.put("pin", pin);
                        requestJObj.put("age", age);
                        requestJObj.put("firstName", fName);
                        requestJObj.put("lastName", lName);
                        requestJObj.put("location", location);
                        requestJObj.put("phone", phone);
                        StringEntity entity = new StringEntity(requestJObj.toString());
                        webserviceCall.callWebservice(Const.baseurl + Const.URL_REGISTER, "post", requestParams, entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void success(String url, String response) {
        if (!response.isEmpty()) {
            if (url.equalsIgnoreCase(Const.baseurl + Const.URL_REGISTER)) {
                try {
                    JSONObject responseJson = new JSONObject(response);

                    Pref.setValue(this, Const.USER_ID, responseJson.optString("id"));
                    Pref.setValue(this, Const.PREF_FIRSTNAME, responseJson.optString("firstName"));
                    Pref.setValue(this, Const.PREF_LASTNAME, responseJson.optString("lastName"));
                    Pref.setValue(this, Const.PREF_PHONE, responseJson.optString("phone"));
                    Pref.setValue(this, Const.PREF_AGE, responseJson.optString("age"));
                    Pref.setValue(this, Const.PREF_LOCATION, responseJson.optString("location"));
                    Pref.setValue(this, Const.PREF_PROFILEPICLINK, responseJson.optString("profilePicLink"));
                    Pref.setValue(this, Const.PREF_PROFILEPICTHUMBLINK, responseJson.optString("profilePicThumbnailLink"));
                    Pref.setValue(this, Const.PREF_PROFILEBARCODEKEY, responseJson.optString("profileBarcodeGenKey"));
                    Pref.setValue(this, Const.PREF_LASTLOGIN, responseJson.optString("lastLogin"));
                    Pref.setValue(this, Const.PREF_LASTUPDATE, responseJson.optString("lastUpDate"));
                    Pref.setValue(this, Const.PREF_REGISTRATIONDATE, responseJson.optString("registrationDate"));
                    Pref.setValue(this, Const.PREF_EMAIL, responseJson.optString("email"));
                    Pref.setValue(this, Const.PREF_PIN, responseJson.optString("pin"));

                    Intent intent = new Intent(Registration_02.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Pref.setValue(Registration_02.this, Const.Is_LOGIN, true);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void failure(String url, String response) {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()){
            case R.id.reg_edt_fname:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkFname);
                    if(mFnameEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }
                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }
                break;

            case R.id.reg_edt_lname:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkLname);
                    if(mLnameEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }
                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }
                break;

            case R.id.reg_edt_age:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkAge);
                    if(mAgeEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }


                break;

            case R.id.reg_edt_email:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkEmail);
                    if(mEmailEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }


                break;


            case R.id.reg_edt_location:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkLocation);
                    if(mLocationEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }


                break;

            case R.id.reg_edt_pin:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkPin);
                    if(mPinEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }

                }


                break;
            case R.id.reg_edt_phone:
                if(hasFocus==false){
                    View checkView= (View) findViewById(R.id.checkPhone);
                    if(mPhoneEdt.getText().toString().equals("")){
                        checkView.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                        checkView.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }


                break;
        }



    }
}
