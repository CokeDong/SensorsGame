package com.example.util;


import java.lang.*;
import android.content.res.Resources;
import android.graphics.Bitmap;

enum Sensor_Delay{
	DELAY_FASTEST ,
	DELAY_GAME,
	SENSOR_DELAY_UI,
	DELAY_NORMAL
	}

public class Constant {
	
	//当前的关数
	public static int LEVEL=0;
	
	//屏幕自适应
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static ScreenScaleResult screenScaleResult;
	
	
	//方法1：传感器延长时间判定（不够通用）
	public static int SENSOR_DELAY_COUNTER=0;
	
	public static final long TIME_SENSOR_DELAY=2000;//单位为ms
	public static final Sensor_Delay sensor_delay_type1=Sensor_Delay.DELAY_NORMAL;
	public static final long SENSOR_DELAY_COUNTER1=Get_Sensor_Delay_Counter(sensor_delay_type1);
	
	public static long Get_Sensor_Delay_Counter(Sensor_Delay sensor_delay_type)
	{
		long t=0;
		switch(sensor_delay_type){
		case  DELAY_NORMAL:
			t=(long)TIME_SENSOR_DELAY*1000/200000;
			break;
		case  SENSOR_DELAY_UI :
			t=(long)TIME_SENSOR_DELAY*1000/66667;
			break;
		case  DELAY_GAME :
			t=(long)TIME_SENSOR_DELAY*1000/20000;
			break;
		case  DELAY_FASTEST  :
			//no meaning t=(long)TIME_SENSOR_DELAY*1000/0;
			break;
		}
		return t;
	}
	
	
	//方法2：使用Thread，在线程结束后判断（适用范围更广）
	public static boolean ALL_LEVEL_DELAY_FLAG=false;
	public static final long ALL_LEVEL_TIME_DELAY=1000;//单位为ms
	
	//状态变量
	public static boolean LOAD_FINISH=false;//图片加载完毕标志
	public static boolean FROM_START=true;//是否需要重新播放音乐
	public static boolean YINYUE_OPEN=true;//背景音乐
	
	
	public static boolean IF_CLOSING_SENSOR_LISTENING=false;
	
	public static boolean WELCOME_VIEW_ONCE=false;
	public static boolean YINYUE1=true;//true为播放背景音，否则播放level-1小冰背景音
	public static boolean LEVELONE_NEAR_FLAG=false;//level-1靠近标志
	public static boolean LEVELTWO_UPSIDEDOWN_FLAG=false;//level-2倒立近标志
	public static boolean LEVELTHREE_DARK_FLAG=false;//level-3光强变弱标志
	//public static boolean LEVELTHREE_STOP_SENDING_FLAG=false;//level-3是否停止传感器监听标志
	public static boolean LEVELFOUR_SHAKE_FLAG=false;//level-4摇晃标志
	public static boolean LEVELFIVE_LAMP_FLAG=false;//level-5点亮标志 用于绘图
	public static boolean LEVELFIVE_LAMP_FLAG2=false;//level-5点亮标志 用于延时逻辑判断
	
	//游戏常量
	public static final double GRAVITY=10;//level-2重力参考量
	public static final double THETA=85;//level-2 倾斜角
	public static final double GRAVITYPRODUCT=Math.sin(Math.toRadians(THETA));//level-2重力投影阈值
	public static final float LIGHT_INTENSITY_THRESHOLD=5; //level-3光强阈值
	public static final float SHAKE_ACCELEROMETER_THRESHOLD=19f; //level-4加速度阈值
	public static final float DELTA_PIXEL=20; //level-5像素偏移
	
	
	
	//图片 Bitmap[]
	public static  Bitmap[] TP_ARRAY;		//	general pic数组
	public static  Bitmap[] NEXTVIEW_ARRAY;	//	缩放前的NEXTVIEW提示语
	public static  Bitmap[] GAMEVIEW_NUM_ARRAY;		//	缩放前的GAMEVIEW题号 
	public static  Bitmap[] GAMEVIEW_QUESTION_ARRAY;//	缩放前的GAMEVIEW提示语 
	

	
	//图片文件名 String[]
	
	//general pic
	public static String[] TP_ARRAY_NAME=
	{
		"TP0.png",//	0	 背景1
		"TP1.png",//    1 	名字
		"TP2.png",//    2 	play按钮
		"TP3.png",//    3 	声音开
		"TP4.png",//    4 	声音关
		"TP5.png",//    5 	唐老鸭
		"TP6.png",//	6 	背景2
		"TP7.png",//  	7 	黑板 
		"TP8.png",//    8 	comeon图片
		"TP9.png",//	9 	lock图片
		"TP10.png",//	10	返回按钮
		"TP11.png",//   	11	level 1
		"TP12.png",//	12	小冰
		"TP13.png",//	13	level 2
		"TP14.png",//	14	老奶奶
		"TP15.png",//	15	level 3
		"TP16.png",//	16	白天森林
		"TP17.png",//	17	黑夜森林
		"TP18.png",//	18	猫头鹰
		"TP19.png",//	19	level 4
		"TP20.png",//	20	可乐1
		"TP21.png",//	21	可乐2
		"TP22.png",//	22	level 5
		"TP23.png",//	23	灯泡1
		"TP24.png",//	24	灯泡2
		"TP25.png",//	25	下一关
		"TP26.png" //	26	选择关卡
	};
	//NEXTVIEW提示语
	public static String[] NEXTVIEW_ARRAY_NAME=
	{
		"11.png",
		"22.png",
		"33.png",
		"44.png",
		"55.png"
	};
	//GAMEVIEW题号
	public static String[] GAMEVIEW_NUM_ARRAY_NAME=
	{
		"Q1.png",
		"Q2.png",
		"Q3.png",
		"Q4.png",
		"Q5.png"
	};
	//GAMEVIEW提示语 
	public static String[] GAMEVIEW_QUESTION_ARRAY_NAME=
	{
		"1.png",
		"2.png",
		"3.png",
		"4.png",
		"5.png"
	};

	
	
