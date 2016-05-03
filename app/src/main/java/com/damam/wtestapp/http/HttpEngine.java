package com.damam.wtestapp.http;

import android.util.Log;

import com.damam.wtestapp.AppConfig;
import com.damam.wtestapp.io.ListResponse;
import com.damam.wtestapp.listener.GetListListener;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Damam on 03-May-16.
 */
public class HttpEngine {

    private static final String TAG = HttpEngine.class.getSimpleName();

    private static HttpEngine httpEngine;

    // Retrofit referece obj
    private Retrofit retrofit;

    // Retrofit service Api reference obj
    private GetListApi getListApi;

    private HttpEngine() {
        // retrofit instance given base url and gson converter.
        retrofit = new Retrofit.Builder().baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getListApi = retrofit.create(GetListApi.class);
    }

    public static HttpEngine getInstance() {
        if (httpEngine == null) {
            httpEngine = new HttpEngine();
        }
        return httpEngine;
    }

    public void fetchItemList(final GetListListener getListListener) {
        Log.d(TAG, "Calling fetchItemList...");
        Call<ListResponse> call = getListApi.fetchItemsList();
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                Log.d(TAG, "fetchItemList. onSuccess");
                getListListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.d(TAG, "fetchItemList. onFailure.", t);
                getListListener.onFailure();
            }
        });
    }

}
