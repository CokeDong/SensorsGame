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
	
	//��ǰ�Ĺ���
	public static int LEVEL=0;
	
	//��Ļ����Ӧ
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static ScreenScaleResult screenScaleResult;
	
	
	//����1���������ӳ�ʱ���ж�������ͨ�ã�
	public static int SENSOR_DELAY_COUNTER=0;
	
	public static final long TIME_SENSOR_DELAY=2000;//��λΪms
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
	
	
	//����2��ʹ��Thread�����߳̽������жϣ����÷�Χ���㣩
	public static boolean ALL_LEVEL_DELAY_FLAG=false;
	public static final long ALL_LEVEL_TIME_DELAY=1000;//��λΪms
	
	//״̬����
	public static boolean LOAD_FINISH=false;//ͼƬ������ϱ�־
	public static boolean FROM_START=true;//�Ƿ���Ҫ���²�������
	public static boolean YINYUE_OPEN=true;//��������
	
	
	public static boolean IF_CLOSING_SENSOR_LISTENING=false;
	
	public static boolean WELCOME_VIEW_ONCE=false;
	public static boolean YINYUE1=true;//trueΪ���ű����������򲥷�level-1С��������
	public static boolean LEVELONE_NEAR_FLAG=false;//level-1������־
	public static boolean LEVELTWO_UPSIDEDOWN_FLAG=false;//level-2��������־
	public static boolean LEVELTHREE_DARK_FLAG=false;//level-3��ǿ������־
	//public static boolean LEVELTHREE_STOP_SENDING_FLAG=false;//level-3�Ƿ�ֹͣ������������־
	public static boolean LEVELFOUR_SHAKE_FLAG=false;//level-4ҡ�α�־
	public static boolean LEVELFIVE_LAMP_FLAG=false;//level-5������־ ���ڻ�ͼ
	public static boolean LEVELFIVE_LAMP_FLAG2=false;//level-5������־ ������ʱ�߼��ж�
	
	//��Ϸ����
	public static final double GRAVITY=10;//level-2�����ο���
	public static final double THETA=85;//level-2 ��б��
	public static final double GRAVITYPRODUCT=Math.sin(Math.toRadians(THETA));//level-2����ͶӰ��ֵ
	public static final float LIGHT_INTENSITY_THRESHOLD=5; //level-3��ǿ��ֵ
	public static final float SHAKE_ACCELEROMETER_THRESHOLD=19f; //level-4���ٶ���ֵ
	public static final float DELTA_PIXEL=20; //level-5����ƫ��
	
	
	
	//ͼƬ Bitmap[]
	public static  Bitmap[] TP_ARRAY;		//	general pic����
	public static  Bitmap[] NEXTVIEW_ARRAY;	//	����ǰ��NEXTVIEW��ʾ��
	public static  Bitmap[] GAMEVIEW_NUM_ARRAY;		//	����ǰ��GAMEVIEW��� 
	public static  Bitmap[] GAMEVIEW_QUESTION_ARRAY;//	����ǰ��GAMEVIEW��ʾ�� 
	

	
	//ͼƬ�ļ��� String[]
	
	//general pic
	public static String[] TP_ARRAY_NAME=
	{
		"TP0.png",//	0	 ����1
		"TP1.png",//    1 	����
		"TP2.png",//    2 	play��ť
		"TP3.png",//    3 	������
		"TP4.png",//    4 	������
		"TP5.png",//    5 	����Ѽ
		"TP6.png",//	6 	����2
		"TP7.png",//  	7 	�ڰ� 
		"TP8.png",//    8 	comeonͼƬ
		"TP9.png",//	9 	lockͼƬ
		"TP10.png",//	10	���ذ�ť
		"TP11.png",//   	11	level 1
		"TP12.png",//	12	С��
		"TP13.png",//	13	level 2
		"TP14.png",//	14	������
		"TP15.png",//	15	level 3
		"TP16.png",//	16	����ɭ��
		"TP17.png",//	17	��ҹɭ��
		"TP18.png",//	18	èͷӥ
		"TP19.png",//	19	level 4
		"TP20.png",//	20	����1
		"TP21.png",//	21	����2
		"TP22.png",//	22	level 5
		"TP23.png",//	23	����1
		"TP24.png",//	24	����2
		"TP25.png",//	25	��һ��
		"TP26.png" //	26	ѡ��ؿ�
	};
	//NEXTVIEW��ʾ��
	public static String[] NEXTVIEW_ARRAY_NAME=
	{
		"11.png",
		"22.png",
		"33.png",
		"44.png",
		"55.png"
	};
	//GAMEVIEW���
	public static String[] GAMEVIEW_NUM_ARRAY_NAME=
	{
		"Q1.png",
		"Q2.png",
		"Q3.png",
		"Q4.png",
		"Q5.png"
	};
	//GAMEVIEW��ʾ�� 
	public static String[] GAMEVIEW_QUESTION_ARRAY_NAME=
	{
		"1.png",
		"2.png",
		"3.png",
		"4.png",
		"5.png"
	};

	
	
	//λ��  float[][]
	
	//general picλ�ã��±��[] TP_ARRAY_NAME����һһ��Ӧ
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
		{260,350},//19���ذ�ť
		{90,250},//20
		{300,250},//21
		{50,480},//22
		{0,200},//23
		{0,200},//24
		{300,500},//25
		{180,500}//26
	};
	
	
	//����λ�����꼯��
	public static float[][] xyoffsetEvent;
	

	
	//��ʼ��������űȵļ���
	public static boolean isscale=false;//Ϊ�˱�֤��Ӧ�ó���û������ǰ�������¼���
	public static void scaleCL()
	{
		
		if(isscale) return;
		 screenScaleResult=ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
		 
		 //����ʵ�ʴ���λ������
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
	

	
	//��ʼ�����ݿ�
	public static void initDB()//ע��Ҫ��֤���ݿ��˳����ٴ���Ȼ��Ч
	{
		if(DBUtil.getLock(0)==0)//�������ݿ⣬getLock(0)��ʾ��1�أ�0��ʾδ����
		{
			DBUtil.insert(0,1,0);
		}
		
		int  i=DBUtil.getYinyue();
		//0:��ʼ״̬  1��open 2��close
		if(i==0)
		{
			YINYUE_OPEN=true;
			DBUtil.insertSetting(1);//��ʼĬ�ϴ�����
			//ע��setting�˳�ǰ���£�see-ChickenRun-line284
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
	
	
	//��ʼ���õ�ͼƬ����
	public static void initBitmap(Resources resources) {
		
		TP_ARRAY=null;//��ʼ�����ÿ�
		TP_ARRAY=new Bitmap[TP_ARRAY_NAME.length];
		for(int i=0;i<TP_ARRAY_NAME.length;i++)
		{
			TP_ARRAY[i]=PicLoadUtil.loadBM(resources, TP_ARRAY_NAME[i]);
		}
		
		NEXTVIEW_ARRAY=null;//��ʼ�����ÿ�
		NEXTVIEW_ARRAY=new Bitmap[NEXTVIEW_ARRAY_NAME.length];
		for(int i=0;i<NEXTVIEW_ARRAY_NAME.length;i++)
		{
			NEXTVIEW_ARRAY[i]=PicLoadUtil.loadBM(resources, NEXTVIEW_ARRAY_NAME[i]);
		}
		
		GAMEVIEW_NUM_ARRAY=null;//��ʼ�����ÿ�
		GAMEVIEW_NUM_ARRAY=new Bitmap[GAMEVIEW_NUM_ARRAY_NAME.length];
		for(int i=0;i<GAMEVIEW_NUM_ARRAY_NAME.length;i++)
		{
			GAMEVIEW_NUM_ARRAY[i]=PicLoadUtil.loadBM(resources, GAMEVIEW_NUM_ARRAY_NAME[i]);
		}
		
		GAMEVIEW_QUESTION_ARRAY=null;//��ʼ�����ÿ�
		GAMEVIEW_QUESTION_ARRAY=new Bitmap[GAMEVIEW_QUESTION_ARRAY_NAME.length];
		for(int i=0;i<GAMEVIEW_QUESTION_ARRAY_NAME.length;i++)
		{
			GAMEVIEW_QUESTION_ARRAY[i]=PicLoadUtil.loadBM(resources, GAMEVIEW_QUESTION_ARRAY_NAME[i]);
		}
	}


}