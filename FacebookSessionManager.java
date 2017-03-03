package com.example.antop.practisesocialwithlogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by antop on 3/3/2017.
 */
public class SessionManager {
    private static final String PREF_NAME = "Login Session";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PROFILE = "profile";

    public static void logOut(Context context)
    {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setProfile_(Context context, String strProfile)
    {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_PROFILE, strProfile);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProfile(Context context)
    {
        try {
            String strProfile = context.getSharedPreferences(PREF_NAME, 0).getString(KEY_PROFILE, "");
            return strProfile;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setEmail(Context context , String strEmail) {

        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_EMAIL, strEmail);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getEmail(Context context)
    {
        try {
            String strEmail= context.getSharedPreferences(PREF_NAME, 0).getString(KEY_EMAIL, "");
            return strEmail;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void setID_(Context context, String strID)
    {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_ID, strID);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getID(Context context)
    {
        try {
            String strID = context.getSharedPreferences(PREF_NAME, 0).getString(KEY_ID, "");
            return strID;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setName_(Context context, String strName)
    {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_NAME, strName);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getName(Context context)
    {
        try {
            String strName = context.getSharedPreferences(PREF_NAME, 0).getString(KEY_NAME, "");
            return strName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void setPassword_(Context context, String strPassword)
    {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            editor.putString(KEY_PASSWORD, strPassword);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getPassword(Context context)
    {
        try {
            String strPassword = context.getSharedPreferences(PREF_NAME, 0).getString(KEY_PASSWORD, "");
            return strPassword;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
