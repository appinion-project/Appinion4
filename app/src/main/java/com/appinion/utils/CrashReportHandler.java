package com.appinion.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashReportHandler implements UncaughtExceptionHandler {

    private Context m_context;

    public static void attach(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashReportHandler(context));
    }

    private CrashReportHandler(Context context) {
        m_context = context;
    }

    @SuppressLint("SimpleDateFormat")
    public void uncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        if (NetworkReachability.isNetworkAvailable()) {
            Intent i = new Intent(Intent.ACTION_SEND);
            // i.setType("text/plain"); //use this line for testing in the
            // emulator
            i.setType("message/rfc822"); // use from live device
            i.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"rishabhsri20@yahoo.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Application Error Mail");
            i.putExtra(Intent.EXTRA_TEXT, "Send email error to developer for resolved this error " + currentDateandTime + "\n" + stackTrace.toString());
            // File logFile = new File(Const.DIR_APP_LOG +
            // "/CONVERZA_APP_VERSION_" + MyUtils.getAppVersion(m_context) + "_"
            // + new SimpleDateFormat("yyyy_MM_dd").format(new Date()) +
            // ".html");
            // Uri u = Uri.fromFile(logFile);
            // i.putExtra(Intent.EXTRA_STREAM, u);
            m_context.startActivity(i);

        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}