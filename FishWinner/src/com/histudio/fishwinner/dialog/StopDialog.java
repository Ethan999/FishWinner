package com.histudio.fishwinner.dialog;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameActivity;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.Constants;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

public class StopDialog extends AlertDialog {
	
	private GameActivity gameActivity = null;
	public StopDialog(Context context) {
		super(context);
		gameActivity = (GameActivity)context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop);
		
		ImageButton stopbut = (ImageButton)findViewById(R.id.stopbut);
		stopbut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
		    	gameActivity.setPause(false);
		    	if(gameActivity.isMusicState())GameView.getMusicPlayer().restart();
		    	gameActivity.removeDialog(Constants.STOP_DIALOG);
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
