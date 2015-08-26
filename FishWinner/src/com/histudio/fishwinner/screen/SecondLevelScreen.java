package com.histudio.fishwinner.screen;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

public class SecondLevelScreen extends GameScreen{

	private Bitmap img_levelbg,img_guanka_lian,img_guanka_an;
	private Bitmap img_back1,img_back2,img_xzgk;
	private ButtonImg butn_back;
	private int max_level = 1;
	private SharedPreferences sp;
	
	
	@Override
	protected void init() {
		
		img_levelbg = UIUtil.loadBitmapByResize(context, R.drawable.guanka2);
		img_guanka_an = UIUtil.loadBitmapByResize(context, R.drawable.guanka_an);
		img_guanka_lian = UIUtil.loadBitmapByResize(context, R.drawable.guanka_liang);
		img_xzgk = UIUtil.loadBitmapByResize(context, R.drawable.xuanze);
		//返回
		img_back1 = UIUtil.loadBitmapByResize(context, R.drawable.iback);
		img_back2 = UIUtil.loadBitmapByResize(context, R.drawable.iback2);
		butn_back = new ButtonImg();
		butn_back.setFirstBitmap(img_back1);
		butn_back.setSecondBitmap(img_back2);
		
		//从sharedPreference中获得信息
		sp = context.getSharedPreferences(Constants.SP_NAME, context.MODE_PRIVATE);
		max_level = sp.getInt("maxLevel", 1);
		Constants.MAX_LEVEL = max_level;
	}

	@Override
	protected void render(Canvas c) {
	
		c.drawBitmap(img_levelbg, 0, 0, paint);
		c.drawBitmap(butn_back.getBitmap(), UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back1.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(img_xzgk, (width - img_xzgk.getWidth())>>1, 
				UIUtil.getPixel(22, VERTICAL), paint);
		
		if(max_level >= 7){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(176, HORIZONTAL), 
				UIUtil.getPixel(146, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(176, HORIZONTAL), 
					UIUtil.getPixel(146, VERTICAL), paint);
		}
		
		if(max_level >= 8){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(311, HORIZONTAL), 
				UIUtil.getPixel(265, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(311, HORIZONTAL), 
					UIUtil.getPixel(265, VERTICAL), paint);
		}
		
		if(max_level >= 9){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(408, HORIZONTAL), 
				UIUtil.getPixel(323, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(408, HORIZONTAL), 
					UIUtil.getPixel(323, VERTICAL), paint);
		}
		
		if(max_level >= 10){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(224, HORIZONTAL), 
				UIUtil.getPixel(334, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(224, HORIZONTAL), 
					UIUtil.getPixel(334, VERTICAL), paint);
		}
		
		if(max_level >= 11){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(134, HORIZONTAL), 
				UIUtil.getPixel(442, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(134, HORIZONTAL), 
					UIUtil.getPixel(449, VERTICAL), paint);
		}
		
		if(max_level >= 12){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(47, HORIZONTAL), 
				UIUtil.getPixel(572, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(47, HORIZONTAL), 
					UIUtil.getPixel(572, VERTICAL), paint);
		}
		
	}

	@Override
	protected void update() {
	}

	@Override
	protected void handleEvent() {
		
		if(HandleTouch.isTouchBitmap(img_back1, 
				UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back1.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_back.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new FirstLevelScreen());
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(176, HORIZONTAL), 
				UIUtil.getPixel(146, VERTICAL))){
			if(max_level >= 7){
				Constants.CURRENT_LEVEL = 7;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(176, HORIZONTAL), 
				UIUtil.getPixel(146, VERTICAL))){
			if(max_level >= 8){
				Constants.CURRENT_LEVEL = 8;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(408, HORIZONTAL), 
				UIUtil.getPixel(323, VERTICAL))){
			if(max_level >= 9){
				Constants.CURRENT_LEVEL = 9;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(224, HORIZONTAL), 
				UIUtil.getPixel(334, VERTICAL))){
			if(max_level >= 10){
				Constants.CURRENT_LEVEL = 10;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(134, HORIZONTAL), 
				UIUtil.getPixel(442, VERTICAL))){
			if(max_level >= 11){
				Constants.CURRENT_LEVEL = 11;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(47, HORIZONTAL), 
				UIUtil.getPixel(572, VERTICAL))){
			if(max_level >= 12){
				Constants.CURRENT_LEVEL = 12;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		
	}

	@Override
	protected void destroy() {
	}

	
	
	
}
