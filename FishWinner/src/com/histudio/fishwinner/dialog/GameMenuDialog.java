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

public class GameMenuDialog extends AlertDialog {

	private GameActivity context = null;
	public GameMenuDialog(Context context) {
		super(context);
		this.context = (GameActivity)context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamemenu);
		ImageButton game_return = (ImageButton)findViewById(R.id.game_return);
		ImageButton game_option = (ImageButton)findViewById(R.id.game_option);
		ImageButton return_menu = (ImageButton)findViewById(R.id.return_menu);
		ImageButton game_help = (ImageButton)findViewById(R.id.game_help);
		game_return.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {//回到游戏
				context.removeDialog(Constants.GAME_MENU);
			}
		});
		game_option.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {//游戏设置
				GameView.setMessage(3);
				context.removeDialog(Constants.GAME_MENU);
			}
		});

		game_help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {//游戏帮助
				GameView.setMessage(1);
				context.removeDialog(Constants.GAME_MENU);
			}
		});
		
		return_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {//回主菜单
				GameView.setMessage(2);
				context.removeDialog(Constants.GAME_MENU);
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
