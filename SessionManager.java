package com.example.antop.loginactivitydemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by antop on 2/17/2017.
 */
public class SessionManager {
    private static final String PREF_NAME="Login Session";
    private static final String KEY_ID="id";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_PROFILE="profile";

    public static void Logout(Context context){
        try {
            SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,0).edit();
            editor.clear();
            editor.commit();
        }catch (Exception e){e.printStackTrace();}
    }
    public static void setKeyEmail(Context context,String strEmail){
        try {
            SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,0).edit();
            editor.putString(KEY_EMAIL,strEmail );
            editor.commit();
        }catch (Exception e){e.printStackTrace();}
    }
    public static String getKeyEmail(Context context){
        try {
            String strEmail=context.getSharedPreferences(PREF_NAME,0).getString(KEY_EMAIL, "");
            return strEmail;
        }catch (Exception e){e.printStackTrace(); return "";}
    }
    public static void setKeyPassword(Context context,String strPassword){
        try{
            SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,0).edit();
            editor.putString(KEY_PASSWORD,strPassword);
            editor.commit();
        }catch (Exception e){e.printStackTrace();}
    }
    public static String getKeyPassword(Context context){
        try {
            String strPassword=context.getSharedPreferences(PREF_NAME,0).getString(KEY_PASSWORD, "");
            return strPassword;
        }catch (Exception e){e.printStackTrace();return "";}
    }/*
    public static void setKEY_ID(Context context,String strId){
        try{
            SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,0).edit();
            editor.putString(strId, KEY_ID);
            editor.commit();
        }catch (Exception e){e.printStackTrace();}
    }
    public static String getKeyId(Context context){
        try {
            String strId=context.getSharedPreferences(PREF_NAME,0).getString(KEY_ID, "");
            return strId;
        }catch (Exception e){e.printStackTrace();return "";}
    }*/
    public static void setKeyId(Context context,String strName){
        try{SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,0).edit();
            editor.putString(KEY_ID,strName);
            editor.commit();
        }catch (Exception e){e.printStackTrace();}
    }
    public static String getKeyId(Context context){
        try{
            String strName=context.getSharedPreferences(PREF_NAME,0).getString(KEY_ID,"");
            return strName;
        }catch (Exception e){e.printStackTrace();return "";}
    }
}
