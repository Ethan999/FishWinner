package com.histudio.fishwinner.game;

import com.histudio.fishwinner.util.UIUtil;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
	
	public static final int HORIZONTAL = 10;
	public static final int VERTICAL = 11;
	
	protected GameActivity context ;
	protected int width,height;
	protected Paint paint;
	
	public GameObject() {

		context = GameActivity.getInstance();
		width = UIUtil.SCREEN_WIDTH;
		height = UIUtil.SCREEN_HEIGHT;
		paint = new Paint();
	
	}
	
	public void init(){};
	public void render(Canvas c){};
	public void playSound(){};
	public void update(){};
	public void handleEvent(){};
	public void destroy(){};

	
}
