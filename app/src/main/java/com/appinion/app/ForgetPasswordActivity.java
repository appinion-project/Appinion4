package com.appinion.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appinion.utils.MaterialUtils;

/**
 * Created by USER on 3/15/2016.
 */
public class ForgetPasswordActivity extends Activity implements View.OnFocusChangeListener {
    private Button btnForgotEmail;
    private EditText edtForgotEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgot_password);
        intialize();
        setActionListener();

    }



    protected void intialize() {
        btnForgotEmail = (Button) findViewById(R.id.button_foget_email);
        edtForgotEmail = (EditText) findViewById(R.id.edt_forget_email);
    }
    private void setActionListener() {
        btnForgotEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtForgotEmail.getText().equals("")){
                    MaterialUtils.showLongToast(ForgetPasswordActivity.this,"Field can't be empty!");
                }
                else {
                    //Code is here to send email
                }
            }
        });

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt_forget_email:
                if (hasFocus == false) {
                    View checkEmail = (View) findViewById(R.id.v_check_email);
                    if (edtForgotEmail.getText().toString().equals("")) {
                        checkEmail.setBackgroundResource(R.drawable.border_with_circle);
                    } else {
                        checkEmail.setBackgroundResource(R.drawable.border_with_circle_green);
                    }
                }
                break;
        }
    }
}
