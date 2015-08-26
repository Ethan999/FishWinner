package com.histudio.fishwinner.screen;

import java.util.Random;
import com.histudio.fishwinner.R;
import com.histudio.fishwinner.entity.Level;
import com.histudio.fishwinner.entity.Machine;
import com.histudio.fishwinner.game.GameView;
import com.histudio.fishwinner.util.ButtonImg;
import com.histudio.fishwinner.util.Constants;
import com.histudio.fishwinner.util.HandleTouch;
import com.histudio.fishwinner.util.SoundPoolPlayer;
import com.histudio.fishwinner.util.TipHelper;
import com.histudio.fishwinner.util.UIUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class MainScreen extends FishScreen{

	//������Ϸ״̬
	public static final byte BEFORE = 21;
	public static final byte DOING = 22;
	public static final byte AFTER = 23;
	//������Ϸ״̬
	public static final byte GAMING_TWOPLAY = 24;
	public static final byte GAMING_TREETRAIN = 25;
	public static final byte GAMING_ALLWIN = 26;
	public static final byte GAMING_RERUN = 27;
	public static final byte GAMING_NONE = 28;
	public static final byte GAMING_FOURTRAIN = 29;
	//����
	public static final byte DAOJU_YUWANG = 40;
	public static final byte DAOJU_YUGOU = 41;
	public static final byte DAOJU_ZHADAN = 42;
	
	//״̬����
	private byte gameState = BEFORE;
	
	//ͼƬ
	private Bitmap img_defeng,img_zhuandeng,img_yafeng,img_kaishi1;
	private Bitmap img_kaishi2,img_chongzhi,img_chongzhi2,img_caidan,img_caidan2;
	private Bitmap img_yugou,img_yugou1,img_yuwan,img_yuwan2,img_zhadan,img_zhadan2;
	private Bitmap img_yuwang;
	private Bitmap[] img_nums,img_shangfengshuzhis,img_baos;
	//��ť
	private ButtonImg butn_kaishi,butn_chongzhi,butn_caidan,butn_yuwang,butn_yugou,butn_zhadan;
	
	//����
	private static MainScreen instance;
	private static Machine machine = new Machine();	//ˮ����ʵ��
	private Random random = new Random();			//�������
	private boolean isDoingEnd ;					//�����Ƿ�����������˾Ͳ����ټ�a1234..
	private int cycleStep = 1;						//����ѭ���Ĳ���
	private byte scoreState = 15 ; 					//gaming��Ϸ���Ϸֵ�ѡ����
	private boolean redLightStat;					//���ƺ�Ƶ���˸
	private int img_before_text_state ;				//����gaming doingʱ�ı��͵Ƶ���˸
	private int DoingCount ;						//�����м��ı��ͺ����˸��Ƶ�ʣ�ʹ��������Ϸˢ��������
	private int DoingTime ;							//��doing_count���ʹ�ã���������ʱ�䣬��Ϸ�߳����м��Σ�����˸һ��
	private byte gamingDoingLevel ;					//gaming��Ϸ�Ķ���֡״̬
	private long gamingTimeDelay = 0;				//����֡�����Ŀ���λ
	public int delayTime2  ;				    	//�߳�����ʱ�䣬Ҳ����ˢ���ļ��ʱ�䣿������������
	private boolean isGamingLevel2StateRun ;		//doing�п��Ƴ���״���ݱ仯ֻ����һ��
	private int gamingMoreState ;					//����״̬�µ�״̬���棬�����ж���9���ǣ�11
	private int gamingmoreString;					//����״̬����ʾ���ı�������˫���ڣ�����ɶ��Ŷ�ף�1-˫���ڣ�2-���𳵣�3-������,4-ʮ���ź���
	private boolean isLucky ;						//gaming��Ϸʱ���ʤ
	private int d2,a1,a2,d1;						//��d2,a1,a2��ֵ��ֵ�������������Ժ�-1���棬�Դ˿���gaming_afterʱ�Ƶ���˸
	private int alertString ;						//��ʾguess��Ϸ������ı�(0-�ޣ�1��ʮ���ź�,2,��ϲ����)
	private int beforePauseFrame = 0;				//��ͣǰ��֡ʱ��
	private int shangFengState = -1;
	private int shangFengIndex = 0;
	private boolean afterAudioState = false;
	private SoundPoolPlayer spp = SoundPoolPlayer.getInstance(context);
	private int goId = 0;
	private static final int tipTime = 1500;
	private int targetScore = 1000;
	private boolean bz_drawyuwang = false;
	private int yugouState = 10;                     //�㹳״̬
	private boolean bz_isDrawLight = true;
	private int bao_index = 0;
	
	//�ɵ�ͼƬ
	private Bitmap img_bg_string;
	private Bitmap img_redLight_gray;
	private Bitmap img_lucky;
	private Bitmap img_cash_out;
	private Bitmap img_luck_cash;
	private Bitmap img_yihan;
	private Bitmap img_shuangpengdeng;
	private Bitmap img_kaihuoche;
	private Bitmap img_damanguan;
	private Bitmap img_red_l;
	private Bitmap img_ya_gray;
	private Bitmap img_red_num;
	private Bitmap img_shangfengshuzhi;
	
	public MainScreen() {
		if (GameView.getGamingScreen() != null)return;
		instance = this;
		//����ͼƬ
		img_defeng = UIUtil.loadBitmapByResize(context, R.drawable.defen);
		img_zhuandeng = UIUtil.loadBitmapByResize(context, R.drawable.zhuandeng);
		img_yafeng = UIUtil.loadBitmapByResize(context, R.drawable.yafen);
		img_bg_string = UIUtil.loadBitmapByDensity(context, R.drawable.bg_string);
		img_redLight_gray = UIUtil.loadBitmapByDensity(context, R.drawable.red_light_gray);
		img_lucky = UIUtil.loadBitmapByDensity(context, R.drawable.lucky);
		img_cash_out = UIUtil.loadBitmapByDensity(context, R.drawable.cash_out);
		img_luck_cash = UIUtil.loadBitmapByDensity(context, R.drawable.lucky_cash);
		img_yihan = UIUtil.loadBitmapByDensity(context, R.drawable.yihan);
		img_shuangpengdeng = UIUtil.loadBitmapByDensity(context, R.drawable.shuangpengdeng);
		img_kaihuoche = UIUtil.loadBitmapByDensity(context, R.drawable.kaihuoche);
		img_damanguan = UIUtil.loadBitmapByDensity(context, R.drawable.damanguan);
		img_ya_gray = UIUtil.loadBitmapByResize(context, R.drawable.ya);
		img_red_l = UIUtil.loadBitmapByDensity(context, R.drawable.red_light);
		img_yuwang = UIUtil.loadBitmapByResize(context, R.drawable.yuwang);
		//��ʼ
		img_kaishi1 = UIUtil.loadBitmapByResize(context, R.drawable.kaishi1);
		img_kaishi2 = UIUtil.loadBitmapByResize(context, R.drawable.kaishi2);
		butn_kaishi = new ButtonImg();
		butn_kaishi.setFirstBitmap(img_kaishi1);
		butn_kaishi.setSecondBitmap(img_kaishi2);
		
		//����
		img_chongzhi = UIUtil.loadBitmapByResize(context, R.drawable.chongzhi);
		img_chongzhi2 = UIUtil.loadBitmapByResize(context, R.drawable.chongzhi2);
		butn_chongzhi = new ButtonImg();
		butn_chongzhi.setFirstBitmap(img_chongzhi);
		butn_chongzhi.setSecondBitmap(img_chongzhi2);
		
		//�˵�
		img_caidan = UIUtil.loadBitmapByResize(context, R.drawable.caidan);
		img_caidan2 = UIUtil.loadBitmapByResize(context, R.drawable.caidan2);
		butn_caidan = new ButtonImg();
		butn_caidan.setFirstBitmap(img_caidan);
		butn_caidan.setSecondBitmap(img_caidan2);
		
		//����
		img_yuwan = UIUtil.loadBitmapByResize(context, R.drawable.yuwang1);
		img_yuwan2 = UIUtil.loadBitmapByResize(context, R.drawable.yuwang2);
		butn_yuwang = new ButtonImg();
		butn_yuwang.setFirstBitmap(img_yuwan);
		butn_yuwang.setSecondBitmap(img_yuwan2);
		
		//�㹳
		img_yugou = UIUtil.loadBitmapByResize(context, R.drawable.yugou);
		img_yugou1 = UIUtil.loadBitmapByResize(context, R.drawable.yugou2);
		butn_yugou = new ButtonImg();
		butn_yugou.setFirstBitmap(img_yugou);
		butn_yugou.setSecondBitmap(img_yugou1);
		
		//ը��
		img_zhadan = UIUtil.loadBitmapByResize(context, R.drawable.zhadang);
		img_zhadan2 = UIUtil.loadBitmapByResize(context, R.drawable.zhadang2);
		butn_zhadan = new ButtonImg();
		butn_zhadan.setFirstBitmap(img_zhadan);
		butn_zhadan.setSecondBitmap(img_zhadan2);

		//��ըͼƬ��
		img_baos = new Bitmap[]{
			
				UIUtil.loadBitmapByResize(context, R.drawable.bao12),
				UIUtil.loadBitmapByResize(context, R.drawable.bao22),
				UIUtil.loadBitmapByResize(context, R.drawable.bao32),
				UIUtil.loadBitmapByResize(context, R.drawable.bao42),
				UIUtil.loadBitmapByResize(context, R.drawable.bao52),
				UIUtil.loadBitmapByResize(context, R.drawable.bao62),
				UIUtil.loadBitmapByResize(context, R.drawable.bao72),
				
		};
		
		
		//����ͼƬ
		img_red_num = UIUtil.loadBitmapByResize(context, R.drawable.red_num);
		img_nums = new Bitmap[10];
		int imgnum = img_red_num.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_nums[k] = Bitmap.createBitmap(img_red_num, imgnum * k, 0, imgnum, img_red_num.getHeight());
		}
		img_red_num = null;
		
		//�Ϸ�����ͼ
		img_shangfengshuzhi = UIUtil.loadBitmapByResize(context, R.drawable.shangfengshuzhi);
		img_shangfengshuzhis = new Bitmap[10];
		imgnum = img_shangfengshuzhi.getWidth() / 10;
		for (int k = 0; k < 10; k++) {
			img_shangfengshuzhis[k] = Bitmap.createBitmap(img_shangfengshuzhi, imgnum * k, 0, imgnum, img_shangfengshuzhi.getHeight());
		}
		img_shangfengshuzhi = null;
	}
	
	@Override
	protected void init() {
		super.init();
		targetScore = Level.getTarget(Constants.CURRENT_LEVEL);
		initGaming();
		bgMusicState = Constants.GAMING_AUDIO;
	}

	@Override
	protected void render(Canvas c) {
		super.render(c);
		
		drawNormal(c);				//������Ϸ�����ж������õ��Ļ�ͼ
		switch (gameState) {
		case BEFORE:
			drawBefore(c);
			break;
		case DOING:
		case GAMING_RERUN:
		case GAMING_NONE:
			drawDoing(c);
			break;
		case AFTER:
			drawAfter(c);
			break;
		case GAMING_TWOPLAY:
		case GAMING_TREETRAIN:
		case GAMING_ALLWIN:
			drawOther(c);
			break;
		case DAOJU_YUWANG:
			drawYuwang(c);
			break;
		case DAOJU_YUGOU:
			drawYugou(c);
			break;
		case DAOJU_ZHADAN:
			drawZhadan(c);
			break;
		}
	
	}


	@Override
	protected void update() {
		super.update();
		
		doNormal();				//������Ϸ�������õ���ѭ��
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
		case GAMING_TWOPLAY:
			doTwoPlay();
			break;
		case GAMING_TREETRAIN:
			doThreeTrain();
			break;
		case GAMING_ALLWIN:
			doAllWin();
			break;
		case GAMING_RERUN:
			doRerun();
			break;
		case GAMING_NONE:
			doNone();
			break;
		case GAMING_FOURTRAIN:
			break;
		case DAOJU_YUWANG:
			doYuwang();
			break;
		case DAOJU_YUGOU:
			doYugou();
			break;
		case DAOJU_ZHADAN:
			doZhadan();
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
		c.drawBitmap(img_zhuandeng, 0, img_defeng.getHeight(), paint);
		c.drawBitmap(img_yafeng, 0, img_defeng.getHeight()+img_zhuandeng.getHeight(),
				paint);
		c.drawBitmap(butn_kaishi.getBitmap(), (width-img_kaishi1.getWidth())>>1,
				UIUtil.getPixel(310, VERTICAL), paint);
		c.drawBitmap(butn_chongzhi.getBitmap(),
				UIUtil.getPixel(10, HORIZONTAL), 
				height-img_chongzhi.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(butn_caidan.getBitmap(), width - img_caidan.getWidth()-UIUtil.getPixel(10, HORIZONTAL),
				height-img_chongzhi.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(butn_yuwang.getBitmap(), UIUtil.getPixel(125, HORIZONTAL), 
				height-img_yuwan.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(butn_yugou.getBitmap(), (width - img_yugou.getWidth())>>1, 
				height-img_yugou.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(butn_zhadan.getBitmap(), UIUtil.getPixel(284, HORIZONTAL), 
				height-img_zhadan.getHeight()-UIUtil.getPixel(5, VERTICAL), paint);
		c.drawBitmap(img_red_l, (width - img_red_l.getWidth())>>1, 
				UIUtil.getPixel(400, VERTICAL), paint);
		
		//Ŀ�����
		paint.setColor(Color.YELLOW);
		paint.setTextSize(UIUtil.getPixel(25, VERTICAL));
		c.drawText(targetScore+"", UIUtil.getPixel(312, HORIZONTAL),
				UIUtil.getPixel(195, VERTICAL), paint);

		c.drawBitmap(img_bg_string, UIUtil.getPixel(120, HORIZONTAL),
				UIUtil.getPixel(171, VERTICAL), paint);
		//��ת�ĵ�
		if(bz_isDrawLight)machine.drawLight(c);
		
		// �Ϸֵ���Ӱ
		int k = scoreState - 11;
		for (int j = 0; j < 8; j++) {

			if (k != j)
				c.drawBitmap(img_ya_gray, UIUtil.getPixel(2, HORIZONTAL)+img_ya_gray.getWidth() * j,
						UIUtil.getPixel(558, VERTICAL), paint);
		}
				
		//�Ϸֵķ���
		float j = UIUtil.getPixel(60, HORIZONTAL);
		float m = UIUtil.getPixel(554, VERTICAL);
		float h = 31;
		UIUtil.getNumImage(machine.bar, UIUtil.getPixel(h, HORIZONTAL), m,img_shangfengshuzhis, c,paint);
		UIUtil.getNumImage(machine.qiqi, UIUtil.getPixel(h, HORIZONTAL)+j, m, img_shangfengshuzhis, c,paint);
		UIUtil.getNumImage(machine.star, UIUtil.getPixel(h, HORIZONTAL)+j*2, m,img_shangfengshuzhis, c, paint);
		UIUtil.getNumImage(machine.watermelon, UIUtil.getPixel(30, HORIZONTAL)+j*3, m,img_shangfengshuzhis, c, paint);
		UIUtil.getNumImage(machine.bell, UIUtil.getPixel(h, HORIZONTAL)+j*4, m, img_shangfengshuzhis, c,paint);
		UIUtil.getNumImage(machine.peach, UIUtil.getPixel(h, HORIZONTAL)+j*5, m, img_shangfengshuzhis, c,paint);
		UIUtil.getNumImage(machine.lemon, UIUtil.getPixel(h, HORIZONTAL)+j*6, m, img_shangfengshuzhis, c,paint);
		UIUtil.getNumImage(machine.apple, UIUtil.getPixel(h, HORIZONTAL)+j*7, m, img_shangfengshuzhis, c,paint);
	
		//�ֺܷ͵÷�
		UIUtil.getNumImage(machine.totle, UIUtil.getPixel(116, HORIZONTAL), UIUtil.getPixel(22, VERTICAL), img_nums, c, paint);
		UIUtil.getNumImage(machine.score, UIUtil.getPixel(355, HORIZONTAL), UIUtil.getPixel(22, VERTICAL), img_nums, c, paint);
		
		
	}

	private void drawBefore(Canvas c) {

		
		
	}
	
	private void drawDoing(Canvas c) {
		
		//�����˸��cash_out,luckyͼƬ�任
		float j = UIUtil.getPixel(230, VERTICAL);
		if(redLightStat)c.drawBitmap(img_redLight_gray, (width-img_redLight_gray.getWidth())>>1, 
				UIUtil.getPixel(400, VERTICAL), paint);
		if(img_before_text_state == 1){
			c.drawBitmap(img_lucky, (width-img_lucky.getWidth())>>1, j, paint);
		}else if(img_before_text_state == 2){
			c.drawBitmap(img_cash_out, (width-img_cash_out.getWidth())>>1, j,paint);
		}
		
	}
	
	private void drawAfter(Canvas c) {
		
		//��Ӯ��ͼƬ��ʾ
		float k = UIUtil.getPixel(230, VERTICAL);
		if(isLucky){
			c.drawBitmap(img_luck_cash, (width-img_luck_cash.getWidth())>>1, k, paint);
		}else{
			c.drawBitmap(img_cash_out, (width-img_cash_out.getWidth())>>1, k, paint);
			c.drawBitmap(img_yihan, (width-img_yihan.getWidth())>>1,
					UIUtil.getPixel(100, VERTICAL), paint);
		}
		
	}
	
	private void drawOther(Canvas c) {

		//��ʾ������ϷЧ��������ͼƬ
		c.drawBitmap(img_luck_cash, (width - img_luck_cash.getWidth()) >> 1,
				UIUtil.getPixel(171, VERTICAL), paint);
		if (gamingmoreString == 1)
			c.drawBitmap(img_shuangpengdeng,
					(width - img_shuangpengdeng.getWidth()) >> 1,
					UIUtil.getPixel(171, VERTICAL), paint);
		if (gamingmoreString == 2)
			c.drawBitmap(img_kaihuoche,
					(width - img_kaihuoche.getWidth()) >> 1,
					UIUtil.getPixel(171, VERTICAL), paint);
		if (gamingmoreString == 3)
			c.drawBitmap(img_damanguan,
					(width - img_damanguan.getWidth()) >> 1,
					UIUtil.getPixel(171, VERTICAL), paint);

	}

	private void drawZhadan(Canvas c) {

		
		c.drawBitmap(img_baos[bao_index], UIUtil.getPixel(-50, HORIZONTAL), 
				UIUtil.getPixel(-10, VERTICAL), paint);
		
	}

	private void drawYugou(Canvas c) {
		// TODO Auto-generated method stub
		
	}

	private void drawYuwang(Canvas c) {
		float[] f = new float[3] ;
		f = machine.getNetPoint(f);
		float width = f[0];
		float height = f[1];
		
		c.drawBitmap(img_yuwang, width, height, paint);
	}
	
	private void doNormal(){
		if(isDoingEnd == false)machine.cycle(cycleStep,machine.direction);//��ת��
	}

	private void doBefore(){
		
		if(shangFengState != -1){
			shangFengIndex++;
			if(shangFengIndex > 3)shangFeng(shangFengState);
		}
		
	}
	
	private void doDoing(){
		
		//��Ƶ���˸�����ֵı仯
		if(isDoingEnd == false){
			
			redLightStat = (redLightStat == true)?false:true;
		}
		
		if(DoingCount < 100)DoingCount++;else DoingCount = 1;
		if(DoingCount%DoingTime == 0){
			
			if(img_before_text_state == 1){
				img_before_text_state = 2;
			}else if(img_before_text_state == 2){
			    img_before_text_state = 1;
			}
			
		}
		
		//��Ϸ�е�Ч����һ֡һ֡��ʾ
		switch (gamingDoingLevel) {
		case 1:
			reduceTime(100,500,(byte)2);
			break;
			
		case 2:
			GameView.setFrameTime(80);
			if (gamingTimeDelay == 0) {
				gamingTimeDelay = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - gamingTimeDelay) <= 600) { 
				if(isGamingLevel2StateRun == false){
					
					machine.gamingLevel2();//����״
					isGamingLevel2StateRun = true;
				}
				

			} else {
				gamingTimeDelay = 0;
				gamingDoingLevel = 3;
			}
			
			break;
			
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			reduceRect((byte)(gamingDoingLevel-3),(byte)(gamingDoingLevel+1));
			break;
		case 10:	
			DoingTime = 2;
			reduceTime(70,500,(byte)(gamingDoingLevel+1));
			break;
		case 11:
			GameView.setFrameTime(70);
			if(machine.result == machine.d2){
				gamingDoingLevel = 0;
				isDoingEnd = true;
				switch (machine.result) {
				case 9:
					gamingMoreState = 9;
					switch (machine.getGamingProbability()) {
					case  GAMING_TWOPLAY:
						gameState = GAMING_TWOPLAY;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;
						
					case  GAMING_TREETRAIN:
						gameState = GAMING_TREETRAIN;
						spp.play("train");
						TipHelper.Vibrate(context, tipTime);
						break;
						
					case  GAMING_ALLWIN:
						gameState = GAMING_ALLWIN;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;

					case  GAMING_RERUN:    //����
						gameState = GAMING_RERUN;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;
					case  GAMING_NONE:	//�ص�gamingbefore
						gameState = GAMING_NONE;
						break;
					case  GAMING_FOURTRAIN:    //�ĵƻ�
						gameState = GAMING_TREETRAIN;
						spp.play("train");
						TipHelper.Vibrate(context, tipTime);
						break;
					
					}
					break;
				case 21:
					gamingMoreState = 21;
					switch (machine.getGamingProbability()) {
					case  GAMING_TWOPLAY:
						gameState = GAMING_TWOPLAY;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;
						
					case  GAMING_TREETRAIN:
						gameState = GAMING_TREETRAIN;
						spp.play("train");
						TipHelper.Vibrate(context, tipTime);
						break;
						
					case  GAMING_ALLWIN:
						gameState = GAMING_ALLWIN;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;
					case  GAMING_RERUN:    //����
						gameState = GAMING_RERUN;
						spp.play("win");
						TipHelper.Vibrate(context, tipTime);
						break;
					case  GAMING_NONE:	//�ص�gamingbefore
						gameState = GAMING_NONE;
						break;
					case  GAMING_FOURTRAIN:    //�ĵƻ�
						gameState = GAMING_TREETRAIN;
						spp.play("train");
						TipHelper.Vibrate(context, tipTime);
						break;
					}
					break;

				default:
					isLucky = machine.handleResult(machine.result);
					gameState = AFTER;
					break;
				}
				img_before_text_state = 0;
			}
			
		}
		
	}

	private void doAfter(){
		if(!afterAudioState){
			spp.stop(goId);
			if(isLucky){
				spp.play("win");
				TipHelper.Vibrate(context, 3500);
			}
			else spp.play("fail");
			afterAudioState = true;
		}
		GameView.setFrameTime(100);
		if(d2 == 0)d2 = machine.d2;
		if(gamingTimeDelay == 0){
			gamingTimeDelay = System.currentTimeMillis();
		}
		if((System.currentTimeMillis() - gamingTimeDelay) <= 3500 ){  
			
			machine.d2 = (machine.d2 == d2)?-1:d2;
			
		}else {
			afterAudioState = false;
			gamingTimeDelay = 0;
			if(isLucky){
				
				GameView.setGameScreen(new LuckyScreen());
			}
			else {
				if (machine.isGameOver()) {
					//GameView.setGameScreen(new GameOverScreen());
					context.getHandler().obtainMessage(Constants.FAIL_DIALOG).sendToTarget();
				}else if (machine.totle >= targetScore) {
					context.getHandler().obtainMessage(Constants.PASS_DIALOG).sendToTarget();
				}else {
					
					initGaming();
					gameState = BEFORE;
					
				}

			}
		}
		
	}
	
	private void doTwoPlay() {
		
		//˫����Ч��
		GameView.setFrameTime(100);
		switch (gamingDoingLevel) {
		case 0:
			if (d2 == 0)
				d2 = machine.d2;
			if (gamingTimeDelay == 0) {
				gamingTimeDelay = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - gamingTimeDelay) <= 2000) {

				gamingmoreString = 1;
				machine.d2 = (machine.d2 == d2) ? -1 : d2;

			} else {
				gamingmoreString = 0;
				gamingTimeDelay = 0;
				gamingDoingLevel = 1;
				d2 = -1;
			}

		case 1:
			if (alertString == 0)
				alertString = random.nextInt(2000) + 2000;// ������guess��Ϸ�е�״̬�����������������ı������˺������ӻ���������С���ֻ�����˸���
			if (gamingTimeDelay == 0) {
				gamingTimeDelay = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - gamingTimeDelay) <= alertString) {
				GameView.setFrameTime(80);
				machine.twoPlay(gamingMoreState);

			} else {
				GameView.setFrameTime(200);
				gamingTimeDelay = 0;
				gamingDoingLevel = 2;
				spp.play("win");
				TipHelper.Vibrate(context, tipTime);
			}

			break;

		case 2:

			if (a1 == 0)
				a1 = machine.a1;
			if (a2 == 0)
				a2 = machine.a2;
			if (gamingTimeDelay == 0) {
				gamingTimeDelay = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - gamingTimeDelay) <= 2000) {

				machine.a1 = (machine.a1 == a1) ? -1 : a1;
				machine.a2 = (machine.a2 == a2) ? -1 : a2;

			} else {
				gamingTimeDelay = 0;
				gamingDoingLevel = 3;
			}
			break;

		case 3:

			boolean i = machine.handleResult(a1);
			boolean j = machine.handleResult(a2);

			if (i || j) {
				GameView.setGameScreen(new LuckyScreen());

			} else {

				if (machine.isGameOver()) {
					//GameView.setGameScreen(new GameOverScreen());
					context.getHandler().obtainMessage(Constants.FAIL_DIALOG).sendToTarget();
				} else if (machine.totle >= targetScore) {
					context.getHandler().obtainMessage(Constants.PASS_DIALOG).sendToTarget();
				}else {
					initGaming();
				}

			}

			break;
		}
	}
	
	private void doThreeTrain(){
		
		
		//����Ч��
		GameView.setFrameTime(100);
		switch (gamingDoingLevel) {
		case 0:
			
			if(d2 == 0)d2 = machine.d2;
			if(gamingTimeDelay == 0){
				gamingTimeDelay = System.currentTimeMillis();
			}
			if((System.currentTimeMillis() - gamingTimeDelay) <= 2000){  
				
				gamingmoreString = 2;
				machine.d2 = (machine.d2 == d2)?-1:d2;
				
			}else {
				gamingmoreString = 0;
				gamingTimeDelay = 0;
				gamingDoingLevel = 1;
				d2 = -1;
			}
			break;
			
		case 1:
			
			if(alertString == 0)alertString = random.nextInt(2000)+3000;//������guess��Ϸ�е�״̬�����������������ı������˺������ӻ���������С���ֻ�����˸���
			if(gamingTimeDelay == 0){
				gamingTimeDelay = System.currentTimeMillis();
			}
			if((System.currentTimeMillis() - gamingTimeDelay) <= alertString ){  
				GameView.setFrameTime(80);
				machine.treeTrain(gamingMoreState);
			}else {
				GameView.setFrameTime(200);
				gamingTimeDelay = 0;
				gamingDoingLevel = 2;
			}
			
			break;
			
		case 2:
			if(a1 == 0)a1 = machine.a1;
			if(a2 == 0)a2 = machine.a2;
			if(d1 == 0)d1 = machine.d1;
			if(gamingTimeDelay == 0){
				gamingTimeDelay = System.currentTimeMillis();
			}
			if((System.currentTimeMillis() - gamingTimeDelay) <= 2000 ){  
				
				machine.a1 = (machine.a1 == a1)?-1:a1;
				machine.a2 = (machine.a2 == a2)?-1:a2;
				machine.d1 = (machine.d1 == d1)?-1:d1;
				
			}else {
				gamingTimeDelay = 0;
				gamingDoingLevel = 3;
			}
			break;

		case 3:
			
			doHuocheResult(a1,a2,d1);
			break;
		}
	};
	

	private void doAllWin(){
		
		//������
		GameView.setFrameTime(100);
		switch (gamingDoingLevel) {
		case 0:
			if (d2 == 0)
				d2 = machine.d2;
			if (gamingTimeDelay == 0) {
				gamingTimeDelay = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - gamingTimeDelay) <= 4000) {

				gamingmoreString = 3;
				machine.d2 = (machine.d2 == d2) ? -1 : d2;

			} else {
				gamingmoreString = 0;
				gamingTimeDelay = 0;
				gamingDoingLevel = 1;
				d2 = -1;
			}

			break;

		case 1:
			machine.allWin();
			GameView.setGameScreen(new LuckyScreen());
			break;
		}

		
	};
	private void doRerun(){
		
		//����
		GameView.setFrameTime(100);
		if (d2 == 0)
			d2 = machine.d2;
		if (gamingTimeDelay == 0) {
			gamingTimeDelay = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - gamingTimeDelay) <= 3000) {

			img_before_text_state = 1;
			machine.d2 = (machine.d2 == d2) ? -1 : d2;

		} else {
			gamingTimeDelay = 0;
			initGaming();
			img_before_text_state = 1;
			machine.getResult();
			gameState = DOING;
			spp.play("go",4);
		}
		
	};
	private void doNone(){
		
		//ʲô��û��
		GameView.setFrameTime(100);
		if (d2 == 0)
			d2 = machine.d2;
		if (gamingTimeDelay == 0) {
			gamingTimeDelay = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - gamingTimeDelay) <= 3000) {

			img_before_text_state = 2;
			machine.d2 = (machine.d2 == d2) ? -1 : d2;

		} else {
			gamingTimeDelay = 0;
			if (machine.isGameOver()) {
				context.getHandler().obtainMessage(Constants.FAIL_DIALOG).sendToTarget();
			}else if (machine.totle >= targetScore) {
				context.getHandler().obtainMessage(Constants.PASS_DIALOG).sendToTarget();
			} else {
				initGaming();
				gameState = BEFORE;
			}
		}
		
	};

	private void doZhadan() {
		
		bz_isDrawLight = false;
		if(bao_index < 7)bao_index++;
		if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
		if(System.currentTimeMillis() - gamingTimeDelay >= 1000){
			gamingTimeDelay = 0;
			bz_isDrawLight = true;
			gameState = BEFORE;
			bao_index = 0;
			
			machine.initBe1();
			machine.allWin();
			machine.initFruit();
			if (machine.totle >= targetScore) {
				context.getHandler().obtainMessage(Constants.PASS_DIALOG)
						.sendToTarget();
			}
			machine.oldTotle =machine.totle;
			machine.init8point();	
		}
		
	}

	private void doYugou() {
		
		isDoingEnd = true;
		switch (yugouState) {
		case 10:
			machine.allPointBeGray();
			if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
			if(System.currentTimeMillis() - gamingTimeDelay >=0){
				gamingTimeDelay = 0;
				yugouState = 20;
				
			}
			
			break;
			
		case 20:
			
			if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
			if(System.currentTimeMillis() - gamingTimeDelay >= 100){
				gamingTimeDelay = 0;
				machine.a1 = random.nextInt(24);
				spp.play("6");
				yugouState = 30;
			}
			break;
			
		case 30:
			
			if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
			if(System.currentTimeMillis() - gamingTimeDelay >= 200){
				gamingTimeDelay = 0;
				while (true) {
					machine.b1 = random.nextInt(24);
					if(machine.b1 != machine.a1 )break;
				}
				spp.play("6");
				yugouState = 40;
			}
			
			break;
			
		case 40:
			
			if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
			if(System.currentTimeMillis() - gamingTimeDelay >= 200){
				gamingTimeDelay = 0;
				while (true) {
					machine.c1 = random.nextInt(24);
					if(machine.c1 != machine.a1 && machine.c1 != machine.b1)break;
				}
				spp.play("6");
				yugouState = 50;
			}
			
			break;
			
		case 50:
			
			if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
			if(System.currentTimeMillis() - gamingTimeDelay >= 200){
				gamingTimeDelay = 0;
				while (true) {
					machine.d1 = random.nextInt(24);
					if(machine.d1 != machine.a1 && machine.d1 != machine.b1 && machine.d1 != machine.c1)break;
				}
				spp.play("6");
				yugouState = 60;
			}
			
			break;
			
		case 60:
			if (gamingTimeDelay == 0)
				gamingTimeDelay = System.currentTimeMillis();
			if (System.currentTimeMillis() - gamingTimeDelay >= 500) {
				gamingTimeDelay = 0;
				yugouState = 10;
				gameState = BEFORE;
				isDoingEnd = false;

				machine.initBe1();
				machine.handleResult(machine.a1);
				machine.handleResult(machine.c1);
				machine.handleResult(machine.b1);
				machine.handleResult(machine.d1);
				machine.initFruit();
				if (machine.totle >= targetScore) {
					context.getHandler().obtainMessage(Constants.PASS_DIALOG)
							.sendToTarget();
				}
				machine.oldTotle =machine.totle;
				machine.init8point();
			}
			break;
		}
		
	}

	private void doYuwang() {
		
		if(gamingTimeDelay == 0)gamingTimeDelay = System.currentTimeMillis();
		if(System.currentTimeMillis() - gamingTimeDelay >= 2000){
			gamingTimeDelay = 0;
			gameState = BEFORE;
			isDoingEnd = false;
			
			machine.initBe1();
			machine.handleResult(machine.a1);
			machine.handleResult(machine.a2);
			machine.handleResult(machine.b1);
			machine.initFruit();
			if (machine.totle >= targetScore) {
				context.getHandler().obtainMessage(Constants.PASS_DIALOG).sendToTarget();
			}
			machine.oldTotle =machine.totle;
			machine.init8point();
		}
		
	}
	
	
	private void handleBefore() {
		
	
		//��ʼ
		if(HandleTouch.isTouchBitmap(img_kaishi1,
				(width-img_kaishi1.getWidth())>>1,
				UIUtil.getPixel(310, VERTICAL))){
			butn_chongzhi.bePressed();
			GameView.rePaint();
			butn_chongzhi.beNormal();
			GameView.rePaint();
			if (machine.oldTotle != machine.totle) {

				gameState = DOING;
				goId = spp.play("go",4);
				gamingDoingLevel = 1;
				machine.getResult();
				img_before_text_state = 1;

			} else {

				int i = 0;
				if ((i = machine.addScoreDef()) > 0 && (machine.totle - i) >= 0) {

					machine.totle -= i;
					gameState = DOING;
					goId = spp.play("go",4);
					gamingDoingLevel = 1;
					machine.getResult();
					img_before_text_state = 1;

				}

			}

		}
		
		//����
		if(HandleTouch.isTouchBitmap(img_chongzhi,UIUtil.getPixel(10, HORIZONTAL), 
				height-img_chongzhi.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_chongzhi.bePressed();
			GameView.rePaint();
			butn_chongzhi.beNormal();
			GameView.rePaint();
				if(machine.oldTotle != machine.totle){
							
					int i = 0;
					if((i = machine.addScoreDef()) > 0 && (machine.totle-i) >= 0){
								
						machine.totle += i;
						machine.reset();
						
					}	
					
					
				}else {
						
					machine.initFruit();
					
				}
		}
		
		//�˵�
		if(HandleTouch.isTouchBitmap(img_caidan,width - img_caidan.getWidth()-UIUtil.getPixel(10, HORIZONTAL),
				height-img_chongzhi.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_caidan.bePressed();
			GameView.rePaint();
			butn_caidan.beNormal();
			context.getHandler().obtainMessage(Constants.GAME_MENU).sendToTarget();
		}
		
		for (int j = 0; j < 8; j++) {
			
			if(HandleTouch.isTouchBitmap(img_ya_gray, UIUtil.getPixel(2, HORIZONTAL)+img_ya_gray.getWidth() * j,
					UIUtil.getPixel(558, VERTICAL))){
				
				shangFengState = j;
				shangFeng(j);
				spp.play("anjian");
				TipHelper.Vibrate(context, 100);
			}
			
		}
		
		for (int j = 0; j < 8; j++) {
			
			if(HandleTouch.isTouchBitmap(HandleTouch.UP,img_ya_gray, UIUtil.getPixel(2, HORIZONTAL)+img_ya_gray.getWidth() * j,
					UIUtil.getPixel(558, VERTICAL))){
				
				shangFengState = -1;
				shangFengIndex = 0;
			}
			
		}	
		
		//����
		if(HandleTouch.isTouchBitmap(img_yuwan2, 
				UIUtil.getPixel(125, HORIZONTAL), 
				height-img_yuwan.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_yuwang.bePressed();
			GameView.rePaint();
			butn_yuwang.beNormal();
			GameView.rePaint();
			
			spp.play("yuwang");
			isDoingEnd = true;
			machine.doYuwang();
			gameState = DAOJU_YUWANG;
			return;
		}
		
		//�㹳
		if(HandleTouch.isTouchBitmap(img_yuwan2, 
				(width - img_yugou.getWidth())>>1, 
				height-img_yugou.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_yuwang.bePressed();
			GameView.rePaint();
			butn_yuwang.beNormal();
			GameView.rePaint();
			gameState = DAOJU_YUGOU;
			return;
		}
		
		//ը��
		if(HandleTouch.isTouchBitmap(img_yuwan2, 
				UIUtil.getPixel(284, HORIZONTAL), 
				height-img_zhadan.getHeight()-UIUtil.getPixel(5, VERTICAL))){
			butn_yuwang.bePressed();
			GameView.rePaint();
			butn_yuwang.beNormal();
			GameView.rePaint();
			gameState = DAOJU_ZHADAN;
			spp.play("baozha");
			return;
		}
		
	}

	private void handleDoing() {
		
	}
	
	private void handleAfter() {
		
	}
	
	private void reduceTime(int i,int time ,byte nextLevel){
		
		if (gamingTimeDelay == 0) {
			gamingTimeDelay = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - gamingTimeDelay) <= time) { 
			GameView.setFrameTime(i); 
		} else {
			gamingTimeDelay = 0;
			gamingDoingLevel = nextLevel;
		}
		
	}
	
	private void reduceRect(byte i,byte nextLevel){
		
		if (gamingTimeDelay == 0) {
			gamingTimeDelay = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - gamingTimeDelay) <= 300) {

			machine.reduceRect(i);

		} else {
			gamingTimeDelay = 0;
			gamingDoingLevel = nextLevel;
		}
		
	}
	
	private void shangFeng(int j){
		scoreState = (byte) (j+11);
		
		if(machine.oldTotle == machine.totle){
			
			if(machine.addScoreDef() != 0){
				machine.initFruit();
			}
		}
		machine.addScore(scoreState,false);
	}
	
	public static Machine getMachine() {
		return machine;
	}
	
	public static void setMachine(Machine machine) {
		MainScreen.machine = machine;
	}
	
	private void initGaming(){
		//��ʼ��
		GameView.setFrameTime(200);			    //�߳�����ʱ�䣬Ҳ����ˢ���ļ���ʱ��
		gameState = BEFORE;        				//gaming��Ϸ��״̬
		gamingDoingLevel = 1;					//gaming��Ϸ�Ķ���֡״̬
		img_before_text_state = 0;				//gaming doingʱ�ı��͵Ƶ���˸����
		isGamingLevel2StateRun = false;	    	//doing�п��Ƴ���״���ݱ仯ֻ����һ��
		isDoingEnd = false;				    	//�����Ƿ�����������˾Ͳ����ټ�a1234..
		DoingCount = 1;							//�����м��ı��ͺ����˸��Ƶ�ʣ�ʹ��������Ϸˢ��������
		DoingTime = 5;							//��doingcount���ʹ�ã���������ʱ�䣬��Ϸ�߳����м��Σ�����˸һ��
		isLucky = false;				    	//gaming��Ϸʱ���ʤ
		d2 = a1 = a2 = d1 = 0;					//��d2��ֵ��ֵ����������d2���Ժ�-1���棬�Դ˿���gaming_afterʱ�Ƶ���˸
		gamingMoreState = 0;					//����״̬�µ�״̬���棬�����ж���9���ǣ�11
		gamingmoreString = 0;					//����״̬����ʾ���ı�������˫���ڣ�����ɶ��Ŷ�ף�1-˫���ڣ�2-���𳵣�3-�����ᣩ
		redLightStat = false;
		machine.score = 0;
		machine.winScore = 0;
		machine.init();
		
	}
	
	public int getTargetScore() {
		return targetScore;
	}
	
	public static MainScreen getInstance() {
		return instance;
	}
	
	private void doHuocheResult(int a1,int a2,int d1) {
		boolean i = machine.handleResult(a1);
		boolean j = machine.handleResult(a2);
		boolean k = machine.handleResult(d1);
		
		if(i  || j || k){
			spp.play("win");
			TipHelper.Vibrate(context, tipTime);
			GameView.setGameScreen(new LuckyScreen());
			
		}else {
			
			if (machine.isGameOver()) {
				//GameView.setGameScreen(new GameOverScreen());
				context.getHandler().obtainMessage(Constants.FAIL_DIALOG).sendToTarget();
			}else if (machine.totle >= targetScore) {
				context.getHandler().obtainMessage(Constants.PASS_DIALOG).sendToTarget();
			}else {
				
				initGaming();
				gameState = BEFORE;
				
			}
			
		}
		
	}
	
}
