package com.appinion.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * Created by Ali Ahmed on 2/18/2016.
 */
public class ChangePasswordActivity extends AppCompatActivity implements WebserviceCall.WebserviceResponse {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private boolean isValid;
    protected Button btnChangePassword;
    protected WebserviceCall webserviceCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Log.d("svjd","dsvdfv");
        try {
            initialize();
            // getFieldValue();
            checkPassword();
        }
        catch (Exception e){}


    }
    protected void initialize(){
        etOldPassword=(EditText)findViewById(R.id.editText_old_Password);
        etNewPassword=(EditText)findViewById(R.id.editText_newPassword);
        etConfirmPassword=(EditText)findViewById(R.id.editText_confirm);
        btnChangePassword=(Button)findViewById(R.id.button_changePassword);
    }
    protected void getFieldValue(){
        oldPassword=etOldPassword.getText().toString();
        newPassword=etNewPassword.getText().toString();
        confirmPassword=etConfirmPassword.getText().toString();
    }

    protected boolean checkPassword(){
       if(oldPassword.trim()==newPassword.trim()){
           return true;
       }
        return false;
    }
    public void onClick(View v){
       // switch (v.getId()){
            //case R.id.button_changePassword:
//                isValid=this.checkPassword();
//                this.getFieldValue();
//                JSONObject response=new JSONObject();
//                if(oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
//                    MaterialUtils.showLongToast(this,"Field can't be empty!");
//                }
//                else if(!oldPassword.equals(Pref.getValue(this, Const.PREF_PIN,response.optString("pin")))){
//                    MaterialUtils.showLongToast(this,"Invalid old password!");
//                }
//                else if(isValid==false){
//                    MaterialUtils.showLongToast(this,"Mismatch password!");
//                }
//                else{
//                    RequestParams requestParams=new RequestParams();
//                    JSONObject requestJOBJ=null;
//                    try{
//                        requestJOBJ=new JSONObject();
//
//                        requestJOBJ.put("userid",Pref.getValue(this, Const.USER_ID, requestJOBJ.optString("userid")));
//                        requestJOBJ.put("email",Pref.getValue(this, Const.PREF_EMAIL, requestJOBJ.optString("email")));
//                        requestJOBJ.put("newPassword",newPassword);
//                        requestJOBJ.put("age",Pref.getValue(this, Const.PREF_AGE, requestJOBJ.optString("age")));
//                        requestJOBJ.put("firstname",Pref.getValue(this, Const.PREF_FIRSTNAME, requestJOBJ.optString("firstname")));
//                        requestJOBJ.put("lastname", Pref.getValue(this, Const.PREF_LASTNAME, requestJOBJ.optString("lastname")));
//                        requestJOBJ.put("location", Pref.getValue(this, Const.PREF_LOCATION, requestJOBJ.optString("location")));
//                        requestJOBJ.put("phone", Pref.getValue(this, Const.PREF_PHONE, requestJOBJ.optString("phone")));
//                        StringEntity entity=new StringEntity(requestJOBJ.toString());
//                        webserviceCall.callWebservice(Const.baseurl+Const.URL_UPDATE,"post",requestParams,entity);
//
//                    }
//                    catch (Exception e){}
//                    //waiting for webservice
//                }
                Toast.makeText(this,"hellooo",Toast.LENGTH_SHORT);

               // break;


       // }
    }

    @Override
    public void success(String url, String response) {
        MaterialUtils.showLongToast(this,"Password change successfully");
        Intent intent=new Intent(ChangePasswordActivity.this,MainActivity.class);
    }

    @Override
    public void failure(String url, String response) {
        MaterialUtils.showLongToast(this,"Password change not complete.Please try again.");
    }
}
