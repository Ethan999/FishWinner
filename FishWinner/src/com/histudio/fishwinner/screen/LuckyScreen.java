package com.histudio.fishwinner.screen;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.SoundPoolPlayer;
import com.histudio.fishwinner.util.TipHelper;
import com.histudio.fishwinner.util.UIUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class LuckyScreen extends FishScreen{

	//正常游戏状态
	public static final byte BEFORE = 31;
	public static final byte DOING = 32;
	public static final byte AFTER = 33;
	public static final byte WIN = 34;
	public static final byte LOST = 35;
	
	//选择项
	public static final byte GUESS_SMALL = 41;
	public static final byte GUESS_LARGE = 42;
	
	private byte gameState = BEFORE;
	
	private boolean isRedLightGray ;				//控制guess游戏总红灯闪烁动画
	private int guessLevel ;						//guess游戏总色子运行时的动画
	private int alertString ;						//显示guess游戏结果的文本(0-无，1、十分遗憾,2,恭喜猜中)
	private byte selected = GUESS_SMALL;			//guess游戏的状态
	private long gamingTimeDelay = 0;				//动画帧技术的控制位
	private int beforePauseFrame = 0;				//暂停前的帧时间
	private boolean isTopFirst;
    private int index;
    private SoundPoolPlayer spp = SoundPoolPlayer.getInstance(context);
    private int bigsmall = 0;
	private boolean bz_isfailrun = false;
    
	private Bitmap img_defeng,img_caidaxiao,img_fanhui1,img_fanhui2,img_jiafeng1,img_jiafeng2;
	private Bitmap img_yu1,img_yu1_an,img_yu1_lian,img_yu2,img_yu2_an,img_yu2_lian;
	private ButtonImg butn_jiafeng,butn_fanhui;
	
	
	private Bitmap img_red_num;
	private Bitmap img_dashuzhi;
	private Bitmap img_saizhishuzhi;
	private Bitmap[] img_nums,img_dashuzhis,img_saizhishuzhis;
	private Bitmap img_redlight_gray;
	private Bitmap img_red_light;
	private Bitmap img_sfyh;
	private Bitmap img_gxcz;
	
	@Override
	protected void init() {
		super.init();
		
		initGuesss();
		bgMusicState = Constants.GAMING_AUDIO;
		
		img_defeng = UIUtil.loadBitmapByResize(context, R.drawable.defen);
		img_caidaxiao = UIUtil.loadBitmapByResize(context, R.drawable.caidaxiao);

		img_yu1 = UIUtil.loadBitmapByResize(context,R.drawable.yu1);
		img_yu1_an = UIUtil.loadBitmapByResize(context, R.drawable.yu1_an);
		img_yu1_lian = UIUtil.loadBitmapByResize(context, R.drawable.yu1_liang);
		
		img_yu2 = UIUtil.loadBitmapByResize(context, R.drawable.yu2);
		img_yu2_an = UIUtil.loadBitmapByResize(context, R.drawable.yu2_an);
		img_yu2_lian = UIUtil.loadBitmapByResize(context, R.drawable.yu2_liang);
		
		//加分
		img_jiafeng1 = UIUtil.loadBitmapByResize(context, R.drawable.ijiafeng1);
		img_jiafeng2 = UIUtil.loadBitmapByResize(context, R.drawable.ijiafeng2);
		butn_jiafeng = new ButtonImg();
		butn_jiafeng.setFirstBitmap(img_jiafeng1);
		butn_jiafeng.setSecondBitmap(img_jiafeng2);
		
		//返回
		img_fanhui1 = UIUtil.loadBitmapByResize(context, R.drawable.fanhui1);
		img_fanhui2 = UIUtil.loadBitmapByResize(context, R.drawable.fanhui2);
		butn_fanhui = new ButtonImg();
		butn_fanhui.setFirstBitmap(img_fanhui1);
		butn_fanhui.setSecondBitmap(img_fanhui2);
		
		img_red_light = UIUtil.loadBitmapByDensity(context, R.drawable.red_light);
		img_redlight_gray = UIUtil.loadBitmapByDensity(context, R.drawable.red_light_gray);
		img_sfyh = UIUtil.loadBitmapByDensity(context, R.drawable.sfyh);
		img_gxcz = UIUtil.loadBitmapByDensity(context, R.drawable.gxcz);
		
		//数字图片
		img_red_num = UIUtil.loadBitmapByResize(context, R.drawable.red_num);
		img_nums = new Bitmap[10];
		int imgnum = img_red_num.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_nums[k] = Bitmap.createBitmap(img_red_num, imgnum * k, 0, imgnum, img_red_num.getHeight());
		}
		img_red_num = null;
		
		//中部数字
		img_dashuzhi = UIUtil.loadBitmapByResize(context, R.drawable.dashuzhi);
		img_dashuzhis = new Bitmap[10];
		imgnum = img_dashuzhi.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_dashuzhis[k] = Bitmap.createBitmap(img_dashuzhi, imgnum * k, 0, imgnum, img_dashuzhi.getHeight());
		}
		img_dashuzhi = null;
		
		//最下边数字
		img_saizhishuzhi = UIUtil.loadBitmapByResize(context, R.drawable.saizhi);
		img_saizhishuzhis = new Bitmap[10];
		imgnum = img_saizhishuzhi.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_saizhishuzhis[k] = Bitmap.createBitmap(img_saizhishuzhi, imgnum * k, 0, imgnum, img_saizhishuzhi.getHeight());
		}
		img_saizhishuzhi = null;
	}

	@Override
	protected void render(Canvas c) {
		super.render(c);
		
		
		drawNormal(c);
		switch (gameState) {
		case BEFORE:
			drawBefore(c);
			break;

		case DOING:
			break;
			
		case AFTER:
			break;
		}
		
	}

	@Override
	protected void update() {
		super.update();
	
		doNormal();
		switch (gameState) {
		case BEFORE:
			doBefore();
			break;
		case DOING:
			if (context.isPause()) {
				beforePauseFrame = GameView.getFrameTime();
				GameView.setFrameTime(200);
			} else {
				GameView.setFrameTime(beforePauseFrame);
				doDoing();
			}
			break;
		case AFTER:
			doAfter();
			break;
		case WIN:
			doWin();
			break;
		case LOST:
			doLost();
			break;
		}
	
	}

	@Override
	protected void handleEvent() {
		super.handleEvent();
		

		switch (gameState) {
		case BEFORE:
			handleBefore();
			break;
		case DOING:
			handleDoing();
			break;
		case AFTER:
			handleAfter();
			break;
		}

	}

	@Override
	protected void destroy() {
		super.destroy();
	}

	private void drawNormal(Canvas c) {

		c.drawBitmap(img_defeng, 0, 0, paint);
		c.drawBitmap(img_caidaxiao, (width - img_caidaxiao.getWidth())>>1, 
				UIUtil.getPixel(152, VERTICAL), paint);
		
		//加分
		c.drawBitmap(butn_jiafeng.getBitmap(), UIUtil.getPixel(15, HORIZONTAL), 
				UIUtil.getPixel(623, VERTICAL), paint);
		//返回
		c.drawBitmap(butn_fanhui.getBitmap(), UIUtil.getPixel(320, HORIZONTAL), 
				UIUtil.getPixel(625, VERTICAL), paint);
		
		//总分和得分
		UIUtil.getNumImage(MainScreen.getMachine().totle, UIUtil.getPixel(116, HORIZONTAL),
				UIUtil.getPixel(22, VERTICAL), img_nums, c, paint);
		UIUtil.getNumImage(MainScreen.getMachine().score, UIUtil.getPixel(355, HORIZONTAL), 
				UIUtil.getPixel(22, VERTICAL), img_nums, c, paint);
		//中部的分数
		UIUtil.getNumImage(MainScreen.getMachine().totleScore2, UIUtil.getPixel(240, HORIZONTAL), 
				UIUtil.getPixel(160, VERTICAL), img_dashuzhis, c, paint);
		UIUtil.getNumImage(MainScreen.getMachine().winScore, UIUtil.getPixel(240, HORIZONTAL), 
				UIUtil.getPixel(265, VERTICAL), img_saizhishuzhis, c, paint);
	
		//画色子
		if(MainScreen.getMachine().dice == 0)
			c.drawBitmap(img_yu1, (width - img_yu1.getWidth())>>1, UIUtil.getPixel(370, VERTICAL), paint);
		else
			c.drawBitmap(img_yu2, (width - img_yu1.getWidth())>>1, UIUtil.getPixel(370, VERTICAL), paint);

		//绘制选中项
		switch (bigsmall) {
		case GUESS_SMALL:
			c.drawBitmap(img_yu1_lian, UIUtil.getPixel(16, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			c.drawBitmap(img_yu2_an, UIUtil.getPixel(304, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			break;

		case GUESS_LARGE:
			c.drawBitmap(img_yu1_an, UIUtil.getPixel(16, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			c.drawBitmap(img_yu2_lian, UIUtil.getPixel(304, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			break;
		case 0:
			c.drawBitmap(img_yu1_lian, UIUtil.getPixel(16, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			c.drawBitmap(img_yu2_lian, UIUtil.getPixel(304, HORIZONTAL), 
					UIUtil.getPixel(448, VERTICAL), paint);
			break;
		}
		
		
		//画红灯的闪烁
		if(isRedLightGray){
			c.drawBitmap(img_redlight_gray, UIUtil.getPixel(65, HORIZONTAL), 
					UIUtil.getPixel(374, VERTICAL), paint);
			c.drawBitmap(img_redlight_gray,UIUtil.getPixel(355, HORIZONTAL), 
					UIUtil.getPixel(374, VERTICAL), paint);
			c.drawBitmap(img_redlight_gray, (width-img_red_light.getWidth())>>1, 
					UIUtil.getPixel(507, VERTICAL), paint);
		}else {
			c.drawBitmap(img_red_light, UIUtil.getPixel(65, HORIZONTAL), 
					UIUtil.getPixel(374, VERTICAL), paint);
			c.drawBitmap(img_red_light, UIUtil.getPixel(355, HORIZONTAL), 
					UIUtil.getPixel(374, VERTICAL), paint);
			c.drawBitmap(img_red_light, (width-img_red_light.getWidth())>>1, 
					UIUtil.getPixel(507, VERTICAL), paint);
		}
		
		//结果的文字图片
		if(alertString != 0){
		
			switch (alertString) {
			
			case 1:
				c.drawBitmap(img_sfyh, (width-img_sfyh.getWidth())>>1, 
						UIUtil.getPixel(212, VERTICAL), paint);
				break;
			case 2:
				c.drawBitmap(img_gxcz, (width-img_gxcz.getWidth())>>1, 
						UIUtil.getPixel(212, VERTICAL), paint);
				break;
			
			}
		
		}
	}
	
	private void drawBefore(Canvas c) {
		
	}
	
	private void doNormal(){
		
	}
	
	private void doBefore(){
		
		GameView.setFrameTime(200);
		bigsmall = 0;
	}
	
	private void doDoing(){
		
		MainScreen.getMachine().getDice();
		bigsmall = (bigsmall == GUESS_SMALL)?GUESS_LARGE:GUESS_SMALL;
		isRedLightGray = (isRedLightGray == false)?true:false;
		switch (guessLevel) {
		case 0:
			reduceTime2(100,3000, (byte)2);
			break;
		case 2:
			gameState = AFTER;
			break;
		}
		
	}
	
	private void doAfter(){
		if (!bz_isfailrun) {
			bz_isfailrun = true;
			bigsmall = 0;
			if (MainScreen.getMachine().isGuessGameWin()) {
				MainScreen.getMachine().totle += MainScreen.getMachine().winScore;
				MainScreen.getMachine().score += MainScreen.getMachine().winScore;
				GameView.rePaint();
				if (MainScreen.getMachine().totle >= MainScreen
						.getInstance().getTargetScore()) {
					context.getHandler().obtainMessage(Constants.PASS_DIALOG)
							.sendToTarget();
				} else {
					gameState = WIN;
				}
				spp.play("win");
				TipHelper.Vibrate(context, 2000);

			} else {

				MainScreen.getMachine().totle -= MainScreen.getMachine().winScore;
				if (MainScreen.getMachine().isGameOver()) {
					MainScreen.getMachine().totle = 0;
					context.getHandler().obtainMessage(Constants.FAIL_DIALOG)
							.sendToTarget();
				} else {
					spp.play("fail");
					bz_isfailrun = false;
					gameState = LOST;

				}

			}
		}
		
	}
	
	private void doWin(){
		
		if(gamingTimeDelay == 0){
			gamingTimeDelay = System.currentTimeMillis();
		}
		if((System.currentTimeMillis() - gamingTimeDelay) <= 2000 ){  
			
			alertString = 2;//恭喜猜中
			
		}else {
			gamingTimeDelay = 0;
			alertString = 0 ;
			initGuesss();
			gameState = BEFORE;
		}
		
	}
	
	
	
	private void doLost(){
		
		if(gamingTimeDelay == 0){
			gamingTimeDelay = System.currentTimeMillis();
		}
		if((System.currentTimeMillis() - gamingTimeDelay) <= 2000 ){
			
			alertString = 1;//十分遗憾
			
		}else {
			GameView.setFrameTime(100);
			gamingTimeDelay = 0;
			alertString = 0;
			
			if (!bz_isfailrun) {
				bz_isfailrun = true;
				if (MainScreen.getMachine().isGameOver()) {
					MainScreen.getMachine().totle = 0;
					context.getHandler().obtainMessage(Constants.FAIL_DIALOG)
							.sendToTarget();
				}else {
					GameView.setGameScreen(new MainScreen());
				}
			}

		}
	}
	
	private void handleBefore(){
		
		if(HandleTouch.isTouchBitmap(img_fanhui1, 
				UIUtil.getPixel(320, HORIZONTAL), 
				UIUtil.getPixel(625, VERTICAL))){
			butn_fanhui.bePressed();
			GameView.rePaint();
			GameView.setGameScreen(new MainScreen());
			return;
		}
		
		if(HandleTouch.isTouchBitmap(img_jiafeng1, UIUtil.getPixel(15, HORIZONTAL), 
				UIUtil.getPixel(623, VERTICAL))){
			butn_jiafeng.bePressed();
			GameView.rePaint();
			butn_jiafeng.beNormal();
			GameView.rePaint();
			if((MainScreen.getMachine().totleScore2 - MainScreen.getMachine().winScore*2) >= 0){
				spp.play("6");
				MainScreen.getMachine().winScore *= 2;
				MainScreen.getMachine().totleScore2 -= MainScreen.getMachine().winScore>>1 ;
			}else{
				if(MainScreen.getMachine().totleScore2 > 0){
					spp.play("6");
					MainScreen.getMachine().winScore += MainScreen.getMachine().totleScore2;
					MainScreen.getMachine().totleScore2 = 0 ;
				}
			}
		}
		
		if(HandleTouch.isTouchBitmap(img_yu1_lian, UIUtil.getPixel(16, HORIZONTAL), 
				UIUtil.getPixel(448, VERTICAL))){
			SoundPoolPlayer.getInstance(context).play("bs");
			selected = GUESS_SMALL;
			switch (selected) {
			case GUESS_SMALL:
				MainScreen.getMachine().guessChoice = 0;
				gameState = DOING;
				bigsmall = selected;
				break;
			case GUESS_LARGE:
				MainScreen.getMachine().guessChoice = 1;
				gameState = DOING;
				bigsmall = selected;
				break;
			}
		
		}
		if(HandleTouch.isTouchBitmap(img_yu2_lian, UIUtil.getPixel(304, HORIZONTAL), 
				UIUtil.getPixel(448, VERTICAL))){
			SoundPoolPlayer.getInstance(context).play("bs");
			selected = GUESS_LARGE;
			switch (selected) {
			case GUESS_SMALL:
				MainScreen.getMachine().guessChoice = 0;
				gameState = DOING;
				bigsmall = selected;
				break;
			case GUESS_LARGE:
				MainScreen.getMachine().guessChoice = 1;
				gameState = DOING;
				bigsmall = selected;
				break;
			}
		}
		
	}
	
	private void handleDoing(){}
	
	private void handleAfter(){}
	

	private void initGuesss(){
		
		GameView.setFrameTime(200);			//线程休眠时间，也就是刷屏的见个时间
		isRedLightGray = false;			    //控制guess游戏总红灯闪烁动画
		guessLevel = 0;						//guess游戏总色子运行时的动画
		alertString = 0;					//显示guess游戏结果的文本(0-无，1、十分遗憾,2,恭喜猜中)
		gameState = BEFORE;					//guess游戏的状态
		MainScreen.getMachine().init();
		bz_isfailrun = false;
	}

	private void reduceTime2(int i,int time ,byte nextLevel){
		
		if (gamingTimeDelay == 0) {
			gamingTimeDelay = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - gamingTimeDelay) <= time) { 
				GameView.setFrameTime(i);
		} else {
			gamingTimeDelay = 0;
			guessLevel = nextLevel;
		}
	}
	
}