	//位置  float[][]
	
	//general pic位置，下标和[] TP_ARRAY_NAME数组一一对应
	public static float[][] xyoffset=
	{
		{0,0},//0
		{0,180},//1
		{180,360},//2
		{340,700},//3
		{340,700},//4
		{0,550},//5
		{0,0},//6
		{40,220},//7
		{0,0},//8
		{},//9
		{260,480},//10
		{50,220},//11
		{160,240},//12
		{260,220},//13
		{90,240},//14
		{50,350},//15
		{90,240},//16
		{90,240},//17
		{180,330},//18
		{260,350},//19返回按钮
		{90,250},//20
		{300,250},//21
		{50,480},//22
		{0,200},//23
		{0,200},//24
		{300,500},//25
		{180,500}//26
	};
	
	
	//触控位置坐标集合
	public static float[][] xyoffsetEvent;
	

	
	//起始坐标和缩放比的计算
	public static boolean isscale=false;//为了保证在应用程序没有销毁前不需重新计算
	public static void scaleCL()
	{
		
		if(isscale) return;
		 screenScaleResult=ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
		 
		 //计算实际触点位置坐标
		 xyoffsetEvent=new float[xyoffset.length][2];
		 for(int i=0;i<xyoffset.length;i++)
		 {
			
			 if(xyoffset[i].length>0)
			 {
				 xyoffsetEvent[i][0]=(xyoffset[i][0]+screenScaleResult.lucX)*screenScaleResult.ratio;
				 xyoffsetEvent[i][1]=(xyoffset[i][1]+screenScaleResult.lucY)*screenScaleResult.ratio;
			 }
			 
		 }
		 isscale=true;
	}
	

	
	//初始化数据库
	public static void initDB()//注意要保证数据库退出后再打开仍然有效
	{
		if(DBUtil.getLock(0)==0)//创建数据库，getLock(0)表示第1关，0表示未解锁
		{
			DBUtil.insert(0,1,0);
		}
		
		int  i=DBUtil.getYinyue();
		//0:初始状态  1：open 2：close
		if(i==0)
		{
			YINYUE_OPEN=true;
			DBUtil.insertSetting(1);//初始默认打开音乐
			//注意setting退出前更新：see-ChickenRun-line284
		}
		if(i==1)
		{
			YINYUE_OPEN=true;
		}
		if(i==2)
		{
			YINYUE_OPEN=false;
		}
	}
	
	
	//初始化得到图片数组
	public static void initBitmap(Resources resources) {
		
		TP_ARRAY=null;//初始化先置空
		TP_ARRAY=new Bitmap[TP_ARRAY_NAME.length];
		for(int i=0;i<TP_ARRAY_NAME.length;i++)
		{
			TP_ARRAY[i]=PicLoadUtil.loadBM(resources, TP_ARRAY_NAME[i]);
		}
		
		NEXTVIEW_ARRAY=null;//初始化先置空
		NEXTVIEW_ARRAY=new Bitmap[NEXTVIEW_ARRAY_NAME.length];
		for(int i=0;i<NEXTVIEW_ARRAY_NAME.length;i++)
		{
			NEXTVIEW_ARRAY[i]=PicLoadUtil.loadBM(resources, NEXTVIEW_ARRAY_NAME[i]);
		}
		
		GAMEVIEW_NUM_ARRAY=null;//初始化先置空
		GAMEVIEW_NUM_ARRAY=new Bitmap[GAMEVIEW_NUM_ARRAY_NAME.length];
		for(int i=0;i<GAMEVIEW_NUM_ARRAY_NAME.length;i++)
		{
			GAMEVIEW_NUM_ARRAY[i]=PicLoadUtil.loadBM(resources, GAMEVIEW_NUM_ARRAY_NAME[i]);
		}
		
		GAMEVIEW_QUESTION_ARRAY=null;//初始化先置空
		GAMEVIEW_QUESTION_ARRAY=new Bitmap[GAMEVIEW_QUESTION_ARRAY_NAME.length];
		for(int i=0;i<GAMEVIEW_QUESTION_ARRAY_NAME.length;i++)
		{
			GAMEVIEW_QUESTION_ARRAY[i]=PicLoadUtil.loadBM(resources, GAMEVIEW_QUESTION_ARRAY_NAME[i]);
		}
	}


}