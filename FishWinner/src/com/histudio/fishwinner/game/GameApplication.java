package com.histudio.fishwinner.game;

import com.histudio.fishwinner.util.Constants;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GameApplication extends Application {

	private SharedPreferences sp ;
	private Editor editor;
	
	
	@Override
	public void onCreate() {

		sp = getSharedPreferences(Constants.SP_NAME, this.MODE_PRIVATE);
		editor = sp.edit();
		editor.putInt("maxLevel", 2);
		editor.commit();
		
	}
	
}
