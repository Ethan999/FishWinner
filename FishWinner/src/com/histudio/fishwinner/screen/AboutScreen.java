package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

public class AboutScreen extends GameScreen{

	private Bitmap img_bg,img_back,img_back2;
	private ButtonImg butn_back;
	
	
	@Override
	protected void init() {
		
		img_bg = UIUtil.loadBitmapByResize(context,R.drawable.ibg);
		
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
			GameView.setGameScreen(new MenuScreen());
			return;
		}
		
	}

	@Override
	protected void destroy() {
	}

	
	
	
}
