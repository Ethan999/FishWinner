package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.MusicPlayer;
import com.histudio.fishwinner.util.TipHelper;
import com.histudio.fishwinner.util.UIUtil;

public class ConfigrationScreen extends FishScreen{

	
	private Bitmap img_back,img_back2,img_configration,img_kai_an,img_kai_lian,img_guan_an,img_guan_lian;
	private Bitmap img_anniubg,img_yingyue,img_zhendong;
	private ButtonImg butn_back;
	private MusicPlayer musicPlayer;
	
	@Override
	protected void init() {
		super.init();
		bgMusicState = Constants.GAMING_AUDIO;
		musicPlayer = GameView.getMusicPlayer();
		
		img_configration = UIUtil.loadBitmapByResize(context, R.drawable.youxishezhi);
		img_kai_an = UIUtil.loadBitmapByResize(context, R.drawable.kai_an);
		img_kai_lian = UIUtil.loadBitmapByResize(context, R.drawable.kai_lian);
		img_guan_an = UIUtil.loadBitmapByResize(context, R.drawable.guan_an);
		img_guan_lian = UIUtil.loadBitmapByResize(context, R.drawable.guan_lian);
		img_anniubg = UIUtil.loadBitmapByResize(context, R.drawable.shezhibg);
		img_yingyue = UIUtil.loadBitmapByResize(context, R.drawable.yinyue);
		img_zhendong = UIUtil.loadBitmapByResize(context, R.drawable.zhendong);
		// ·µ»Ø¼ü
		img_back = UIUtil.loadBitmapByResize(context, R.drawable.iback);
		img_back2 = UIUtil.loadBitmapByResize(context, R.drawable.iback2);
		butn_back = new ButtonImg();
		butn_back.setFirstBitmap(img_back);
		butn_back.setSecondBitmap(img_back2);
	}

	@Override
	protected void render(Canvas c) {
		super.render(c);
		c.drawBitmap(butn_back.getBitmap(), UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(img_configration, (width - img_configration.getWidth())>>1,
				UIUtil.getPixel(40, VERTICAL), paint);
		c.drawBitmap(img_anniubg, (width - img_anniubg.getWidth())>>1, 
				UIUtil.getPixel(252, VERTICAL), paint);
		c.drawBitmap(img_anniubg, (width - img_anniubg.getWidth())>>1, 
				UIUtil.getPixel(467, VERTICAL), paint);
		c.drawBitmap(img_yingyue, UIUtil.getPixel(108, HORIZONTAL), 
				UIUtil.getPixel(262, VERTICAL), paint);
		c.drawBitmap(img_zhendong, UIUtil.getPixel(108, HORIZONTAL),
				UIUtil.getPixel(479, VERTICAL), paint);
		
		
		//ÉùÒôÉèÖÃ
		if(musicPlayer.isPlayerSwitch()){
			c.drawBitmap(img_kai_lian, 
					UIUtil.getPixel(237, HORIZONTAL), 
					UIUtil.getPixel(269, VERTICAL), paint);
			c.drawBitmap(img_guan_an, 
					UIUtil.getPixel(295, HORIZONTAL), 
					UIUtil.getPixel(269, VERTICAL), paint);
		}else {
			c.drawBitmap(img_kai_an, 
					UIUtil.getPixel(237, HORIZONTAL), 
					UIUtil.getPixel(269, VERTICAL), paint);
			c.drawBitmap(img_guan_lian, 
					UIUtil.getPixel(295, HORIZONTAL), 
					UIUtil.getPixel(269, VERTICAL), paint);
		}
		//Õð¶¯ÉèÖÃ
		if(TipHelper.isTipSwitch()){
			c.drawBitmap(img_kai_lian, 
					UIUtil.getPixel(237, HORIZONTAL), 
					UIUtil.getPixel(478, VERTICAL), paint);
			c.drawBitmap(img_guan_an, 
					UIUtil.getPixel(295, HORIZONTAL), 
					UIUtil.getPixel(478, VERTICAL), paint);
		}else {
			c.drawBitmap(img_kai_an, 
					UIUtil.getPixel(237, HORIZONTAL), 
					UIUtil.getPixel(478, VERTICAL), paint);
			c.drawBitmap(img_guan_lian, 
					UIUtil.getPixel(295, HORIZONTAL), 
					UIUtil.getPixel(478, VERTICAL), paint);
		}
	}

	@Override
	protected void update() {
		super.update();
	}

	@Override
	protected void handleEvent() {
		super.handleEvent();
		
		
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN, butn_back.getBitmap(), UIUtil.getPixel(10, HORIZONTAL), 
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
		
		
		if(HandleTouch.isTouchBitmap(img_anniubg, (width - img_anniubg.getWidth())>>1, 
				UIUtil.getPixel(252, VERTICAL))){
			
			if(musicPlayer.isPlayerSwitch()){
				musicPlayer.pause();
			}else {
				musicPlayer.restart();
			}
			
		}
		
		if(HandleTouch.isTouchBitmap(img_anniubg, (width - img_anniubg.getWidth())>>1, 
				UIUtil.getPixel(467, VERTICAL))){
			
			TipHelper.setTipSwitch((TipHelper.isTipSwitch())?false:true);
			if(TipHelper.isTipSwitch()){
				TipHelper.Vibrate(context, 500);
			}
		}
	}

	@Override
	protected void destroy() {
		super.destroy();
	}
	
}
