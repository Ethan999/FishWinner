package com.histudio.fishwinner.screen;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MenuScreen extends FishScreen{

	private Bitmap img_title,img_shezhi,img_bangzhu,img_paiming,img_tuichu;
	private Bitmap img_shezhi2,img_bangzhu2,img_paiming2,img_tuichu2;
	private Bitmap img_start1,img_start2,img_help1,img_help2;
	private ButtonImg butn_shezhi,butn_bangzhu,butn_paiming,butn_tuichu;
	private ButtonImg butn_start,butn_help;
	
	
	@Override
	protected void init() {
		super.init();
		GameView.setFrameTime(100);
		img_title = UIUtil.loadBitmapByResize(context,R.drawable.title);
		//设置
		img_shezhi = UIUtil.loadBitmapByResize(context, R.drawable.shezhi);
		img_shezhi2 = UIUtil.loadBitmapByResize(context, R.drawable.shezhi2);
		butn_shezhi = new ButtonImg();
		butn_shezhi.setFirstBitmap(img_shezhi);
		butn_shezhi.setSecondBitmap(img_shezhi2);
		
		//排名
		img_paiming = UIUtil.loadBitmapByResize(context, R.drawable.paihangbang);
		img_paiming2 = UIUtil.loadBitmapByResize(context, R.drawable.paihangbang2);
		butn_paiming = new ButtonImg();
		butn_paiming.setFirstBitmap(img_paiming);
		butn_paiming.setSecondBitmap(img_paiming2);
		
		//帮助
		img_bangzhu = UIUtil.loadBitmapByResize(context,R.drawable.bangzhu);
		img_bangzhu2 = UIUtil.loadBitmapByResize(context, R.drawable.bangzhu2);
		butn_bangzhu = new ButtonImg();
		butn_bangzhu.setFirstBitmap(img_bangzhu);
		butn_bangzhu.setSecondBitmap(img_bangzhu2);
		
		//退出
		img_tuichu = UIUtil.loadBitmapByResize(context,R.drawable.tuichu);
		img_tuichu2 = UIUtil.loadBitmapByResize(context, R.drawable.tuichu2);
		butn_tuichu = new ButtonImg();
		butn_tuichu.setFirstBitmap(img_tuichu);
		butn_tuichu.setSecondBitmap(img_tuichu2);
		
		//开始
		img_start1 = UIUtil.loadBitmapByResize(context, R.drawable.start1);
		img_start2 = UIUtil.loadBitmapByResize(context, R.drawable.start2);
		butn_start = new ButtonImg();
		butn_start.setFirstBitmap(img_start1);
		butn_start.setSecondBitmap(img_start2);
		
		//帮助
		img_help1 = UIUtil.loadBitmapByResize(context,R.drawable.help1);
		img_help2 = UIUtil.loadBitmapByResize(context, R.drawable.help2);
		butn_help = new ButtonImg();
		butn_help.setFirstBitmap(img_help1);
		butn_help.setSecondBitmap(img_help2);
	}

	@Override
	protected void render(Canvas c) {
		super.render(c);
		float f = UIUtil.getPixel(18, HORIZONTAL);
		c.drawBitmap(img_title, (width-img_title.getWidth())/2, 
				UIUtil.getPixel(40, VERTICAL), paint);
		c.drawBitmap(butn_shezhi.getBitmap(), f, UIUtil.getPixel(710, VERTICAL), paint);
		c.drawBitmap(butn_bangzhu.getBitmap(), f+(width>>2)*1, UIUtil.getPixel(710, VERTICAL), paint);
		c.drawBitmap(butn_paiming.getBitmap(), f+(width>>2)*2, UIUtil.getPixel(710, VERTICAL), paint);
		c.drawBitmap(butn_tuichu.getBitmap(), f+(width>>2)*3, UIUtil.getPixel(710, VERTICAL), paint);
		c.drawBitmap(butn_start.getBitmap(), (width-img_start1.getWidth())>>1, 
				UIUtil.getPixel(400, VERTICAL), paint);
		c.drawBitmap(butn_help.getBitmap(), (width-img_help1.getWidth())>>1, 
				UIUtil.getPixel(500, VERTICAL), paint);
	}

	@Override
	protected void update() {
		super.update();
	}

	@Override
	protected void handleEvent() {
		super.handleEvent();
		float f = UIUtil.getPixel(18, HORIZONTAL);
		
		//关于
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN,img_bangzhu,
				f+(width>>2)*1, UIUtil.getPixel(710, VERTICAL)))
		{
			butn_bangzhu.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new AboutScreen(),Constants.MENU_ID);
			return;
		}
		
		
		//帮助
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN,img_help1,
				(width-img_help1.getWidth())>>1, 
				UIUtil.getPixel(500, VERTICAL)))
		{
			butn_help.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new HelpScreen(),Constants.MENU_ID);
			return;
		}
		
		//开始
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN,img_start1,//start_menu
				(width-img_start1.getWidth())>>1, 
				UIUtil.getPixel(400, VERTICAL)))
		{
			butn_start.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new FirstLevelScreen());
			return;
		}
		
		//退出
		if(HandleTouch.isTouchBitmap(img_tuichu, 
				f+(width>>2)*3, UIUtil.getPixel(710, VERTICAL))){
			context.getHandler().obtainMessage(Constants.EXITGAME_DIALOG).sendToTarget();
		
		}
		
		//排名
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN,img_paiming,//about_menu
				f+(width>>2)*2, UIUtil.getPixel(710, VERTICAL)))
		{
			butn_paiming.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new RankScreen());
			return;
		}
		
		
		//设置
		if(HandleTouch.isTouchBitmap(HandleTouch.DOWN,img_shezhi,//about_menu
				f, UIUtil.getPixel(710, VERTICAL)))
		{
			butn_paiming.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new ConfigrationScreen(),Constants.MENU_ID);
			return;
		}
		
	}

	@Override
	protected void destroy() {
		super.destroy();
	}

	
	
}
