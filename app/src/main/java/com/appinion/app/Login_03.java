package com.appinion.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appinion.utils.Const;
import com.appinion.utils.MaterialUtils;
import com.appinion.utils.Pref;
import com.appinion.utils.WebserviceCall;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Rishabh Srivastava on 21-Dec-15.
 */
public class Login_03 extends BaseActivity implements View.OnClickListener, WebserviceCall.WebserviceResponse,View.OnFocusChangeListener {
    private EditText mEmailEdt, mPinEdt;
    private Button mLoginBtn, mForgotPasswordBtn, mRegisterBtn;
    private WebserviceCall webserviceCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login03);
        try {
            initComponenets();
            prepareView();
            setActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponenets() {
        mEmailEdt = (EditText) findViewById(R.id.act_login_edt_email);
        mEmailEdt.setOnFocusChangeListener(this);
        mPinEdt = (EditText) findViewById(R.id.act_login_edt_pin);
        mPinEdt.setOnFocusChangeListener(this);
        mLoginBtn = (Button) findViewById(R.id.act_login_btn_login);
        mForgotPasswordBtn = (Button) findViewById(R.id.act_login_btn_forgotpassword);
        mRegisterBtn = (Button) findViewById(R.id.act_login_btn_register);
        webserviceCall = new WebserviceCall(Login_03.this);
        webserviceCall.setWebserviceResponse(this);



    }

    private void prepareView() {
    }

    private void setActionListener() {
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mForgotPasswordBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_login_btn_login:
                String email = mEmailEdt.getText().toString().trim();
                String pin = mPinEdt.getText().toString().trim();
                if (email.isEmpty() || pin.isEmpty()) {
                    MaterialUtils.showShortToast(Login_03.this, getResources().getString(R.string.loginvalidationmessage));
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    MaterialUtils.showShortToast(Login_03.this, getResources().getString(R.string.invalidemail));
                } else {
                    RequestParams requestParams = new RequestParams();
                    JSONObject requestJObj = null;
                    try {
                        requestJObj = new JSONObject();
                        requestJObj.put("email", email);
                        requestJObj.put("pin", pin);

                        StringEntity entity = new StringEntity(requestJObj.toString());
                        webserviceCall.callWebservice(Const.baseurl + Const.URL_LOGIN, "post", requestParams, entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.act_login_btn_forgotpassword:
                Intent intent=new Intent(Login_03.this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.act_login_btn_register:
                intent = new Intent(Login_03.this, Registration_02.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void success(String url, String response) {
        if (!response.isEmpty()) {
            if (url.equalsIgnoreCase(Const.baseurl + Const.URL_LOGIN)) {
                try {
                    JSONObject responseJson = new JSONObject(response);

                    Pref.setValue(this, "data", responseJson.optString("data"));
                    String responseInResponse=Pref.getValue(this,"data",responseJson.optString("data"));
                    JSONObject responseJsonInJson=new JSONObject(responseInResponse);

                    Pref.setValue(this, Const.USER_ID, responseJsonInJson.optString("id"));
                    Pref.setValue(this, Const.PREF_FIRSTNAME, responseJsonInJson.optString("firstName"));
                    Pref.setValue(this, Const.PREF_LASTNAME, responseJsonInJson.optString("lastName"));
                    Pref.setValue(this, Const.PREF_PHONE, responseJsonInJson.optString("phone"));
                    Pref.setValue(this, Const.PREF_AGE, responseJsonInJson.optString("age"));
                    Pref.setValue(this, Const.PREF_LOCATION, responseJsonInJson.optString("location"));
                    Pref.setValue(this, Const.PREF_PROFILEPICLINK, responseJsonInJson.optString("profilePicLink"));
                    Pref.setValue(this, Const.PREF_PROFILEPICTHUMBLINK, responseJsonInJson.optString("profilePicThumbnailLink"));
                    Pref.setValue(this, Const.PREF_PROFILEBARCODEKEY, responseJsonInJson.optString("profileBarcodeGenKey"));
                    Pref.setValue(this, Const.PREF_LASTLOGIN, responseJsonInJson.optString("lastLogin"));
                    Pref.setValue(this, Const.PREF_LASTUPDATE, responseJsonInJson.optString("lastUpDate"));
                    Pref.setValue(this, Const.PREF_REGISTRATIONDATE, responseJsonInJson.optString("registrationDate"));
                    Pref.setValue(this, Const.PREF_EMAIL, responseJsonInJson.optString("email"));
                    Pref.setValue(this, Const.PREF_PIN, responseJsonInJson.optString("pin"));

                    Intent intent = new Intent(Login_03.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Pref.setValue(Login_03.this, Const.Is_LOGIN, true);
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
            case R.id.act_login_edt_email:
                if(hasFocus==false){
                    View checkEmail=(View)findViewById(R.id.v_check_email);
                    if(mEmailEdt.getText().toString().equals("")){
                       // checkEmail.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                       // checkEmail.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }
                break;

            case R.id.act_login_edt_pin:
                if(hasFocus==false){
                    View checkPin=(View)findViewById(R.id.v_check_pin);
                    if(mPinEdt.getText().toString().equals("")){
                        //checkPin.setBackgroundResource(R.drawable.border_with_circle);
                    }

                    else
                    {
                       // checkPin.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }
                break;

        }
    }
}
