package com.vslimit.kotlindemo.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created by vslimit on 14-4-25.
 */
public class NetworkUtil {

    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isNetwork(Context context) {
        return isWifi(context) || isMobile(context);
    }

    public static boolean isGps(Context context) {
        LocationManager lm = ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);

        return accessibleProviders != null && accessibleProviders.size() > 0;
    }



}
