package com.appinion.app;

import android.app.Application;

/**
 * Created by user on 18/6/15.
 */
public class BaseApplication extends Application {
    public static BaseApplication mInstance;
    private String dbName, dbSql;
    private int dbVersion;
    private String appName;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appName = "Instantt";
/*
        dbName = appName + ".db";
        dbVersion = 1;
        dbSql = appName + ".sql";

        try {
            dataHelper = new SmartDataHelper(getApplicationContext(), dbName, dbVersion, dbSql);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
