package com.damam.wtestapp.http;

import com.damam.wtestapp.io.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Damam on 03-May-16.
 */
public interface GetListApi {

    String FETCH_ITEMS_LIST = "u/746330/facts.json";

    @GET(FETCH_ITEMS_LIST)
    Call<ListResponse> fetchItemsList();

}
