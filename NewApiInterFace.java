package com.example.antop.retrofitdemo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by antop on 2/11/2017.
 */
public interface ApiInterFace {

    @GET("person_array.json")
    Call<ResponseBody>getUser();
}
