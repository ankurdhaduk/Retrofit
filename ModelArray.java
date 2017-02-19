package com.example.antop.recylerview;

import java.util.ArrayList;

/**
 * Created by antop on 1/26/2017.
 */
public class ModelArray {

    String strName;
    String strLast;
    int image;
    ArrayList<ModelSecond> stringArrayList;

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrLast() {
        return strLast;
    }

    public void setStrLast(String strLast) {
        this.strLast = strLast;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<ModelSecond> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<ModelSecond> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public ModelArray(String strName, String strLast, int image, ArrayList<ModelSecond> array) {

        this.strName = strName;
        this.strLast=strLast;
        this.image=image;
        this.stringArrayList=array;

    }

}
