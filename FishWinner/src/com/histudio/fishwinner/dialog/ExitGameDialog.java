package com.histudio.fishwinner.dialog;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameActivity;
import com.histudio.fishwinner.util.Constants;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

public class ExitGameDialog extends AlertDialog {

	private GameActivity context = null;
	public ExitGameDialog(Context context) {
		super(context);
		this.context = (GameActivity)context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exit_game);
		
		ImageButton exit_ok = (ImageButton)findViewById(R.id.exit_ok);
		ImageButton exit_can = (ImageButton)findViewById(R.id.exit_can);
		exit_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				System.exit(0);
			}
		});
		exit_can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				context.removeDialog(Constants.EXITGAME_DIALOG);
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
