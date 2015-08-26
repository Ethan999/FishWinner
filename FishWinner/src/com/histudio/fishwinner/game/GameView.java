package com.histudio.fishwinner.game;

import com.histudio.fishwinner.entity.Machine;
import com.histudio.fishwinner.screen.ConfigrationScreen;
import com.histudio.fishwinner.screen.GameOverScreen;
import com.histudio.fishwinner.screen.HelpScreen;
import com.histudio.fishwinner.screen.MainScreen;
import com.histudio.fishwinner.screen.MenuScreen;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.MusicPlayer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class GameView extends SurfaceView implements Callback, Runnable {
	
	public static GameView instance;
	private SurfaceHolder sfh;//用于控制SurfaceView
	private Thread th;//声明一条线程
	private boolean isRun;//线程消亡的标识位
	private Canvas canvas;//声明一个画布
	private PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0,
			Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);// 设置画布绘图无锯齿
	private static GameScreen gameScreen;
	private static int FrameTime = 100;
	private static MainScreen mainScreen = null;
	private static int message = 0;
	private static MusicPlayer musicPlayer = null;
	
	public GameView(Context context) {
		super(context);
		instance = this;
		sfh = this.getHolder();
		setFocusable(true);
		sfh.addCallback(this);
		musicPlayer = new MusicPlayer(context);
		if(gameScreen == null)setGameScreen(new MenuScreen());
	}
	
	public static GameView getInstance(){
		return instance;
	}

	public void onPaint() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.setDrawFilter(pfd);
				canvas.drawColor(Color.WHITE);
				gameScreen.render(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}

	public void doAudio(){
		
		if (musicPlayer.isPlayerSwitch()) {
			if (musicPlayer.getMusicState() == Constants.NON_AUDIO) {
				musicPlayer.play(gameScreen.bgMusicState);
			}
			if (gameScreen.bgMusicState != musicPlayer.getMusicState()) {

				musicPlayer.switchMusic(gameScreen.bgMusicState);

			}
		}
	}
	
	@Override
	public synchronized void run() {
		while (isRun) {
			long start = System.currentTimeMillis();
			synchronized (this) {
				onPaint();
				doAudio();
				update();
				handleEvent();
				cycleMsg();
			}
			long end = System.currentTimeMillis();
			long time = end - start ;
			//System.out.println("time   :"+time);
			try {
				if (time < FrameTime) {
					Thread.sleep(FrameTime - time);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		gameScreen.update();
	}
	
	public void handleEvent(){
		
		gameScreen.handleEvent();
		
	}
	
	public void cycleMsg(){
		
		switch (message) {
		case 1://转到帮助
			HelpScreen helpScreen = new HelpScreen();
			helpScreen.priviousScreenID = Constants.GAMING_ID;
			setGameScreen(helpScreen);
			break;
		case 2://转到主菜单
			setGameScreen(new MenuScreen());
			break;
		case 3://设置
			ConfigrationScreen configrationScreen = new ConfigrationScreen();
			configrationScreen.priviousScreenID = Constants.GAMING_ID;
			setGameScreen(configrationScreen);
			break;
		case 4://旧的gaming
			setGameScreen(new MainScreen());
			break;
		case 5://新的gaming
			mainScreen = null;
			setGameScreen(new MainScreen());
			break;
		case 6://填入记录
			mainScreen = null;
			setGameScreen(new MainScreen());
			int result = GameActivity.getInstance().getLastRecored();
			MainScreen.getMachine().totle = result;
			break;
		case 7://过关
			if(Constants.CURRENT_LEVEL == Constants.MAX_LEVEL){
				if(Constants.ALL_LEVEL == Constants.MAX_LEVEL){
					GameView.setGameScreen(new GameOverScreen());
				}else {
					Constants.CURRENT_LEVEL += 1;
					Constants.MAX_LEVEL += 1;
					SharedPreferences sp = GameActivity.getInstance()
					.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
					Editor editor = sp.edit();
					editor.putInt("maxLevel", Constants.MAX_LEVEL);
					editor.commit();
					MainScreen mainScreen = new MainScreen();
					MainScreen.getMachine().initLevel();
					GameView.setGameScreen(mainScreen);
				}
			}else {
				
				Constants.CURRENT_LEVEL += 1;
				MainScreen mainScreen = new MainScreen();
				MainScreen.getMachine().initLevel();
				GameView.setGameScreen(mainScreen);		
			}
			break;
		}
		
		message = 0;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		event.getAction();
		HandleTouch.onTouch(event.getAction(), event.getX(), event.getY());
		return true;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		isRun = true;
		th = new Thread(this);
		th.start();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isRun = false;
	}
	
	
	
	public  static void setGameScreen(GameScreen Screen){
		
			//如果是gamingScreen类的话，直接使用原先的。（主游戏屏幕不释放）
			if (Screen instanceof MainScreen) {
				if (mainScreen != null) {
					Screen = null;
					gameScreen = mainScreen;
					gameScreen.init();
					return;
				} else {
					mainScreen = (MainScreen) Screen;
					MainScreen.setMachine(new Machine());
				}
			}
			if (gameScreen != null)gameScreen.destroy();
			gameScreen = Screen;
			gameScreen.init();
		
	}
	
	public static void setGameScreen(GameScreen Screen,int screenId){
		
		setGameScreen(Screen);
		gameScreen.priviousScreenID = screenId;
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public static int getFrameTime() {
		return FrameTime;
	}

	public static void setFrameTime(int frameTime) {
		FrameTime = frameTime;
	}
	
	public static void rePaint(){
	
		GameView.getInstance().onPaint();
		
	}

	public static MainScreen getGamingScreen() {
		return mainScreen;
	}

	public static void setGamingScreen(MainScreen mainScreen) {
		GameView.mainScreen = mainScreen;
	}


	public static void setMessage(int message) {
		GameView.message = message;
	}
	
	public static MusicPlayer getMusicPlayer() {
		return musicPlayer;
	}
	
}
























