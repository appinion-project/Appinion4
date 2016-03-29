package com.appinion.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appinion.utils.Const;
import com.appinion.utils.MaterialUtils;
import com.appinion.utils.Pref;
import com.appinion.utils.WebserviceCall;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Ali Ahmed on 2/14/2016.
 */
public class UpdateProfileActivity extends AppCompatActivity implements WebserviceCall.WebserviceResponse{
    EditText etFirstName;
    EditText etLastName;
    EditText etLocation;
    EditText etPhone;
    private int check;
    private WebserviceCall webserviceCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        initialize();
        preparedView();

    }
    protected void initialize(){
        etFirstName=(EditText)findViewById(R.id.editText_FirstName);
        etLastName=(EditText)findViewById(R.id.editText_LastName);
        etLocation=(EditText)findViewById(R.id.editTextLocation);
        etPhone=(EditText)findViewById(R.id.editTextPhone);
        webserviceCall = new WebserviceCall(UpdateProfileActivity.this);
        webserviceCall.setWebserviceResponse(this);
    }
    protected void preparedView(){
        JSONObject response=new JSONObject();
        //etFirstName.setText(Pref.getValue(this,"hello","Default"));
        etFirstName.setText(Pref.getValue(this, Const.PREF_FIRSTNAME,response.optString("firstName")));
        etLastName.setText(Pref.getValue(this,Const.PREF_LASTNAME,response.optString("lastName")));
        etPhone.setText(Pref.getValue(this,Const.PREF_PHONE,response.optString("phone")));
        etLocation.setText(Pref.getValue(this,Const.PREF_LOCATION,response.optString("location")));
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_Update:
                RequestParams requestParams=new RequestParams();
                JSONObject requestJOB=null;
                try{

                    requestJOB=new JSONObject();
                    requestJOB.put("userid",Pref.getValue(this, Const.USER_ID, requestJOB.optString("userid")));
                    requestJOB.put("email", Pref.getValue(this, Const.PREF_EMAIL, requestJOB.optString("email")));
                    requestJOB.put("pin", Pref.getValue(this, Const.PREF_PIN, requestJOB.optString("pin")));
                    requestJOB.put("age", Pref.getValue(this, Const.PREF_AGE, requestJOB.optString("age")));
                    requestJOB.put("firstname", etFirstName.getText().toString().trim());
                    requestJOB.put("lastname", etLastName.getText().toString().trim());
                    requestJOB.put("phone", etPhone.getText().toString().trim());
                    requestJOB.put("location", etLocation.getText().toString().trim());

                    StringEntity entity=new StringEntity(requestJOB.toString());

                    final  String url=Const.baseurl + Const.URL_UPDATE;
                    webserviceCall.callWebservice(url, "post", requestParams, entity);
                }
                catch(Exception e){
                    MaterialUtils.showLongToast(this,"Exception");
                }

        }
    }

    @Override
    public void success(String url, String response) {
        Intent intent=new Intent(UpdateProfileActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void failure(String url, String response) {
        MaterialUtils.showLongToast(this,"Update can't possible now.Please try later.");
    }
}
