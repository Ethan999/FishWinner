package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

public class HelpScreen extends GameScreen{

	
	private Bitmap img_bg,img_back,img_back2,img_helBitmap ;
	private ButtonImg butn_back;
	
	
	@Override
	protected void init() {
		
		img_bg = UIUtil.loadBitmapByResize(context,R.drawable.ibg);
		img_helBitmap = UIUtil.loadBitmapByResize(context, R.drawable.helpstring);
		
		//·µ»Ø¼ü
		img_back = UIUtil.loadBitmapByResize(context, R.drawable.iback);
		img_back2 = UIUtil.loadBitmapByResize(context, R.drawable.iback2);
		butn_back = new ButtonImg();
		butn_back.setFirstBitmap(img_back);
		butn_back.setSecondBitmap(img_back2);
		
	}

	@Override
	protected void render(Canvas c) {
		
		c.drawBitmap(img_bg, 0, 0, paint);
		c.drawBitmap(img_helBitmap, 0, 0, paint);
		
		c.drawBitmap(butn_back.getBitmap(), UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		
	}

	@Override
	protected void update() {
	}

	@Override
	protected void handleEvent() {
		
		if(HandleTouch.isTouchBitmap(img_back, 
				UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_back.bePressed();
			GameView.rePaint();
			switch (priviousScreenID) {
			case Constants.MENU_ID:
				GameView.setGameScreen(new MenuScreen());
				break;

			case Constants.GAMING_ID:
				GameView.setGameScreen(new MainScreen());
				break;
			}
			return;
		}
		
	}

	@Override
	protected void destroy() {
	}
	
}
