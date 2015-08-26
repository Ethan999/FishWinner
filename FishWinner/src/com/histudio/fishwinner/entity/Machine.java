package com.histudio.fishwinner.entity;

import java.util.Random;

import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameActivity;
import com.histudio.fishwinner.game.GameScreen;
import com.histudio.fishwinner.util.UIUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.HorizontalScrollView;


public class Machine {

	
	//水果的种类
	public static final byte FRUIT_BAR = 11;
	public static final byte FRUIT_77 = 12;
	public static final byte FRUIT_STAR = 13;
	public static final byte FRUIT_WATERTMELON = 14;
	public static final byte FRUIT_BELL = 15;
	public static final byte FRUIT_PEACH = 16;
	public static final byte FRUIT_LEMON = 17;
	public static final byte FRUIT_APPLE= 18;
	//旋转方向
	private static final byte DIRECTION_LEFT = 0;
	private static final byte DIRECTION_RIGHT = 1;

	//全局变量
	private Paint paint = new Paint();
	private Bitmap img_screen_gray;
	private int grayWidth ,grayHeight;
	private Random random = new Random();
	private int[] screen = new int[24];					//水果机屏幕的数据结构
	public int a1,a2,b1,b2,c1,c2,d1,d2;					//移动方框变量
	public int totle = 500,score = 0;				  	//分数情况
	public int winScore = 0 ;							//gaming游戏中获得的分数
	public byte direction = DIRECTION_RIGHT;			//走马灯的方向
	
	//需要初始化
	public int bar,qiqi,star,watermelon,bell,peach,lemon,apple;		//各种水果上分情况
	public int result ;									//随机生成的结果（0-23）
	private byte shortResult ;							//结果是那种水果
	private boolean isBig ;								//是大水果还是小水果
	private boolean isMediumBar ;						//bar特殊的情况，中等大小
	public int guessChoice ;		     				//买大还是买小（0-小,1-大	）
	public int dice ;									//色子的结果	
	public int oldTotle;								//上分前的总分
	public int totleScore2;								//总分的副本
	
	public Machine() {
		//图片加载
		img_screen_gray = UIUtil.loadBitmapByResize(GameActivity.getInstance(), R.drawable.big_gray);
		grayWidth = img_screen_gray.getWidth();
		grayHeight = img_screen_gray.getHeight();
		init();
	}
	
	
	public void init(){
		
		result = 9;								//随机生成的结果（0-23）
		shortResult = 0;						//结果是那种水果
		isBig = false;						    //是大水果还是小水果
		isMediumBar = false;				    //bar特殊的情况，中等大小
		dice = 0;								//色子的结果		
		direction = (direction == DIRECTION_LEFT)?DIRECTION_RIGHT : DIRECTION_LEFT;
		oldTotle = totle;
		totleScore2 = totle - winScore;
		init8point();
		
	}
	
	public void reset(){
		
		initFruit();
		oldTotle = totle;
		totleScore2 = totle - winScore;
		
	}
	
	public void initLevel(){
		totle = 500;
		score = 0;
		reset();
	}
	
	public void drawLight(Canvas c){

		
		for (int i = 0; i < screen.length; i++) {
			if(i != a1 && i != a2 && i != b1 && i != b2 && i != c1 && i != c2 && i != d1 && i != d2){
				
				if (i > -1 && i < 6) {

					c.drawBitmap(img_screen_gray, UIUtil.getPixel(0, GameScreen.HORIZONTAL)+grayWidth*i,
							UIUtil.getPixel(73, GameScreen.VERTICAL), paint);

				} else if (i > 5 && i < 12) {

					c.drawBitmap(img_screen_gray, UIUtil.getPixel(409,GameScreen.HORIZONTAL), 
							UIUtil.getPixel(73, GameScreen.VERTICAL)+grayHeight* (i - 6), paint);
					
				} else if (i > 11 && i < 18) {
					
					c.drawBitmap(img_screen_gray, UIUtil.getPixel(408,GameScreen.HORIZONTAL)-grayWidth*(i-12), 
							UIUtil.getPixel(482, GameScreen.VERTICAL), paint);
					
				} else if (i > 17 && i < 24) {

					c.drawBitmap(img_screen_gray, UIUtil.getPixel(0,GameScreen.HORIZONTAL), 
							UIUtil.getPixel(482, GameScreen.VERTICAL)-grayHeight*(i-18), paint);

				}
			}
		   }
		}
	
