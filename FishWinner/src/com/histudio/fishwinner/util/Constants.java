package com.histudio.fishwinner.util;

import com.histudio.fishwinner.R;

public class Constants {

	//对话框类别
	public static final int STOP_DIALOG = 0;
	public static final int GAME_MENU=3;
	public static final int ASK_DIALOG=4;
	public static final int EXITGAME_DIALOG=5;
	public static final int ASK_CONTINUE = 6;
	public static final int PASS_DIALOG = 7;
	public static final int FAIL_DIALOG = 8;
	
	//数据库
	public static final String DB_NAME = "db_FishWinner";
	public static final String TB_NAME = "tb_fruit";
	
	//屏幕id
	public static final int NO_PRIVIOUS = 0;
	public static final int MENU_ID = 10;
	public static final int	GAMING_ID = 11;
	
	//背景音乐状态
	public static final int NON_AUDIO = 1; 
	public static final int MENU_AUDIO = R.raw.menubg;
	public static final int GAMING_AUDIO = R.raw.gamingbg;

	//保存信息
	public static final String SP_NAME = "fishwinner";
	
	//当前的关卡和最大关卡
	public static int CURRENT_LEVEL = 1;
	public static int MAX_LEVEL = 1;
	public static final int ALL_LEVEL = 12;
}
