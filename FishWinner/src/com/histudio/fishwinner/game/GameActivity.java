package com.histudio.fishwinner.game;

import com.histudio.fishwinner.dialog.AskContinueDialog;
import com.histudio.fishwinner.dialog.AskLastDialog;
import com.histudio.fishwinner.dialog.ExitGameDialog;
import com.histudio.fishwinner.dialog.FailDialog;
import com.histudio.fishwinner.dialog.GameMenuDialog;
import com.histudio.fishwinner.dialog.PassDialog;
import com.histudio.fishwinner.dialog.StopDialog;
import com.histudio.fishwinner.entity.Machine;
import com.histudio.fishwinner.screen.MainScreen;
import com.histudio.fishwinner.screen.MenuScreen;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.DatabaseHelper;
import com.histudio.fishwinner.util.UIUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	
	private static GameActivity instance;
	private GameView gameView ;
	private boolean isPause = false;
	private Handler handler;
	private boolean musicState = true;
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//自身引用
		instance = this;
		// 设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置屏幕信息
		UIUtil.setDeviceInfo(this);
		// 显示自定义的SurfaceView视图
		gameView = new GameView(this);
		setContentView(gameView);
		//处理非ui线程事件
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				handleUIEvent(msg.what);
			}
		};
		
    }

    
    @Override
    protected void onResume() {
    	if(isPause == true){
    		showDialog(Constants.STOP_DIALOG);
    	}
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	isPause = true;
    	musicState = GameView.getMusicPlayer().isPlayerSwitch();
    	if(GameView.getMusicPlayer().isPlayerSwitch())GameView.getMusicPlayer().pause();
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
		if(GameView.getGamingScreen() != null){
			storeRecoreds();
		}
    	super.onDestroy();
    	
    	handler.post(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
    }
    
    @Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog dialog=null;
		switch (id) {
		case Constants.STOP_DIALOG:
			dialog = new StopDialog(this);
			break;
		case Constants.ASK_DIALOG:
			dialog = new AskLastDialog(this);
			break;
		case Constants.EXITGAME_DIALOG:
			dialog = new ExitGameDialog(this);
			break;
		case Constants.GAME_MENU:
			dialog = new GameMenuDialog(this);
			break;
		case Constants.ASK_CONTINUE:
			dialog = new AskContinueDialog(this);
			break;
		case Constants.PASS_DIALOG:
			dialog = new PassDialog(this);
			break;
		case Constants.FAIL_DIALOG:
			dialog = new FailDialog(this);
			break;
		}
		return dialog;
	}
    
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			if(GameView.getGameScreen() instanceof MenuScreen)
				showDialog(Constants.EXITGAME_DIALOG);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

    
    public static GameActivity getInstance(){
    	
    	return instance;
    	
    }

	public boolean isPause() {
		return isPause;
	}


	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public void storeRecoreds() {

		DatabaseHelper databaseHelper = new DatabaseHelper(this, Constants.DB_NAME);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Machine machine = MainScreen.getMachine();
		if (machine != null) {

			ContentValues values = new ContentValues();
			values.put("score", machine.oldTotle);
			db.insert(Constants.TB_NAME, null, values);
		}
		db.close();
	}
	
	
	public boolean hasRecoreds(){
		
		DatabaseHelper databaseHelper = new DatabaseHelper(this, Constants.DB_NAME);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.query(Constants.TB_NAME, new String[]{"score"},
								null, null, null, null, null);
		int a = cursor.getCount();
		db.close();
		if(a>0)return true;
		else return false;
	}
	
	public int getLastRecored(){
		
		DatabaseHelper databaseHelper = new DatabaseHelper(this, Constants.DB_NAME);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.query(Constants.TB_NAME, new String[]{"score"},
								null, null, null, null, null);
		int a = cursor.getCount();
		int result = 500;
		if(a>0){
			cursor.moveToLast();
			result = cursor.getInt(0);
		}
		db.close();
		return result;
	}
	
	public int[] getRanks(){
		DatabaseHelper databaseHelper = new DatabaseHelper(this, Constants.DB_NAME);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor cursor = db.query(Constants.TB_NAME, new String[]{"score"},
								null, null, null, null, null);
		int[] ranks = new int[cursor.getCount()];
		int i = 0;
		while(cursor.moveToNext()){
			boolean isinranks = false;
			int k = cursor.getInt(0);
			for (int j = 0 ; j < ranks.length ;j++) {
				if(k == ranks[j])isinranks = true;
			}
			if(isinranks == false){
				ranks[i] = k;
				i++;
			}
		}
		sort(ranks);
		cursor.close();
		cursor = null;
		db.close();	
		return ranks;
	}
	
	//处理非ui线程控制ui线程的一些动作
	private void handleUIEvent(int event){
		
		switch (event) {
		case Constants.ASK_DIALOG:
			showDialog(Constants.ASK_DIALOG);
			break;
		case Constants.GAME_MENU:
			showDialog(Constants.GAME_MENU);
			break;
		case Constants.EXITGAME_DIALOG:
			showDialog(Constants.EXITGAME_DIALOG);
			break;
		case Constants.ASK_CONTINUE:
			showDialog(Constants.ASK_CONTINUE);
			break;
		case Constants.PASS_DIALOG:
			showDialog(Constants.PASS_DIALOG);
			break;
		case Constants.FAIL_DIALOG:
			showDialog(Constants.FAIL_DIALOG);
			break;
		}
		
	}
	
	public boolean isMusicState() {
		return musicState;
	}


	//排序算法
	private void sort(int[] data) {


		for (int i = 1; i < data.length; i++) {

			for (int j = i; (j > 0) && (data[j] > data[j - 1]); j--) {

				swap(data, j, j - 1);

			}
		}
	}

	private  void swap(int[] data, int i, int j) {

		int temp = data[i];

		data[i] = data[j];

		data[j] = temp;

	}

}