	public void cycle(int i,byte direction) {

		switch (direction) {
		case DIRECTION_LEFT:
	
			if(a1 == 0)a1 = 24;if(a1 != -1)a1 = ((a1-1)%24);
			if(a2 == 0)a2 = 24;if(a2 != -1)a2 = ((a2-1)%24);
			if(b1 == 0)b1 = 24;if(b1 != -1)b1 = ((b1-1)%24);
			if(b2 == 0)b2 = 24;if(b2 != -1)b2 = ((b2-1)%24);
			if(c1 == 0)c1 = 24;if(c1 != -1)c1 = ((c1-1)%24);
			if(c2 == 0)c2 = 24;if(c2 != -1)c2 = ((c2-1)%24);
			if(d1 == 0)d1 = 24;if(d1 != -1)d1 = ((d1-1)%24);
			if(d2 == 0)d2 = 24;if(d2 != -1)d2 = ((d2-1)%24);
			
			break;
			
		case DIRECTION_RIGHT:
			
			if(a1 != -1)a1 = ((a1+i)%24);
			if(a2 != -1)a2 = ((a2+i)%24);
			if(b1 != -1)b1 = ((b1+i)%24);
			if(b2 != -1)b2 = ((b2+i)%24);
			if(c1 != -1)c1 = ((c1+i)%24);
			if(c2 != -1)c2 = ((c2+i)%24);
			if(d1 != -1)d1 = ((d1+i)%24);
			if(d2 != -1)d2 = ((d2+i)%24);
			
			break;
		}

		
	}
	

	public void initFruit(){
		bar=qiqi=star=watermelon=bell=peach=lemon=apple=0;
	}
	
	public int addScoreDef(){
		
		int i = 0;
		if(bar != 0)i += bar;
		if(qiqi != 0)i += qiqi;
		if(star != 0)i += star;
		if(watermelon != 0)i += watermelon;
		if(bell != 0)i += bell;
		if(peach != 0)i += peach;
		if(lemon != 0)i += lemon;
		if(apple != 0)i += apple;
		
		return i;
	}
	
