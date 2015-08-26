package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.UIUtil;

public class GameOverScreen extends GameScreen{

	private Bitmap img_bg ,img_gameover;
	private long time = 0;
	
	@Override
	protected void init() {
		
		//¼ÓÔØÍ¼Æ¬
		img_bg = UIUtil.loadBitmapByResize(context, R.drawable.ibg);
		img_gameover = UIUtil.loadBitmapByResize(context, R.drawable.gameover);
	}

	@Override
	protected void render(Canvas c) {
		
		c.drawBitmap(img_bg, 0, 0, paint);
		c.drawBitmap(img_gameover, (width-img_gameover.getWidth())>>1, (height-img_gameover.getHeight())>>1, paint);
		
	}

	@Override
	protected void update() {
		
		if (time == 0) time = System.currentTimeMillis();
		if((System.currentTimeMillis() - time) >= 3000){
			GameView.setGameScreen(new MenuScreen());
		}
		
	}

	@Override
	protected void handleEvent() {
	}

	@Override
	protected void destroy() {
		GameView.setFrameTime(100);
		GameView.setGamingScreen(null);
		img_bg = null;
		img_gameover = null;
		System.gc();
	}

	
	
	
}
