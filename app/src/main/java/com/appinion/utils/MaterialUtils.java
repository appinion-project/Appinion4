package com.appinion.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by user on 28/5/15.
 */
public class MaterialUtils {
    static Dialog dialog;
    static AlertDialog.Builder builder;
    static LocationManager mlocManager;

    public static void showAlert(Context contex, String message) {
        try {
            try {
                builder = new AlertDialog.Builder(contex);
                builder.setMessage(message);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(Context contex, String message, final GetBuilderClick builderClick) {
        try {
            builder = new AlertDialog.Builder(contex);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    builderClick.onPositiveButtonClick();
                }
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(Context contex, String message, boolean hasTwoButtons, final GetBuilderClick builderClick) {
        try {
            builder = new AlertDialog.Builder(contex);
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    builderClick.onPositiveButtonClick();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    builderClick.onNegativeButtonClick();
                }
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSettingsAlert(Context contex, String message, boolean hasTwoButtons, final GetBuilderClick builderClick) {
        try {
            builder = new AlertDialog.Builder(contex);
            builder.setMessage(message);
            builder.setPositiveButton("SETTINGS", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    builderClick.onPositiveButtonClick();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    builderClick.onNegativeButtonClick();
                }
            });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dismissProgress(Context context) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShortToast(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLongToast(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String removeCommaFromFirstLetter(String string) {
        if (string.equalsIgnoreCase("")) {
            return string;
        } else {
            if (string.charAt(0) == ',') {
                string = string.substring(1);
            } else {
                return string;
            }
        }
        return string;
    }

    public static Integer stringToInt(String numberString) {
        try {
            return Integer.parseInt(numberString);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Double stringToDouble(String numberString) {
        try {
            return Double.parseDouble(numberString);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static Boolean stringToBoolean(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return false;
        }
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String getBudget(String code) {
        if (code.equalsIgnoreCase("1")) {
            return "£";
        } else if (code.equalsIgnoreCase("2")) {
            return "££";
        } else if (code.equalsIgnoreCase("3")) {
            return "£££";
        }
        return "";
    }

    public static String replaceCommaInAddress(String str) {
        try {
            return str.replace(",", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTrueFalse(String str) {
        try {
            if (str.equalsIgnoreCase("0")) {
                return "false";
            } else {
                return "true";
            }
        } catch (Exception e) {
            return "false";
        }
    }

    public static Date addMinutesToDate(String stringDate, int minutes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            Date date = dateFormat.parse(stringDate);
            long t = date.getTime();
            return new Date(t + (minutes * 60000));

        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String ChangeDateFormat(String mydate) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            Date date = dateFormat.parse(mydate);
            return dateFormat2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return mydate;
        }
    }

    public static String UTCtoLocalDateOrder(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat changedDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            TimeZone utcZone = TimeZone.getTimeZone("UTC");
            simpleDateFormat.setTimeZone(utcZone);
            Date dateUTC = simpleDateFormat.parse(date);
            changedDateFormat.setTimeZone(TimeZone.getDefault());
            return changedDateFormat.format(dateUTC);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    public static String getDateFromTimeStamp(String dateString) {
        if (!dateString.equalsIgnoreCase("")) {
            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                date = sdf.parse(dateString);
                return new SimpleDateFormat("dd/MM/yyyy").format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return date.toString();
        }
        return "";
    }

    public static String UTCtoLocalDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone utcZone = TimeZone.getTimeZone("UTC");
            simpleDateFormat.setTimeZone(utcZone);
            Date dateUTC = simpleDateFormat.parse(date);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(dateUTC);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }


    public static String localtoUTCDate(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return date.toString();
        }
    }

    public static Date UTCtoLocalTime(String time) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
            //TimeZone utcZone = TimeZone.getTimeZone("UTC");
            //  simpleDateFormat.setTimeZone(utcZone);
            //  Date dateUTC = simpleDateFormat.parse(time);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.parse(simpleDateFormat.format(simpleDateFormat.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String ampmTo24(String time) {
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            Date date = parseFormat.parse(time);
            return displayFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
    }

    public static Date getCurrentTime() {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            return sdf.parse(sdf.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void hideKeyboard(Activity mActivity) {
        // Check if no view has focus:
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            view.clearFocus();
            InputMethodManager inputManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboardWithDelay(final Activity mActivity) {
        // Check if no view has focus:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideKeyboard(mActivity);
            }
        }, 250);
    }

    public static void makeCaps(EditText etext, Editable medtb) {
        try {
            if (!medtb.toString().isEmpty()) {
                String mEdttxt = medtb.toString();
                String firstChar = String.valueOf(mEdttxt.charAt(0));
                if (!firstChar.equals(firstChar.toUpperCase())) {

                    mEdttxt = mEdttxt.substring(0, 1).toUpperCase() + mEdttxt.substring(1);
                    etext.setText(mEdttxt);
                    etext.setSelection(firstChar.length());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String makeFirstLetCaps(String mText) {
        try {
            if (!TextUtils.isEmpty(mText)) {
                String firstChar = String.valueOf(mText.charAt(0));
                if (!firstChar.equals(firstChar.toUpperCase())) {
                    return mText.substring(0, 1).toUpperCase() + mText.substring(1);
                } else {
                    return mText;
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mText;
        }

    }

    public static boolean isGPSEnabled(final Context context) {
        try {
            int locationMode = 0;
            String locationProviders;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return locationMode != Settings.Secure.LOCATION_MODE_OFF;

            } else {
                locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
                return !TextUtils.isEmpty(locationProviders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String itemOrItems(String count) {
        try {
            return Integer.parseInt(count) > 1 ? " Items" : " Item";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return count;
        }
    }

    public interface GetBuilderClick {
        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }

}
