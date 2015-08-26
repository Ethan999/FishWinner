package com.histudio.fishwinner.entity;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.game.GameObject;
import com.histudio.fishwinner.util.UIUtil;

public class QiPao extends GameObject{

	private Bitmap img_qipao,img_qipao1,img_qipao2,img_qipao3,img_qipao4,img_qipao5,img_qipao6;
	private Random random = new Random();
	private int state = 1;
	//private int index;
	//private int speed;
	
	public QiPao() {
		init();
	}
	
	public QiPao(int state) {
		init();
		this.state = state;
	}
	
	@Override
	public void init() {
		
		img_qipao = UIUtil.loadBitmapByResize(context, R.drawable.qipao);
		img_qipao1   = UIUtil.loadBitmapByResize(context, R.drawable.qipao1);
		img_qipao2   = UIUtil.loadBitmapByResize(context, R.drawable.qipao2);
		img_qipao3   = UIUtil.loadBitmapByResize(context,R.drawable.qipao3);
		img_qipao4   = UIUtil.loadBitmapByResize(context, R.drawable.qipao4);
		img_qipao5   = UIUtil.loadBitmapByResize(context, R.drawable.qipao5);
		img_qipao6   = UIUtil.loadBitmapByResize(context, R.drawable.qipao6);
 	
	}
	
	@Override
	public void render(Canvas c) {
		
		switch (state) {
		case 0:
			c.drawBitmap(img_qipao, UIUtil.getPixel(110, HORIZONTAL), 
					UIUtil.getPixel(550, VERTICAL), paint);
			
			c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+440, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+490, VERTICAL), paint);
			/*c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+435, HORIZONTAL), 
				UIUtil.getPixel(random.nextInt(10)+410, VERTICAL), paint);
			*/	
			
			break;
		case 1:
			c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+110, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(25)+530, VERTICAL), paint);
			
			c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+420, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+460, VERTICAL), paint);
			/*c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+415, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+470, VERTICAL), paint);
			*/break;

		case 2:
			c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+140, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(20)+500, VERTICAL), paint);		
			
			c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+405, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+430, VERTICAL), paint);
			/*c.drawBitmap(img_qipao1, UIUtil.getPixel(random.nextInt(10)+400, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+440, VERTICAL), paint);
		*/	break;
		case 3:
			c.drawBitmap(img_qipao3, UIUtil.getPixel(random.nextInt(10)+120, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(20)+470, VERTICAL), paint);		
			
			c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+395, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+400, VERTICAL), paint);
			/*c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+390, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+410, VERTICAL), paint);
		*/	break;
		case 4:
			c.drawBitmap(img_qipao4, UIUtil.getPixel(random.nextInt(10)+135, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+450, VERTICAL), paint);		
			
			c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+390, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+370, VERTICAL), paint);
			/*c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+385, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+380, VERTICAL), paint);
			*/break;
		case 5:
			c.drawBitmap(img_qipao5, UIUtil.getPixel(random.nextInt(10)+125, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+410, VERTICAL), paint);		
			
			c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+395, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+340, VERTICAL), paint);
			/*c.drawBitmap(img_qipao2, UIUtil.getPixel(random.nextInt(10)+390, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+350, VERTICAL), paint);
			*/break;
		case 6:
			c.drawBitmap(img_qipao6, UIUtil.getPixel(random.nextInt(10)+140, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+380, VERTICAL), paint);		
			
			c.drawBitmap(img_qipao4, UIUtil.getPixel(random.nextInt(10)+405, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+310, VERTICAL), paint);
			/*c.drawBitmap(img_qipao4, UIUtil.getPixel(random.nextInt(10)+400, HORIZONTAL), 
					UIUtil.getPixel(random.nextInt(10)+320, VERTICAL), paint);
			*/break;
		}
	
	}
	
	@Override
	public void update() {


			state++;
			if (state > 6)
				state = 0;
		
	}	
}

















