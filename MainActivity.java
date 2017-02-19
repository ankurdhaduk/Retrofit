package com.example.antop.recylerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antop.recylerview.Adapter.ProductImageAdapter;
import com.example.antop.recylerview.Adapter.ProductNameAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvVertical;
    private Context context;
    private ArrayList<ModelArray> modelArrayArrayList=new ArrayList<ModelArray>();
    private ArrayList<ModelSecond>array=new ArrayList<ModelSecond>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        rvVertical=(RecyclerView)findViewById(R.id.rvVertical);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVertical.setLayoutManager(linearLayoutManager);

        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));
        modelArrayArrayList.add(new ModelArray("Ankur", "Dhaduk", R.mipmap.ic_launcher, array));

        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));
        array.add(new ModelSecond("Paresh",R.mipmap.ic_launcher));

        ProductNameAdapter adapter=new ProductNameAdapter(context,modelArrayArrayList);
        rvVertical.setAdapter(adapter);

        }


    }
