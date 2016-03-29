package com.appinion.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by user on 26/6/15.
 */
public class InternetDetector extends BroadcastReceiver {
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        //  Intent intent1 = new Intent();
        //  intent1.setAction(Const.CUSTOM_INTENT);
        if (haveConnectedWifi || haveConnectedMobile) {
            //  intent1.putExtra("status", "false");
            Toast.makeText(context, "Net aa Gaya", Toast.LENGTH_SHORT).show();
        } else {
            // intent1.putExtra("status","true");
            Toast.makeText(context, "Net Gaya", Toast.LENGTH_SHORT).show();
        }
        // context.sendBroadcast(intent1);
    }
}
