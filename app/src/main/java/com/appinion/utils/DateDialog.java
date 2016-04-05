package com.appinion.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by ali on 4/5/2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    String date;
    EditText txtDate;
    public DateDialog(){}
    public DateDialog(View v){
        txtDate=(EditText)v;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,year,month,day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date=dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
        this.date=date;

    }
}
