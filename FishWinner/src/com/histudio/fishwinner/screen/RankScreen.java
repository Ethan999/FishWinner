package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.UIUtil;

public class RankScreen extends GameScreen{

	private Bitmap img_bg,img_back,img_back2 ,img_rank;
	private Bitmap img_dijiming,img_paimingshu,img_shangfengshuzhi;
	private Bitmap[] img_paimingshes,img_shangfengshuzhis;
	private ButtonImg butn_back;
	private int[] ranks = null;
	
	
	@Override
	protected void init() {
		
		img_bg = UIUtil.loadBitmapByResize(context,R.drawable.ibg);
		img_rank = UIUtil.loadBitmapByResize(context, R.drawable.rank);
		img_dijiming = UIUtil.loadBitmapByResize(context, R.drawable.paiming);
		//返回键
		img_back = UIUtil.loadBitmapByResize(context, R.drawable.iback);
		img_back2 = UIUtil.loadBitmapByResize(context, R.drawable.iback2);
		butn_back = new ButtonImg();
		butn_back.setFirstBitmap(img_back);
		butn_back.setSecondBitmap(img_back2);
		
		//数字
		img_paimingshu = UIUtil.loadBitmapByResize(context, R.drawable.paimingshu);
		img_paimingshes = new Bitmap[10];
		int imgnum = img_paimingshu.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_paimingshes[k] = Bitmap.createBitmap(img_paimingshu, imgnum * k, 0, imgnum, img_paimingshu.getHeight());
		}
		img_paimingshu = null;
		
		//分数数字
		img_shangfengshuzhi = UIUtil.loadBitmapByResize(context, R.drawable.red_num);
		img_shangfengshuzhis = new Bitmap[10];
		imgnum = img_shangfengshuzhi.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_shangfengshuzhis[k] = Bitmap.createBitmap(img_shangfengshuzhi, imgnum * k, 0, imgnum, img_shangfengshuzhi.getHeight());
		}
		img_shangfengshuzhi = null;
		
		if(GameView.getGamingScreen() != null){
			context.storeRecoreds();
		}
		ranks = context.getRanks();
	}

	@Override
	protected void render(Canvas c) {
		
		c.drawBitmap(img_bg, 0, 0, paint);
		c.drawBitmap(butn_back.getBitmap(), UIUtil.getPixel(10, HORIZONTAL), 
				height-img_back.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(img_rank, (width-img_rank.getWidth())>>1, UIUtil.getPixel(40, VERTICAL), paint);
		c.drawBitmap(img_dijiming, UIUtil.getPixel(31, HORIZONTAL), UIUtil.getPixel(149, VERTICAL), paint);
		
		float k = UIUtil.getPixel(50, VERTICAL);
		int j = 10;
		if (ranks != null) {
			if(ranks.length <= 10)j = ranks.length;
			// 画名次
			for (int i = 1; i <= j; i++) {
				if (ranks[i - 1] != 0){
					UIUtil.getNumImage(i-1,  UIUtil.getPixel(90, HORIZONTAL), 
							UIUtil.getPixel(145, VERTICAL)+k*(i-1),img_paimingshes, c, paint);
				c.drawBitmap(img_dijiming, UIUtil.getPixel(31, HORIZONTAL), UIUtil.getPixel(149, VERTICAL)+k*(i-1), paint);
		
				}
				}
			// 画分数
			for (int i = 0; i < j; i++) {
				if (ranks[i] != 0)
					UIUtil.getNumImage(ranks[i],  UIUtil.getPixel(370, HORIZONTAL), 
							UIUtil.getPixel(140, VERTICAL)+k*(i),img_shangfengshuzhis, c, paint);
			}

		}
		
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
