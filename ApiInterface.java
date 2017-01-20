package com.example.antop.ecommerce_app.Retrofit;

import com.example.antop.ecommerce_app.Bean.AllCategoryList;
import com.example.antop.ecommerce_app.Bean.AllFeatureCategory;
import com.example.antop.ecommerce_app.Bean.FeatureProduct;
import com.example.antop.ecommerce_app.Bean.ImageList;
import com.example.antop.ecommerce_app.Bean.ProductItem;
import com.example.antop.ecommerce_app.Bean.ProductListSub;
import com.example.antop.ecommerce_app.Bean.RegisterUser;
import com.example.antop.ecommerce_app.Bean.SubCategory;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by antop on 1/14/2017.
 */
public interface ApiInterface {

    public static String BASE_URL = "http://itechnotion.in/ecommerce/index.php?route=api";

    @GET("/registerUser/")
    void getRegisterUser(@Query("gcmId") String gcmId , @Query("email") String email , @Query("password") String password , @Query("mobile")String mobile , Callback<RegisterUser>callback);

    @GET("/getAllFeaturesCategoryList")
    void getAllFeatureCategory(Callback<ArrayList<AllFeatureCategory>> callback);

    @GET("/getFeaturesProductById/{id}")
    void getFeatureProduct(@Path("id")String id ,Callback<ArrayList<FeatureProduct>> callback);

    @GET("/getAllCategoryList")
    void getAllCategoryList(Callback<ArrayList<AllCategoryList>>callback);

    @GET("/getSubCategoryByCategoryId/{id}")
    void getSubCategory(@Path("id")String id , Callback<ArrayList<SubCategory>>callback);

    @GET("/getProductListBySubCategoryId/{id}")
    void getProductList(@Path("id")String id , Callback<ArrayList<ProductListSub>>callback);

    @GET("/getProductById/{id}")
    void getProduct(@Path("id")String id , Callback<ArrayList<ProductItem>>callback);

    @GET("/getProductImageById/{id}")
    void getImageProduct(@Path("id")String id , Callback<ArrayList<ImageList>>callback);


}
