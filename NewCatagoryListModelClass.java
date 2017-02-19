package com.example.antop.retrofitdemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by antop on 2/11/2017.
 */
public class CatagoryListModelClass {
    private String categoryName;
    private String email;

    public String getName() {
        return categoryName;
    }

    public void setName(String name) {
        this.categoryName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
