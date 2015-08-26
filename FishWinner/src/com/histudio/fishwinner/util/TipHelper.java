package com.histudio.fishwinner.util;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

public class TipHelper {

	private static boolean tipSwitch = false; 
	
	  public static void Vibrate(final Activity context, long milliseconds) {
	   if (tipSwitch) {
		Vibrator vib = (Vibrator) context
				.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	   }
	 }

	public static boolean isTipSwitch() {
		return tipSwitch;
	}

	public static void setTipSwitch(boolean tipSwitch) {
		TipHelper.tipSwitch = tipSwitch;
	}
	  
	  
}
