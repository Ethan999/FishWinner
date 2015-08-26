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

public class AskContinueDialog extends AlertDialog {

	private GameActivity context = null;
	public AskContinueDialog(Context context) {
		super(context);
		this.context = (GameActivity)context;
		System.out.println("continuedialog");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iscontinu);
		ImageButton ask_ok = (ImageButton) findViewById(R.id.ask_ok);
		ImageButton ask_can = (ImageButton) findViewById(R.id.ask_can);
		
		ask_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				GameView.setMessage(4);
				context.removeDialog(Constants.ASK_CONTINUE);
			}
		});
		ask_can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				GameView.setMessage(5);
				context.removeDialog(Constants.ASK_CONTINUE);
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
