package me.xiba.startlearnmvvm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import me.xiba.startlearnmvvm.MyApplication;

/**
 * Created by liukun on 2017/12/21.
 */

public class SysUtils {

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetWorkConnected() {
        Context context = MyApplication.getInstance().getApplicationContext();
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo == null ? false : mNetworkInfo.isAvailable();
    }
}
