package com.appinion.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.appinion.utils.Const;
import com.appinion.utils.MaterialUtils;
import com.appinion.utils.Pref;
import com.appinion.utils.WebserviceCall;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Ali Ahmed on 2/21/2016.
 */
public class ProfileActivity extends AppCompatActivity implements WebserviceCall.WebserviceResponse,MaterialUtils.GetBuilderClick{
    private int check;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txtAge;
    private TextView txtPhone;
    private ImageView mMainPageIv;
    private ScrollView scrollView;
    private LinearLayout updateLayout,privacyLayout,historyLayout;
    private View updateView,privacyView,historyView;



    private EditText etFirstName;
    private EditText etLastName;
    private EditText etLocation;
    private EditText etPhone;

    private EditText oldPass;
    private EditText newPass;
    private EditText confirmPass;
    private Button submitButton;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    private boolean isValid;

    private WebserviceCall webserviceCall;
    private JSONObject response;
    private Button button_update;
    private Button button_layout_update;
    private Button button_privacy;
    private Button button_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        initialize();
        preparedView();
    }

    protected void initialize(){
        //tamim
        updateLayout=(LinearLayout) findViewById(R.id.update_lay);
        scrollView=(ScrollView) findViewById(R.id.scrollView);
        updateView= LayoutInflater.from(ProfileActivity.this).inflate(R.layout.update_layout,null);
        privacyView=LayoutInflater.from(ProfileActivity.this).inflate(R.layout.privacy_layout,null);

        //tamim
        response=new JSONObject();
        etFirstName=(EditText) updateView.findViewById(R.id.editText_FirstNames);
        etLastName=(EditText) updateView.findViewById(R.id.editText_LastName);
        etLocation=(EditText) updateView.findViewById(R.id.editTextLocation);
        etPhone=(EditText) updateView.findViewById(R.id.editTextPhone);


        button_update=(Button)findViewById(R.id.btn_update);
        button_privacy=(Button)findViewById(R.id.btn_privacy);
        button_history=(Button)findViewById(R.id.btn_history);

        oldPass=(EditText) privacyView.findViewById(R.id.editText_old_Password);
        newPass= (EditText) privacyView.findViewById(R.id.editText_newPassword);
        confirmPass=(EditText) privacyView.findViewById(R.id.editText_confirm);
        submitButton=(Button) privacyView.findViewById(R.id.button_changePassword);


        webserviceCall = new WebserviceCall(ProfileActivity.this);
        webserviceCall.setWebserviceResponse(this);




    }

    protected void getFieldValue(){
        oldPassword=oldPass.getText().toString();
        newPassword=newPass.getText().toString();
        confirmPassword=confirmPass.getText().toString();
        Log.d("TEST", "GETField");
    }

    protected boolean checkPassword(){
        Log.d("TEST", "checkPAss");
        if(confirmPassword.trim().equals(newPassword.trim())){
            return true;
        }
        return false;
    }





    //tamim
    protected void preparedView(){
        etFirstName.setText(Pref.getValue(this, Const.PREF_FIRSTNAME, response.optString("firstName")));
        etLastName.setText(Pref.getValue(this, Const.PREF_LASTNAME, response.optString("lastName")));
        etPhone.setText(Pref.getValue(this,Const.PREF_PHONE,response.optString("phone")));
        etLocation.setText(Pref.getValue(this,Const.PREF_LOCATION,response.optString("location")));
        editTextEnabler(false);
        scrollView.addView(updateView);

    }


    public void onClick(View v){

        switch (v.getId()){
            case R.id.mainPageIV:
                finish();
                break;

            //tamim
            case R.id.btn_history:
                button_update.setBackgroundResource(R.drawable.circle_button_update);
                button_history.setBackgroundResource(R.drawable.circle_button_history_click);
                button_privacy.setBackgroundResource(R.drawable.circle_button_privacy);
                scrollView.removeAllViews();
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
                break;

            //tamim
            case R.id.btn_privacy:
                button_update.setBackgroundResource(R.drawable.circle_button_update);
                button_history.setBackgroundResource(R.drawable.circle_button_history);
                button_privacy.setBackgroundResource(R.drawable.circle_button_privacy_click);
               // button_privacy.setBackgroundResource(R.drawable.c);
                //Toast.makeText(this,"privacy",Toast.LENGTH_LONG).show();
                scrollView.removeAllViews();
                scrollView.addView(privacyView);
                break;

            //tamim
            case R.id.btn_update:
                scrollView.removeAllViews();
                scrollView.addView(updateView);
                editTextEnabler(true);
                button_update.setBackgroundResource(R.drawable.circle_button_update_click);
                button_history.setBackgroundResource(R.drawable.circle_button_history);
                button_privacy.setBackgroundResource(R.drawable.circle_button_privacy);
                break;
            case R.id.button_changePassword:
                this.getFieldValue();
                isValid=this.checkPassword();
                response=new JSONObject();
                if(oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
                    MaterialUtils.showLongToast(this,"Field can't be empty!");
                }
                else if(!oldPassword.equals(Pref.getValue(this, Const.PREF_PIN,response.optString("pin")))){
                    MaterialUtils.showLongToast(this,"Invalid old password!");
                }
                else if(isValid==false){
                    MaterialUtils.showLongToast(this,"Mismatch password!");
                }
                else{
                    check=3;
                    MaterialUtils.showAlert(ProfileActivity.this,"Are you sure?",true,this);
                }
                break;
            case R.id.button_Update:
                check=1;
                MaterialUtils.showAlert(ProfileActivity.this,"Are you sure?",true,this);
                break;

            case R.id.btn_logOut:
                check=2;
                MaterialUtils.showAlert(ProfileActivity.this, "Are you sure?", true, this);
        }
    }

    public void editTextEnabler(boolean flag) {
        etFirstName.setEnabled(flag);
        etLastName.setEnabled(flag);
        etLocation.setEnabled(flag);
        etPhone.setEnabled(flag);
        if(flag==true){
            etFirstName.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etFirstName, InputMethodManager.SHOW_IMPLICIT);
      }
    }
    @Override
    public void success(String url, String response) {
        //Pref.setValue();


        MaterialUtils.showLongToast(ProfileActivity.this,"Successfully Updated");
        Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void failure(String url, String response) {
        MaterialUtils.showLongToast(this, "Update can't possible now.Please try later.");
    }

    @Override
    public void onPositiveButtonClick() {
        switch (check){
            case 1:
                RequestParams requestParams=new RequestParams();
                JSONObject requestJOB=null;
                try{
                    requestJOB=new JSONObject();
                    requestJOB.put("id",Pref.getValue(this, Const.USER_ID, requestJOB.optString("userid")));
                    requestJOB.put("email", Pref.getValue(this, Const.PREF_EMAIL, requestJOB.optString("email")));
                    requestJOB.put("pin", Pref.getValue(this, Const.PREF_PIN, requestJOB.optString("pin")));
                    requestJOB.put("age", Pref.getValue(this, Const.PREF_AGE, requestJOB.optString("age")));
                    requestJOB.put("firstName", etFirstName.getText().toString().trim());
                    requestJOB.put("lastName", etLastName.getText().toString().trim());
                    requestJOB.put("phone", etPhone.getText().toString().trim());
                    requestJOB.put("location", etLocation.getText().toString().trim());

                    StringEntity entity=new StringEntity(requestJOB.toString());
                    final  String url=Const.baseurl + Const.URL_UPDATE;
                    webserviceCall.callWebservice(url, "post", requestParams, entity);
                }
                catch(Exception e){
                    MaterialUtils.showLongToast(this,"Exception");
                }
                break;

            case 2:
                Pref.deletAll(this);
                finish();
                Intent in=new Intent(ProfileActivity.this,Login_03.class);
                startActivity(in);
                break;
            case 3:

                requestParams=new RequestParams();
                requestJOB=null;
                try{
                    requestJOB=new JSONObject();
                    requestJOB.put("id",Pref.getValue(this, Const.USER_ID, requestJOB.optString("userid")));
                    requestJOB.put("email", Pref.getValue(this, Const.PREF_EMAIL, requestJOB.optString("email")));
                    requestJOB.put("pin",newPass.getText().toString().trim());
                    requestJOB.put("age", Pref.getValue(this, Const.PREF_AGE, requestJOB.optString("age")));
                    requestJOB.put("firstName",Pref.getValue(this, Const.PREF_FIRSTNAME, requestJOB.optString("firstName")));
                    requestJOB.put("lastName", Pref.getValue(this, Const.PREF_LASTNAME, requestJOB.optString("lastName")));
                    requestJOB.put("phone", Pref.getValue(this, Const.PREF_PHONE, requestJOB.optString("phone")));
                    requestJOB.put("location",Pref.getValue(this, Const.PREF_LOCATION, requestJOB.optString("location")));

                    StringEntity entity=new StringEntity(requestJOB.toString());
                    final  String url=Const.baseurl + Const.URL_UPDATE;
                    webserviceCall.callWebservice(url, "post", requestParams, entity);
                }
                catch(Exception e) {
                    MaterialUtils.showLongToast(this, "Exception");
                }
        }

    }

    @Override
    public void onNegativeButtonClick() {

    }
}
