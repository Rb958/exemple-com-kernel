package com.afrikpay.security.utils;

import okhttp3.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtil {

    private HttpUtil(){}

    public static Response sendPOST(String url, Map<String, String> body, Headers headers) throws IOException {
        FormBody.Builder requestBodyBuilder = new FormBody.Builder();
        body.forEach(requestBodyBuilder::add);
        RequestBody requestBody = requestBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(headers)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    public static Response sendPOST(String url, Map<String, String> body) throws IOException {
        FormBody.Builder requestBodyBuilder = new FormBody.Builder();
        body.forEach(requestBodyBuilder::add);
        RequestBody requestBody = requestBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    public static Response sendGET(String url) throws IOException {
        Request request = new Request.Builder()
                .url(URLEncoder.encode(url, StandardCharsets.UTF_8.toString()))
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    public static Response sendGET(String url, Headers headers) throws IOException {
        Request request = new Request.Builder()
                .url(URLEncoder.encode(url, StandardCharsets.UTF_8.toString()))
                .headers(headers)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }
}
