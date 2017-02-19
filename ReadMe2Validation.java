package com.rapido.AppUtils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Point;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalData {

//	Facebook
	public static final String FACEBOOK_ID = "180678992291939";

	public static final String APPLICATION_NAME = "Rapido";
	
	public static final String STR_PROGRESS_DIALOG_MESSAGE = "Please Wait...";
	public static final int intFlagShow = 1;
	public static final int intFlagNotShow = 0;
	
	public static final String DIRECTORY_NAME = "Rapido";

	public static final String STR_INTERNET_ALERT_TITLE = "Network Error!";
	public static final String STR_INETRNET_ALERT_MESSAGE = "Something wrong with the internet connection";
	public static final String STR_ALERT_CONFIRM = "Are you sure you want to remove this item from cart?";

	public static final String STR_SELECT_FRIEND_ON_TRACK = "Please select at least 1 friend to track on map";




	public static String GET_DEVICE_ID(Context context) {
		try {
				String AndroidId = Settings.Secure.getString(context.getContentResolver() , Settings.Secure.ANDROID_ID);
			try{
				TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				return AndroidId+"-"+telephonyManager.getDeviceId();
			} catch (Exception e){
				e.printStackTrace();
				return AndroidId;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private static final String VALID_EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	public static boolean checkEmailIsCorrect(String strEmail) {
		try {
			Pattern pattern = Pattern.compile(VALID_EMAIL);
			Matcher matcher = pattern.matcher(strEmail);
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getScreenWidth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size.x;
	}

	public static int getScreenHeight(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size.y;
	}


}