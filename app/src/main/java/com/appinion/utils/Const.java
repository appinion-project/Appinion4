package com.appinion.utils;

import android.annotation.SuppressLint;
import android.os.Environment;

@SuppressLint("SdCardPath")
public class Const {


    //This is the base url for the webservices.
    public static final String baseurl = "http://ec2-54-201-186-73.us-west-2.compute.amazonaws.com:8080/webservice/";
    public static final String URL_REGISTER = "register";
    public static final String URL_LOGIN = "validate";


    //@ali updateUrl
    public static final String URL_UPDATE="user?operation=update";
    public static final String URL_UPDATE_PROFILE="";


    public static boolean ISONCHATSCREEN = false;
    public static int selectedTab = 0;

    //Shared Preference
    public static final String USER_ID = "userid";
    public static final String PREF_FIRSTNAME = "firstname";
    public static final String PREF_LASTNAME = "lastname";
    public static final String PREF_PHONE = "phone";
    public static final String PREF_AGE = "age";
    public static final String PREF_LOCATION = "location";
    public static final String PREF_PROFILEPICLINK = "profilepiclink";
    public static final String PREF_PROFILEPICTHUMBLINK = "profilepiolinkthumb";
    public static final String PREF_LASTLOGIN = "lastlogin";
    public static final String PREF_LASTUPDATE = "lastupdate";
    public static final String PREF_REGISTRATIONDATE = "regdate";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_PIN = "pin";
    public static final String PREF_PROFILEBARCODEKEY = "profilebarcodekey";


    public static boolean IS_ALLOWED = false;
    public static final String HEADER_KEY = "headerkey";

    public static final String ACCESS_TOKEN = "accesstoken";
    public static final String NOTIFICATION = "notification";
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public final static String UPDT_STAT = "com.ex";
    public final static String PREF_GCM_REG_ID = "gcmregid";
    public final static String PREF_PROPERTY_APP_VERSION = "propertyappversion";
    public final static String GCM_SENDER_ID = "gcmsenderid";
    public static final String PREF_FILE = "APPINION_DEV_PREF";

    public static String Is_LOGIN = "islogin";
    public static String APP_HOME = Environment.getExternalStorageDirectory().getPath() + "/Appinion";
    public static String DIR_APP_DATA = APP_HOME + "/.AppData";
    public static String DIR_MEDIA = APP_HOME + "/Media";
    /**
     * ********* Child dir of DIR_APP_DATA *************
     */

    public static String DIR_APP_LOG = DIR_APP_DATA + "/Log";
    public static String DIR_APP_IMAGES = DIR_APP_DATA + "/AppImages";
    public static String DIR_APP_VIDEO = DIR_APP_DATA + "/AppVideos";
    public static String DIR_APP_AUDIO = DIR_APP_DATA + "/AppAudio";
}
