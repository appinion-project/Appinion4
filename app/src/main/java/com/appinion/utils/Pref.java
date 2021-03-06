package com.appinion.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class Pref {
    /*---------------String--------------*/
    /*---------------int----------------*/
    /*---------------boolean----------------*/
    /*---------------XML----------------*/
    private static SharedPreferences sharedPreferences = null;

    public static void openPref(Context context) {
        sharedPreferences = context.getSharedPreferences(Const.PREF_FILE, Context.MODE_PRIVATE);

    }

    public static String getValue(Context context, String key,
                                  String defaultValue) {
        try {
            Pref.openPref(context);
            String result = Pref.sharedPreferences.getString(key, defaultValue);
            Pref.sharedPreferences = null;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void setValue(Context context, String key, int value) {
        try {
            Pref.openPref(context);
            Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
            prefsPrivateEditor.putInt(key, value);
            prefsPrivateEditor.commit();
            prefsPrivateEditor = null;
            Pref.sharedPreferences = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getValue(Context context, String key,
                               int defaultValue) {
        try {
            Pref.openPref(context);
            int result = Pref.sharedPreferences.getInt(key, defaultValue);
            Pref.sharedPreferences = null;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void setValue(Context context, String key, String value) {
        try {
            Pref.openPref(context);
            Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
            prefsPrivateEditor.putString(key, value);
            prefsPrivateEditor.commit();
           prefsPrivateEditor = null;
            Pref.sharedPreferences = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getValue(Context context, String key,
                                   boolean defaultValue) {
        try {
            Pref.openPref(context);
            boolean result = Pref.sharedPreferences.getBoolean(key, defaultValue);
            Pref.sharedPreferences = null;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void setValue(Context context, String key, boolean value) {

        try {
            Pref.openPref(context);
            Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
            prefsPrivateEditor.putBoolean(key, value);
            prefsPrivateEditor.commit();
            prefsPrivateEditor = null;
            Pref.sharedPreferences = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletAll(Context context) {
        try {
            Pref.openPref(context);
            Pref.sharedPreferences.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
