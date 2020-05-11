package sdk;


import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sdk.client.ClientConfig;

public class Invoker {
    private static final MediaType JSON_TYPE
            = MediaType.get("application/json; charset=utf-8");

    private final ClientConfig config;
    private final OkHttpClient client;

    public Invoker(ClientConfig clientConfig) {
        this.config = clientConfig;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(config.isRetryOnConnectionFailure())
                .connectionPool(new ConnectionPool(config.getConnectionPoolSize(), 5, TimeUnit.MINUTES))
                .build();
    }

    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(config.getPrefixUrl() + path)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String path, Object requestObject) throws IOException {
        RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(requestObject));
        Request request = new Request.Builder()
                .url(config.getPrefixUrl() + path)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
