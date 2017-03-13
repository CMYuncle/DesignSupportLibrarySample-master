package com.wuxiaolong.designsupportlibrarysample.API;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wuxiaolong.designsupportlibrarysample.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by IVERSON on 2017/3/13.
 */
public class ApiClient {
    private static ApiClient mInstence;
    private ApiStories longquanyiApi;


    public synchronized static ApiClient getApiServer() {
        if (mInstence == null) {
            mInstence = new ApiClient();
        }
        return mInstence;
    }

    public ApiStories getLongquanRetrofitApi() {
        if (longquanyiApi == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
           Retrofit longquanRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStories.LONGQUANBASEURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            longquanyiApi =  longquanRetrofit.create(ApiStories.class);

        }
        return longquanyiApi;
    }
}
