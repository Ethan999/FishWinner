package com.histudio.fishwinner.util;

import android.graphics.Bitmap;

public class ButtonImg {

	private int index = 0;
	
	private Bitmap[] imgs = new Bitmap[2];
	
	public void setFirstBitmap(Bitmap bitmap) {

		imgs[0] = bitmap;

	}

	public void setSecondBitmap(Bitmap bitmap) {

		imgs[1] = bitmap;

	}

	public Bitmap getBitmap() {

		if(index == 1){
			return imgs[1];
			
		}
		return imgs[0];
	}
	
	
	public void beNormal(){  
		
		index = 0;
	}
	
	public void bePressed(){
		index = 1;
	}
}





