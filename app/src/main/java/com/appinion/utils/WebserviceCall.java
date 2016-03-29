package com.appinion.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.appinion.app.R;
import com.appinion.app.UpdateProfileActivity;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by user on 5/5/15.
 */
public class WebserviceCall {
    AsyncHttpClient asyncHttpClient;
    WebserviceResponse webserviceResponse;
    Context context;
    String url;
    ProgressDialog progressDialog;
    String response;

    public WebserviceCall(Context context) {
        this.context = context;
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setConnectTimeout(15000);
        asyncHttpClient.setResponseTimeout(15000);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(null);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");
    }


    public void callWebservice(final String url, String type, RequestParams requestParams, StringEntity entity) {

        this.url = url;
        if (isInternetConnected()) {
            if (type.equalsIgnoreCase("post")) {
                try {
//                    asyncHttpClient.addHeader("Content-Type", "application/json");
                    asyncHttpClient.post(context, url, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            super.onStart();
                            try {
                                progressDialog.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            try {
                                response = new String(bytes);
                                progressDialog.dismiss();
                                Log.e("Success Response", response);
                                webserviceResponse.success(url, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            try {
                                progressDialog.dismiss();
                                webserviceResponse.failure(url, throwable.getLocalizedMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (type.equalsIgnoreCase("get")) {
                asyncHttpClient.get(context, url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        try {
                            progressDialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            progressDialog.dismiss();

                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            progressDialog.dismiss();
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            try {
                Toast.makeText(context, context.getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void callWebserviceWithoutLoader(final String url, String type, RequestParams requestParams) {
        this.url = url;
        if (isInternetConnected()) {
            if (type.equalsIgnoreCase("post")) {
                asyncHttpClient.addHeader("X-API-KEY", Pref.getValue(context, Const.HEADER_KEY, ""));
                asyncHttpClient.post(context, url, requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            Log.e("Success Response", response);
                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            } else if (type.equalsIgnoreCase("get")) {
                asyncHttpClient.get(context, url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            try {
                Toast.makeText(context, context.getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void callWebserviceForPut(final String url, String type, RequestParams requestParams) {
        this.url = url;
        if (isInternetConnected()) {
            if (type.equalsIgnoreCase("put")) {
                asyncHttpClient.put(context, url, requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            Log.e("Success Response", response);
                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } else {
            try {
                Toast.makeText(context, context.getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void callWebserviceWithMultiheader(final String url, String type, RequestParams requestParams) {
        this.url = url;
        if (isInternetConnected()) {
            if (type.equalsIgnoreCase("post")) {
                asyncHttpClient.post(context, url, requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        try {
                            progressDialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            progressDialog.dismiss();
                            Log.e("Success Response", response);
                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            progressDialog.dismiss();
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            } else if (type.equalsIgnoreCase("get")) {
                asyncHttpClient.get(context, url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        try {
                            progressDialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            response = new String(bytes);
                            progressDialog.dismiss();
                            webserviceResponse.success(url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            progressDialog.dismiss();
                            webserviceResponse.failure(url, throwable.getLocalizedMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            try {
                Toast.makeText(context, context.getResources().getString(R.string.nointernet), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public interface WebserviceResponse {
        public void success(String url, String response);

        public void failure(String url, String response);
    }

    public void setWebserviceResponse(WebserviceResponse webserviceResponse) {
        this.webserviceResponse = webserviceResponse;
    }

    public boolean isInternetConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}