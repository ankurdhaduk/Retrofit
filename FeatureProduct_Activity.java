package com.example.antop.ecommerce_app.Util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.antop.ecommerce_app.Adapter.FeatureProductAdapter;
import com.example.antop.ecommerce_app.Bean.FeatureProduct;
import com.example.antop.ecommerce_app.R;
import com.example.antop.ecommerce_app.Retrofit.RestClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FeatureProduct_Activity extends AppCompatActivity {

    GridView gridView ;
    private ArrayList<FeatureProduct> products = new ArrayList<FeatureProduct>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_product_);

        Bundle bundle = new Bundle();
        String strprdct = getIntent().getExtras().getString("ID");

        gridView = (GridView) findViewById(R.id.gridProduct);

        RestClient.get().getFeatureProduct(strprdct, new Callback<ArrayList<FeatureProduct>>() {
            @Override
            public void success(ArrayList<FeatureProduct> featureProducts, Response response) {

                for (int i =0 ; i < featureProducts.size() ; i++){

                    FeatureProduct feature = new FeatureProduct();

                    feature.setPrice(featureProducts.get(i).getPrice());
                    feature.setProductColor(featureProducts.get(i).getProductColor());
                    feature.setProductId(featureProducts.get(i).getProductId());
                    feature.setDescription(featureProducts.get(i).getDescription());
                    feature.setProductimage(featureProducts.get(i).getProductimage());
                    feature.setProductQuantity(featureProducts.get(i).getProductQuantity());
                    feature.setProductSize(featureProducts.get(i).getProductSize());
                    feature.setProductStatusId(featureProducts.get(i).getProductStatusId());
                    feature.setSubcategoryId(featureProducts.get(i).getSubcategoryId());
                    feature.setCategoryId(featureProducts.get(i).getCategoryId());
                    feature.setFeaturesCategoryName(featureProducts.get(i).getFeaturesCategoryName());
                    feature.setFeaturesrelatedcategoryId(featureProducts.get(i).getFeaturesrelatedcategoryId());
                    feature.setFeaturesCategoryId(featureProducts.get(i).getFeaturesCategoryId());
                    feature.setProductName(featureProducts.get(i).getProductName());
                    products.add(feature);
                    FeatureProductAdapter adapter = new FeatureProductAdapter(FeatureProduct_Activity.this , R.layout.featureproduct_item , products);
                    gridView.setAdapter(adapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }
}