	public void addScore(byte scoreState,boolean isMusicOn){
		
		
		switch (scoreState) {
		case FRUIT_BAR:
			if(totle > 0 && bar < 99){
				bar++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_77:
			if(totle > 0 && qiqi < 99){
				qiqi++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_STAR:
			if(totle > 0  && star < 99){
				star++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_WATERTMELON:
			if(totle > 0  && watermelon < 99){
				watermelon++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_BELL:
			if(totle > 0  && bell < 99){
				bell++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_PEACH:
			if(totle > 0 && peach < 99){
				peach++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_LEMON:
			if(totle > 0  && lemon < 99){
				lemon++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
			
		case FRUIT_APPLE:
			if(totle > 0  && apple < 99 ){
				apple++;
				totle--;
				//if(isMusicOn)player.play(canvas, "/shangfeng.wav");
			}
			break;
		}

	}
		
	
	public void gamingLevel2(){
		
		
		a1 = ((a1+12)%24);
		a2 = ((a2+12)%24);
		b1 = ((b1+8)%24);
		b2 = ((b2+8)%24);
		c1 = ((c1+4)%24);
		c2 = ((c2+4)%24);
		
	}
	
	
	public void reduceRect(int i) {

		switch (i) {
		case 0:
			a1 = -1;
			break;
			
		case 1:
			a2 = -1;
			break;
			
		case 2:
			b1 = -1;
			break;
			
		case 3:
			b2 = -1;
			break;
			
		case 4:
			c1 = -1;
			break;
			
		case 5:
			c2 = -1;
			break;
		case 6:
			d1 = -1;
		}
		
	}
	
	
	public byte getGamingProbability() {

		int i = random.nextInt(100) + 1;
		byte result = 0;
		if (i >= 1 && i <= 19)
			result = (byte) 24;
		if (i >= 20 && i <= 38)
			result = (byte) 25;
		if (i >= 39 && i <= 57)
			result = (byte) 28;
		if (i >= 58 && i <= 76)
			result = (byte) 27;
		if (i >= 77 && i <= 95)
			result = (byte) 29;
		if (i >= 96 && i <= 100)
			result = (byte) 26;
		return result;
		 
		
		//---测试用--
		//return (byte)29;

	}
	
public boolean handleResult(int result) {
		
		boolean isLucky = false;
		switch (result) {
		case 0:
		case 12:
			shortResult = FRUIT_LEMON;
			isBig = true;
			break;
		case 11:
			shortResult = FRUIT_LEMON;
			isBig = false;
			break;
		case 1:
		case 13:
			shortResult = FRUIT_BELL;
			isBig = true;
			break;
		case 23:
			shortResult = FRUIT_BELL;
			isBig = false;
			break;
		case 5:
		case 10:
		case 16:
			shortResult = FRUIT_APPLE;
			isBig = true;
			break;
		case 6:
		case 18:
			shortResult = FRUIT_PEACH;
			isBig = true;
			break;
		case 17:
			shortResult = FRUIT_PEACH;
			isBig = false;
			break;
		case 7:
			shortResult = FRUIT_WATERTMELON;
			isBig = true;
			break;
		case 8:
			shortResult = FRUIT_WATERTMELON;
			isBig = false;
			break;
		case 19:
			shortResult = FRUIT_STAR;
			isBig = true;
			break;
		case 20:
			shortResult = FRUIT_STAR;
			isBig = false;
			break;
		case 15:
			shortResult = FRUIT_77;
			isBig = true;
			break;
		case 14:
			shortResult = FRUIT_77;
			isBig = false;
			break;
		case 2:
			shortResult = FRUIT_BAR;
			isMediumBar = true;
			isBig = false;
			break;
		case 4:
			shortResult = FRUIT_BAR;
			isBig = false;
			break;
		case 3:
			shortResult = FRUIT_BAR;
			isBig = true;
			break;
			
		}
		
		
		switch (shortResult) {
		case FRUIT_BAR:
			if(bar != 0){
				isLucky = true;
				if(isBig == true){
					winScore += bar*100;
					score += bar*100;
					totle += bar*100;
					
				}else if(isMediumBar == true){
					
					winScore += bar*50;
					score += bar*50;
					totle += bar*50;
					
				}else{
					
					winScore += bar*25;
					score += bar*25;
					totle += bar*25;
					
				}
				
			}
			break;
			
		case FRUIT_77:
			if(qiqi != 0){
				isLucky = true;
				if(isBig == true){
					winScore += qiqi*40;
					score += qiqi*40;
					totle += qiqi*40;
				}else{
					winScore += qiqi*2;
					score += qiqi*2;
					totle += qiqi*2;
				}
				
				

			}
			break;
			
		case FRUIT_STAR:
			if(star != 0){
				isLucky = true;
				if(isBig == true){
					winScore += star*30;
					score += star*30;
					totle += star*30;
					
				}else{
					winScore += star*2;
					score += star*2;
					totle += star*2;
					
				}
				
			}
			break;
			
		case FRUIT_WATERTMELON:
			if(watermelon != 0){
				isLucky = true;
				if(isBig == true){
					winScore += watermelon*20;
					score += watermelon*20;
					totle += watermelon*20;
					
				}else{
					winScore += watermelon*2;
					score += watermelon*2;
					totle += watermelon*2;
					
				}
				
			}
			break;
			
		case FRUIT_BELL:
			if(bell != 0){
				isLucky = true;
				if(isBig == true){
					winScore += bell*20;
					score += bell*20;
					totle += bell*20;
					
				}else{
					winScore += bell*2;
					score += bell*2;
					totle += bell*2;
					
				}

			}
			break;
			
		case FRUIT_PEACH:
			if(peach != 0){
				isLucky = true;
				if(isBig == true){
					winScore += peach*15;
					score += peach*15;
					totle += peach*15;
					
				}else{
					winScore += peach*2;
					score += peach*2;
					totle += peach*2;
					
				}
				
			}
			break;
			
		case FRUIT_LEMON:
			if(lemon != 0){
				isLucky = true;
				if(isBig == true){
					winScore += lemon*10;
					score += lemon*10;
					totle += lemon*10;
					
				}else{
					winScore += lemon*2;
					score += lemon*2;
					totle += lemon*2;
					
				}

			}
			break;
			
		case FRUIT_APPLE:
			if(apple != 0){
				isLucky = true;
				if(isBig == true){
					winScore += apple*5;
					score += apple*5;
					totle += apple*5;
					
				}else{
					winScore += apple*2;
					score += apple*2;
					totle += apple*2;
					
				}
			}
			break;
		}
		
		return isLucky;
	}

	public void twoPlay(int gamingMoreState){
		
		switch (gamingMoreState) {
		case 9:
			if(a1 == -1)a1 = 9;
			if(a2 == -1)a2 = 9;
			if(a1 != -1)a1 = ((a1+random.nextInt(3)+1)%24);
			if(a2 == 0)a2 = 24;if(a2 != -1)a2 = ((a2-1)%24);
			
			break;
	
		case 21:
			
			if(a1 == -1)a1 = 21;
			if(a2 == -1)a2 = 21;
			if(a1 != -1)a1 = ((a1+random.nextInt(3)+1)%24);
			if(a2 == 0)a2 = 24;if(a2 != -1)a2 = ((a2-1)%24);
			
			break;
		}
		
	}
	
	public boolean isGameOver(){
		
		boolean isLost = false;
		if(totle <= 0){
			isLost = true;
		}
		return isLost;
	}
	
	public void treeTrain(int gamingMoreState){

		int i = random.nextInt(2)+1;
		switch (gamingMoreState) {
		case 9:
			
			switch (direction) {
			case DIRECTION_LEFT:
				
				if(a1 == -1)a1 = 10;
				if(a2 == -1)a2 = 11;
				if(d1 == -1)d1 = 12;
				if(a1 != -1)a1 = ((a1+i)%24);
				if(a2 != -1)a2 = ((a2+i)%24);
				if(d1 != -1)d1 = ((d1+i)%24);
				break;

			case DIRECTION_RIGHT:
				
				if(a1 == -1)a1 = 8;
				if(a2 == -1)a2 = 7;
				if(d1 == -1)d1 = 6;
				if(a1 == 0)a1 = 24;if(a1 != -1)a1 = ((a1-1)%24);
				if(a2 == 0)a2 = 24;if(a2 != -1)a2 = ((a2-1)%24);
				if(d1 == 0)d1 = 24;if(d1 != -1)d1 = ((d1-1)%24);
				break;
			}
			
			break;

		case 21:
			
			switch (direction) {
			case DIRECTION_LEFT:
				
				if(a1 == -1)a1 = 20;
				if(a2 == -1)a2 = 19;
				if(d1 == -1)d1 = 18;
				
				if(a1 != -1)a1 = ((a1+i)%24);
				if(a2 != -1)a2 = ((a2+i)%24);
				if(d1 != -1)d1 = ((d1+i)%24);
				break;

			case DIRECTION_RIGHT:
				
				if(a1 == -1)a1 = 22;
				if(a2 == -1)a2 = 23;
				if(d1 == -1)d1 = 0;
				if(a1 == 0)a1 = 24;if(a1 != -1)a1 = ((a1-1)%24);
				if(a2 == 0)a2 = 24;if(a2 != -1)a2 = ((a2-1)%24);
				if(d1 == 0)d1 = 24;if(d1 != -1)d1 = ((d1-1)%24);
				break;
			}
			
			break;
		}
		
		
		
	}
	
	
	public void allWin(){
		isBig = true;
		for (int i = 0; i < 24; i++) {
			handleResult(i);
		}
		
	}

	
	public void getResult(){
		int small = 27;
		int big = 10;
		int stars = 3;
		int qiqi = 2;
		int smallBar = 3;
		int mediumBar = 2;
		int bigBar = 1;		//保持1不要变
		int specialImage = 12;
		
		
		int j = random.nextInt(100)+1;
		if(j >= 1 && j <= small){
			
			//小物品
			int i = random.nextInt(6)+1;
			switch (i) {
			case 1:
				result = 8;
				break;
			case 2:
				result = 11;
				break;
			case 3:
				result = 14;
				break;
			case 4:
				result = 17;
				break;
			case 5:
				result = 20;
				break;
			case 6:
				result = 23;
				break;
			}
			
			
		}else if (j >= (small+1) && j <= (small + big)) {
			
			//12苹果
			int i  = random.nextInt(4)+1;
			switch (i) {
			case 1:
				result = 5;
				break;
				
			case 2:
				result = 10;
				break;
				
			case 3:
				result = 16;
				break;
				
			case 4:
				result = 22;
				break;

			}
			
			
			
		}else if (j >= (small + big + 1) && j <= (small+big*2)) {
			
			//12柠檬
/*			int i = random.nextInt(2)+1;
			switch (i) {
			case 1:
				result = 0;
				break;
			case 2:
				result = 12;
				break;
			}*/result = 12;
			
		}else if (j >= (small+big*2+1) && j <= (small+big*3)) {
			
			//12桃子（木瓜）
			int i = random.nextInt(2)+1;
			switch (i) {
			case 1:
				result = 6;
				break;
			case 2:
				result = 18;
				break;
			}
			
			
		}else if (j >= (small+big*3+1) && j <= (small+big*4)) {
			//12bell
			int i = random.nextInt(2)+1;
			switch (i) {
			case 1:
				result = 1;
				break;
			case 2:
				result = 13;
				break;
			}
			
			
		}else if (j >= (small+big*4+1) && j <= (small+big*5)) {
			//12watermelon
			result = 7;
			
		}else if (j >= (small+big*5+1) && j <= (small+big*5+stars)) {
			//3star
			result = 19;
		}else if (j >= (small+big*5+stars+1) && j <= (small+big*5+stars+qiqi)) {
			//2qiqi
			result = 15;
		}else if (j == (small+big*5+stars+qiqi+bigBar)) {
			//1big bar
			result = 3;
		}else if (j >= (small+big*5+stars+qiqi+bigBar+1) && j <= (small+big*5+stars+qiqi+bigBar+specialImage)){
			//9logo
			int i = random.nextInt(2)+1;
			switch (i) {
			case 1:
				result = 9;
				break;
			case 2:
				result = 21;
				break;
			}
					
					
		}else if (j >= (small+big*5+stars+qiqi+bigBar+specialImage+1) && j <= (small+big*5+stars+qiqi+bigBar+specialImage+mediumBar)) {
			//2mediumbar
			result = 2;
			
		}else if (j >= small+big*5+stars+qiqi+bigBar+specialImage+mediumBar+1 && j <= small+big*5+stars+qiqi+bigBar+specialImage+mediumBar+smallBar) {
			//3small bar
			result = 4;
		}
		
		
		//-----------测试用-----------
		//result = 9;
	}
	
	public void getDice() {

		//dice = random.nextInt(2);
		dice = 0;
	}
	
	public boolean isGuessGameWin(){
		
		boolean isWin = false;
		
		if(dice == 0 && guessChoice == 0){
			isWin = true;
		}else if(dice == 1 && guessChoice == 1){
			isWin = true;
		}
		
		return isWin;
	}


	@Override
	public String toString() {
		return "Machine [totle=" + totle + ", score=" + score + ", winScore="
				+ winScore + ", direction=" + direction + ", oldTotle="
				+ oldTotle + ", totleScore2=" + totleScore2 + "]";
	}
	
	
	public void doYuwang(){
		
		a1 = random.nextInt(24);
		a2 = (a1+1)%24;
		b1 = (a2+1)%24;
		
		b2 = c1 = c2 = d1 = d2 = -1;
		
		
	}
	
	public void doYugou(){
		
	}
	
	public void doZhadan(){
		
	}
	
	public float[] getNetPoint(float[] f){
		
		float width = 0,height = 0;
		if(a1 > -1 && a1 < 5){
			width = -5 + UIUtil.getPixel(67, GameScreen.HORIZONTAL)*a1;
			height = 0f;
			
		}else if(a1 == 5){
			
			width = UIUtil.getPixel(308, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(35, GameScreen.VERTICAL);
			
		}else if (a1 > 5 && a1 < 11) {
			width = UIUtil.getPixel(327, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(79, GameScreen.VERTICAL) + UIUtil.getPixel(67, GameScreen.VERTICAL)*(a1-6);
		}else if (a1 == 11) {
			
			width = UIUtil.getPixel(319, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(397, GameScreen.VERTICAL );
			
		}else if (a1 > 11 && a1 < 17) {
			
			width = UIUtil.getPixel(265, GameScreen.HORIZONTAL) - UIUtil.getPixel(67, GameScreen.HORIZONTAL)*(a1-12);
			height = UIUtil.getPixel(420, GameScreen.VERTICAL);
		
		}else if (a1 == 17) {
			
			width = UIUtil.getPixel(-53, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(391, GameScreen.VERTICAL);
			
		}else if (a1 > 17 && a1 < 23) {
			
			width = UIUtil.getPixel(-76, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(344, GameScreen.VERTICAL) - UIUtil.getPixel(67,GameScreen.VERTICAL)*(a1 - 18);
			
		}else if(a1 == 23){
			
			width = UIUtil.getPixel(-54, GameScreen.HORIZONTAL);
			height = UIUtil.getPixel(15, GameScreen.VERTICAL);
			
		}
		
		f[0] = width;
		f[1] = height;
		return f;
	}
	
	
	public void init8point(){
		
		//初始化8个光标点
		a1 = random.nextInt(24);
		a2 = (a1+1)%24;
		b1 = (a1+6)%24;
		b2 = (b1+1)%24;
		c1 = (b1+6)%24;
		c2 = (c1+1)%24;
		d1 = (c1+6)%24;
		d2 = (d1+1)%24;
		
	}
	
	
	public void initBe1(){
		
		bar = qiqi = star = watermelon = bell = peach = lemon = apple = 1;
		
	}
	
	public void allPointBeGray(){
		
		a1 = a2 = b1 = b2 = c1 = c2 = d1 = d2 = -1;
		
		
	}
	
	
}







