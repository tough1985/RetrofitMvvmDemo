package me.xiba.startlearnmvvm.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukun on 2017/12/21.
 */

public class RetrofitHolder {

    private String mBaseUrl = "https://api.douban.com";

    private volatile static Retrofit sRetrofit;
    private OkHttpClient mClient;

    private RetrofitHolder() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        sRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getClient(){

        if (mClient == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            mClient = builder
                    .addInterceptor(new LogInterceptor())
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return mClient;
    }

    public static Retrofit getRetrofitInstance(){
        if (sRetrofit == null) {
            synchronized (RetrofitHolder.class){
                if (sRetrofit == null) {
                    new RetrofitHolder();
                }
            }
        }
        return sRetrofit;
    }
}
