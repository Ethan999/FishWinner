package com.histudio.fishwinner.game;

import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.UIUtil;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameScreen {
	
	public static final int HORIZONTAL = 10;
	public static final int VERTICAL = 11;
	
	protected GameActivity context ;
	protected int width,height;
	protected Paint paint;
	protected int priviousScreenID = Constants.NO_PRIVIOUS;
	protected int bgMusicState = Constants.MENU_AUDIO;
	
	public GameScreen() {

		context = GameActivity.getInstance();
		width = UIUtil.SCREEN_WIDTH;
		height = UIUtil.SCREEN_HEIGHT;
		paint = new Paint();
	
	}
	
	
	protected void init(){};
	protected void render(Canvas c){};
	protected void playSound(){};
	protected void update(){};
	protected void handleEvent(){};
	protected void destroy(){};
	
}
