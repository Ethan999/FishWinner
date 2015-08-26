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

public class FailDialog extends AlertDialog {

	private GameActivity context = null;
	public FailDialog(Context context) {
		super(context);
		this.context = (GameActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fail);
		ImageButton ask_ok = (ImageButton) findViewById(R.id.d_ok);
		ImageButton ask_can = (ImageButton) findViewById(R.id.d_can);
		
		ask_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				context.removeDialog(Constants.FAIL_DIALOG);
				GameView.setMessage(5);
			}
		});
		ask_can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				context.removeDialog(Constants.FAIL_DIALOG);
				GameView.setMessage(2);
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
