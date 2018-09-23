package com.ammar.socialpocketa;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitClient {

    //private static final String BASE_URL = "http://192.168.10.5:5000/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    //LoginActivity loginActivity = new LoginActivity();


    public String authToken = LoginActivity.getAuthToken();



    private RetrofitClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", authToken)
                                        .method(original.method(), original.body());
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();


        //Creating a retrofit object
        retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


    }


    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }


    //creating the api interface
    public APIService getApi(){
        return retrofit.create(APIService.class);
    }
}