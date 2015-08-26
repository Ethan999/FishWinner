package com.histudio.fishwinner.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;

public class UIUtil {

	//基准分辨率
	public static int BASE_WIDTH = 480;
	public static int BASE_HEIGHT = 800;
	public static float BASE_DENSITY = 1.5f;
	
	//屏幕信息
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static float DENSITY;
	
	//放大缩小
	public static float SCALE_WIDTH ;
	public static float SCALE_HEIGHT;
	
	//垂直和水平常量
	public static final int HORIZONTAL = 10;
	public static final int VERTICAL = 11;
	
	//在入口activity的oncreate方法中调用
	public static void setDeviceInfo(Activity context){
		
		//设置屏幕有关的参数
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		SCREEN_WIDTH = dm.widthPixels;
		SCREEN_HEIGHT = dm.heightPixels;
		DENSITY = dm.density;
		SCALE_WIDTH = (float)SCREEN_WIDTH/BASE_WIDTH;
		SCALE_HEIGHT = (float)SCREEN_HEIGHT/BASE_HEIGHT;
		
		System.out.println(
				"Screen_width:"+SCREEN_WIDTH
				+"  screen_height:"+SCREEN_HEIGHT
				+"  scale_width:"+SCALE_WIDTH
				+"  scale_height"+ SCALE_HEIGHT
				+"  densty"+dm.density
				+"  denstyDPI"+dm.densityDpi);
		
	}
	
	public static float getPixel(float num , int i){
		
		if(i == HORIZONTAL)return num*SCALE_WIDTH;
		else if (i == VERTICAL) return num*SCALE_HEIGHT;
		else return num;
	}
	
	public static Bitmap loadBitmap(Context context,int id){
		
		return BitmapFactory.decodeResource(context.getResources(), id);
		
	}

	public static Bitmap loadBitmap(Context context,int id,float width,float height){
		
		Bitmap bitmap = loadBitmap(context, id);
		Matrix matrix = new Matrix();
		matrix.postScale(width,height);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);

	}
	
	public static Bitmap resizeBitmap(Bitmap bitmap,float width,float height){
		
		Matrix matrix = new Matrix();
		matrix.postScale(width,height);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
		
	}
	
	//drawable-nodip文件夹下面使用最好
	//basesize的图片缩放到屏幕大小
	public static Bitmap loadBitmapByResize(Context context,int id){
		
		return loadBitmap(context, id, SCALE_WIDTH, SCALE_HEIGHT);
		
		//和byDensity同理
		//return loadBitmap(context, id, BASE_DENSITY/DENSITY*SCALE_WIDTH, BASE_DENSITY/DENSITY*SCALE_HEIGHT);
		
	}

	//drawable文件夹带有后缀的时候使用
	//如果放在带有-dpi后缀的drawable文件夹下，先去掉系统自动的缩放，再缩放到base_density大小,之后再根据分辨率缩放
	//带有后缀的drawable文件夹中的图片系统自动压缩，所以显示的质量有所下降
	public static Bitmap loadBitmapByDensity(Context context,int id){
		
		return loadBitmap(context, id, BASE_DENSITY/DENSITY*SCALE_WIDTH, BASE_DENSITY/DENSITY*SCALE_HEIGHT);
		
	}
	
	public static float getImageDensty(String densty){
		
		if(densty.equals("ldpi"))return 0.8f;
		else if(densty.equals("mdpi"))return 1.0f;
		else if(densty.equals("hdpi"))return 1.5f;
		else if(densty.equals("xdpi"))return 2f;
		else return 1f;
	}
	
	public static int[] getNum(int i){   //把数值拆分成单个数字，最多6位
		
		int count = 1;
		if(i >= 10)count++;
		if(i >= 100)count++;
		if(i >= 1000)count++;
		if(i >= 10000)count++;
		if(i >= 100000)count++;
		int[] num = new int[count];
		num[0] = i%10;
		if(count>1)num[1] = ((i-num[0])%100)/10;
		if(count>2)num[2] = ((i-num[0]-num[1])%1000)/100;
		if(count>3)num[3] = ((i-num[0]-num[1]-num[2])%10000)/1000;
		if(count>4)num[4] = ((i-num[0]-num[1]-num[2]-num[3])%100000)/10000;
		if(count>5)num[5] = ((i-num[0]-num[1]-num[2]-num[3]-num[4])%1000000)/100000;
		return num;
	}
	
	public static void getNumImage(int i,float x,float y,Bitmap[] nums,Canvas c,Paint paint) {
		if(i < 0 ){
			try {
				throw new Exception("输入的数值为负数："+i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int j = 0;
		int[] num = getNum(i);
		float beginPoint = x-num.length*(nums[0].getWidth()/2);
		for (int k = num.length - 1; k >= 0 ; k--) {
			c.drawBitmap(nums[num[k]], beginPoint+nums[k].getWidth()*j, y, paint);
			j++;
		}
	}
	
}

