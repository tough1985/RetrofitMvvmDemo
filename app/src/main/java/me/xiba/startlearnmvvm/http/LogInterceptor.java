package me.xiba.startlearnmvvm.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by liukun on 2017/12/21.
 */

public class LogInterceptor implements Interceptor{

    public static final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originRequest = chain.request();

        Request.Builder requestBuilder = originRequest.newBuilder();

        logRequestBody(originRequest, requestBuilder);

        Request request = requestBuilder.build();

        Response response = chain.proceed(request);
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();

        Log.e(TAG, "request.url()=" + request.url());
        Log.e(TAG, "response.code()=" + response.code());
        Log.e(TAG, "response.body()=" + content);

        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }


    private void logRequestBody(Request request, Request.Builder requestBuilder) {

        String bodyToString = "";
        if ("GET".equals(request.method())) {
            //将参数添加到url后
            bodyToString = urlQueryToString(request);
        } else {
            //将参数转化为Map，添加基础参数，在转为body
            bodyToString = bodyToString(request);
        }

        Log.e(TAG, "request.body()=" + bodyToString);

        if ("GET".equals(request.method())) {
            //将map在拼回url
            HttpUrl url = request.url().newBuilder().encodedQuery(bodyToString).build();

            requestBuilder.url(url);
        } else {
            //将map转化为body
            requestBuilder.post(RequestBody.create(request.body().contentType(), bodyToString)).build();
        }
    }

    /**
     * 获取get请求中的请求参数
     *
     * @param request
     * @return
     */
    private String urlQueryToString(Request request) {
        if (request == null) {
            return "";
        }

        HttpUrl httpUrl = request.url();
        return httpUrl.query();
    }

    /**
     * 将requestBody转换为String
     *
     * @param request
     * @return
     */
    private String bodyToString(Request request) {
        if (request == null) {
            return "";
        }
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            return "";
        }
    }

}
