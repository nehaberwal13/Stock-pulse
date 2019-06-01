package com.app.criteria_parser.data.remote;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class initializes retrofit with a default configuration.
 * You can use this class to initialize the different services.
 */

public class RetrofitHelper {

    @Inject
    public RetrofitHelper() {
    }

    public ApiHelper getApiHelper() {
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(ApiHelper.class);
    }

    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();

            final HttpUrl url = originalHttpUrl.newBuilder().build();

            // Request customization: add request headers
            final Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            final Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return httpClient.build();
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient())
                .baseUrl(ApiEndPoint.ENDPOINT_SCAN_DATA_REQUEST)
                .build();
    }
}
