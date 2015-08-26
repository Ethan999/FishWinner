package com.histudio.fishwinner.screen;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameActivity;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.UIUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class WelcomeScreen extends GameScreen{

	private Bitmap logo;
	private long time = 0;
	
	
	@Override
	public void init() {
		context = GameActivity.getInstance();
		paint.setColor(Color.RED);
		
		//Í¼Æ¬¼ÓÔØ
		logo = UIUtil.loadBitmapByResize(context, R.drawable.title);
	}

	@Override
	public void render(Canvas c) {
		
		c.drawBitmap(logo,(width-logo.getWidth())>>1, 
				(height-logo.getHeight())>>2, paint);
	}

	@Override
	public void handleEvent() {
		
	}

	@Override
	public void update() {
		
		if (time == 0) time = System.currentTimeMillis();
		if((System.currentTimeMillis() - time) >= 2000){
			GameView.setGameScreen(new MenuScreen());
		}
		
	}

	@Override
	public void destroy() {
		
		logo = null;
		System.gc();
		
	}


}
