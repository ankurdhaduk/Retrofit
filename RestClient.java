package com.example.antop.ecommerce_app.Retrofit;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by antop on 1/14/2017.
 */
public class RestClient {

    private static ApiInterface REST_CLIENT;
    static
    {
        setupRestClient();
    }

    private RestClient()
    {
    }

    public static ApiInterface get()
    {
        return REST_CLIENT;
    }

    public static ApiInterface post()
    {
        return REST_CLIENT;
    }

    private static void setupRestClient()
    {

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);

        /*Gson gson = new GsonBuilder()
                .registerTypeAdapter(LoginBean.class,new MyDeserializer())
                .create();
*/

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiInterface.BASE_URL)
                .setClient(new OkClient(client))

                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        //.setConverter(new GsonConverter(gson))
        REST_CLIENT = restAdapter.create(ApiInterface.class);/*.setConverter(new GsonConverter(gson))*/


    }

}

