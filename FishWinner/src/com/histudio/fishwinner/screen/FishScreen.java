package com.histudio.fishwinner.screen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.entity.Fish;
import com.histudio.fishwinner.entity.QiPao;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.manager.FishManager;
import com.histudio.fishwinner.manager.QiPaoManager;
import com.histudio.fishwinner.util.UIUtil;

public class FishScreen extends GameScreen{

	private Bitmap img_fishbg,img_yu01,img_yu02,img_yu03,img_yu04,img_yu05,img_yu06;
	private FishManager fishs = new FishManager();
	private QiPaoManager qiPaos = new QiPaoManager();
	
	public FishScreen() {

		img_fishbg = UIUtil.loadBitmapByResize(context,R.drawable.fishbg);
		img_yu01 = UIUtil.loadBitmapByResize(context, R.drawable.yu01);
		img_yu02 = UIUtil.loadBitmapByResize(context, R.drawable.yu02);
		img_yu03 = UIUtil.loadBitmapByResize(context, R.drawable.yu03);
		img_yu04 = UIUtil.loadBitmapByResize(context, R.drawable.yu04);
		img_yu05 = UIUtil.loadBitmapByResize(context, R.drawable.yu05);
		img_yu06 = UIUtil.loadBitmapByResize(context, R.drawable.yu06);
		
		//ÃÌº””„
		fishs.addFish(new Fish(img_yu01, 0, 100));
		fishs.addFish(new Fish(img_yu02, -10, 100,15));
		fishs.addFish(new Fish(img_yu03, -40, 200,20));
		fishs.addFish(new Fish(img_yu04, -60, 300,25));
		fishs.addFish(new Fish(img_yu05, -100, 400,30));
		fishs.addFish(new Fish(img_yu06, 520, 250,20,Fish.Right2Left));
		
		qiPaos.addQiPao(new QiPao(2));
		qiPaos.addQiPao(new QiPao(3));
		qiPaos.addQiPao(new QiPao(4));
		
	}
	
	@Override
	protected void init() {

	}

	@Override
	protected void render(Canvas c) {
		
		c.drawBitmap(img_fishbg, 0, 0, paint);
		fishs.drawAll(c);
		qiPaos.drawAll(c);
	}

	@Override
	protected void update() {
		
		fishs.updateAll();
		qiPaos.updateAll();
	}

	@Override
	protected void handleEvent() {
	}

	@Override
	protected void destroy() {
		
	}
}
