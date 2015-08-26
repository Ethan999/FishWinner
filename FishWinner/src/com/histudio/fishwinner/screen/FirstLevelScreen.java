package com.histudio.fishwinner.screen;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

public class FirstLevelScreen extends GameScreen{

	private Bitmap img_levelbg,img_guanka_lian,img_guanka_an;
	private Bitmap img_back1,img_back2,img_next,img_next2,img_xzgk;
	private ButtonImg butn_back,butn_next;
	private int max_level = 1;
	private SharedPreferences sp;
	
	@Override
	protected void init() {
		
		img_levelbg = UIUtil.loadBitmapByResize(context, R.drawable.guanka1);
		img_guanka_an = UIUtil.loadBitmapByResize(context, R.drawable.guanka_an);
		img_guanka_lian = UIUtil.loadBitmapByResize(context, R.drawable.guanka_liang);
		img_xzgk = UIUtil.loadBitmapByResize(context, R.drawable.xuanze);
		//返回
		img_back1 = UIUtil.loadBitmapByResize(context, R.drawable.iback);
		img_back2 = UIUtil.loadBitmapByResize(context, R.drawable.iback2);
		butn_back = new ButtonImg();
		butn_back.setFirstBitmap(img_back1);
		butn_back.setSecondBitmap(img_back2);
		
		//前进
		img_next = UIUtil.loadBitmapByResize(context, R.drawable.eback);
		img_next2 = UIUtil.loadBitmapByResize(context, R.drawable.eback2);
		butn_next = new ButtonImg();
		butn_next.setFirstBitmap(img_next);
		butn_next.setSecondBitmap(img_next2);
		
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
		c.drawBitmap(butn_next.getBitmap(), width-img_next.getWidth()-UIUtil.getPixel(10, HORIZONTAL),
				height-img_back1.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(img_xzgk, (width - img_xzgk.getWidth())>>1, 
				UIUtil.getPixel(22, VERTICAL), paint);
		
		
		if(max_level >= 1){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(247, HORIZONTAL), 
				UIUtil.getPixel(123, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(247, HORIZONTAL), 
					UIUtil.getPixel(123, VERTICAL), paint);
		}
		
		if(max_level >= 2){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(327, HORIZONTAL), 
				UIUtil.getPixel(212, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(327, HORIZONTAL), 
					UIUtil.getPixel(212, VERTICAL), paint);
		}
		
		if(max_level >= 3){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(365, HORIZONTAL), 
				UIUtil.getPixel(325, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(365, HORIZONTAL), 
					UIUtil.getPixel(325, VERTICAL), paint);
		}
		
		if(max_level >= 4){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(271, HORIZONTAL), 
				UIUtil.getPixel(409, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(271, HORIZONTAL), 
					UIUtil.getPixel(409, VERTICAL), paint);
		}
		
		if(max_level >= 5){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(194, HORIZONTAL), 
				UIUtil.getPixel(483, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(194, HORIZONTAL), 
					UIUtil.getPixel(483, VERTICAL), paint);
		}
		
		if(max_level >= 6){
			c.drawBitmap(img_guanka_lian, UIUtil.getPixel(121, HORIZONTAL), 
				UIUtil.getPixel(598, VERTICAL), paint);}
		else {
			c.drawBitmap(img_guanka_an, UIUtil.getPixel(121, HORIZONTAL), 
					UIUtil.getPixel(598, VERTICAL), paint);
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
			GameView.setGameScreen(new MenuScreen());
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_back1, 
				width-img_next.getWidth()-UIUtil.getPixel(10, HORIZONTAL),
				height-img_back1.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_back.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new SecondLevelScreen());
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(247, HORIZONTAL), 
				UIUtil.getPixel(123, VERTICAL))){
			if(max_level >= 1){
				Constants.CURRENT_LEVEL = 1;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(327, HORIZONTAL), 
				UIUtil.getPixel(212, VERTICAL))){
			if(max_level >= 2){
				Constants.CURRENT_LEVEL = 2;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(365, HORIZONTAL), 
				UIUtil.getPixel(325, VERTICAL))){
			if(max_level >= 3){
				Constants.CURRENT_LEVEL = 3;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(271, HORIZONTAL), 
				UIUtil.getPixel(409, VERTICAL))){
			if(max_level >= 4){
				Constants.CURRENT_LEVEL = 4;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(194, HORIZONTAL), 
				UIUtil.getPixel(483, VERTICAL))){
			if(max_level >= 5){
				Constants.CURRENT_LEVEL = 5;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);
			}
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_guanka_an, 
				UIUtil.getPixel(121, HORIZONTAL), 
				UIUtil.getPixel(598, VERTICAL))){
			if(max_level >= 6){
				Constants.CURRENT_LEVEL = 6;
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
