package com.example.antop.retrofitdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    private RecyclerView rvVertical;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        rvVertical=(RecyclerView)findViewById(R.id.rvVertical);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVertical.setLayoutManager(linearLayoutManager);
        ApiInterFace apiService =
                ApiClient.getClient().create(ApiInterFace.class);
        Call<ResponseBody> call=apiService.getUser();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                arrayList=new ArrayList<>();
                /*ArrayList<String> strings=new ArrayList<String>();
                try {
                    strings.add(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<strings.size();i++){
                    CatagoryListModelClass c=new CatagoryListModelClass();
*/
                try {
                    arrayList.add(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainActivityAdapter adapter=new MainActivityAdapter(context,arrayList);
                    rvVertical.setAdapter(adapter);
                }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        //Call<ArrayList<CatagoryListModelClass>> call = apiService.getAllFeatureCategory();
        /*call.enqueue(new Callback<ArrayList<CatagoryListModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<CatagoryListModelClass>> call, Response<ArrayList<CatagoryListModelClass>> response) {

                for (int i = 0; i < response.body().size(); i++) {

                    CatagoryListModelClass c = new CatagoryListModelClass();
                    c.setFeaturesCategoryName(response.body().get(i).getFeaturesCategoryName());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<CatagoryListModelClass>> call, Throwable t) {

            }
        });
*/

    }
}
