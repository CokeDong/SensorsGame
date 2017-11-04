package com.example.game;


import com.example.util.Constant;
import com.example.util.DBUtil;
import com.example.util.SoundUtil;
import com.example.util.*;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;

import android.media.AudioManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


enum WhichView{
	MAIN_MENU_VIEW,
	GOTO_NEXT_VIEW,
	LEVEL_CHOOSE_VIEW,
	GAME_VIEW,
	WELCOME_VIEW
	}


public class FuckedGameActivity extends Activity {
	
	WhichView curr;		//��ǰ�Ľ���
	
	MainMenuView 		mmv;//������
	NextView 			nv;//��һ�ؽ���
	LevelChooseView 	lcv;//�ؿ�ѡ�����
	GameView 			gv;//��Ϸ����
	WelcomeView			wcv;//��ӭ����
	
	//�����࣬����soundutil������������
	public SoundUtil soundutil;
	
	//����������������С
	AudioManager audio;
		
	//��Ϣ���գ���ͬview��ת  Handlerʹ�ã�git TEST��
	public Handler hd=new Handler()
		{
			public void handleMessage(Message msg)
			{
				switch(msg.what)
				{
					case 0:
						gotoMainMenuView();//ȥ������
						break;
					case 1:
						gotoNextView();//ȥ��ת����
						break;
					case 2:
						gotoLevelChooseView();//ȥѡ�ؽ���
						break;
					case 3:
						gotoGameView();//ȥ��Ϸ����
						break;	
					case 4:
						gotoWelcomeView();//ȥ��Ϸ����
						break;		
//					case 3:
//						gotoLevelOneView();
//						break;
//					case 4:
//						gotoLevelTwoView();
//						break;
//					case 5:
//						gotoLevelThreeView();
//						break;
//					case 6:
//						gotoLevelFourView();
//						break;
//					case 7:
//						gotoLevelFiveView();
//						break;
						

				}
			}
		};
	
		
	//���������
	SensorManager mySensorManager;
	Sensor accelerometerSensor;
	Sensor lightSensor;
	Sensor proximitySensor;
	
	
	//���¾�Ϊ��ͬ�Ĵ�������������
	
	
	//���ٶȴ�����
	private SensorEventListener accelerometerSensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//��֤����������ֻ��GameView��Ч
				if(curr == WhichView.GAME_VIEW)
				{
				switch(Constant.LEVEL)
				{	case 0:
						
					
					break;
					
					case 1:
						
						
//						if( event.values[1] < -1.0*Constant.GRAVITYPRODUCT){
//							
////						    try   
////						    {   
////						    Thread.currentThread();
////							Thread.sleep(1500);//����   
////						    }   
////						    catch(Exception e){
////						    	
////						    }  
						
//							Constant.SENSOR_DELAY_COUNTER++;
//						    
//							if(Constant.SENSOR_DELAY_COUNTER>Constant.SENSOR_DELAY_COUNTER1){
//								if(Constant.YINYUE_OPEN)//������ֿ�
//								{
//									soundutil.playEffectsSound(2, 0);
//								}
//								//���������Ϣ
//								if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
//								{
//									DBUtil.insert(Constant.LEVEL+1, 0, 1);
//								}
//								//���ü�����
//								Constant.SENSOR_DELAY_COUNTER=0;
//								//������һ�ؽ���
//								hd.sendEmptyMessage(1);
//							}
//						}
//						else{
//							Constant.SENSOR_DELAY_COUNTER=0;
//						}
						
						
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){
							
						if( event.values[1] < -1.0*Constant.GRAVITYPRODUCT){
							
							if(!Constant.ALL_LEVEL_DELAY_FLAG)//��ʱ��ʼ
							{
								Constant.LEVELTWO_UPSIDEDOWN_FLAG=true;//��֤delay��2���ڵ��κηǷ�������ʹ���Ϊfalse����ǰ���޷������ν�
								Constant.ALL_LEVEL_DELAY_FLAG=true;//��֤delay��2���ڲ����ڱ�����
								
								//��֪�������̣߳��Ƿ����������������̣߳�	-��
								new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										
										
										
										
										
										if(Constant.LEVELTWO_UPSIDEDOWN_FLAG){//ά����2��
											
											
											
											//������Ч
											if(Constant.YINYUE_OPEN)//������ֿ�
											{
												//������Ч
												soundutil.playEffectsSound(2, 0);
											}
											
											//���¹�����Ϣ
											DBUtil.upDateLevel(Constant.LEVEL);
											//���������Ϣ
											if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
											{
												DBUtil.insert(Constant.LEVEL+1, 1, 0);
											}
											
											//ֹͣ���������������������ñ�־λ֮ǰ
											Constant.IF_CLOSING_SENSOR_LISTENING=true;
											
											//���ñ�־λ
											Constant.LEVELTWO_UPSIDEDOWN_FLAG=false;
											Constant.ALL_LEVEL_DELAY_FLAG=false;
											
											//������һ�ؽ���
											hd.sendEmptyMessage(1);
										}
										else{
											Constant.ALL_LEVEL_DELAY_FLAG=false;
										}
										
										
									}
								}, Constant.ALL_LEVEL_TIME_DELAY);						
							}
						}	
						else{
								Constant.LEVELTWO_UPSIDEDOWN_FLAG=false;
								//Constant.ALL_LEVEL_DELAY_FLAG=false;
						}
						
						}
					break;
					
					case 2:
						
						
						
					break;
					
					case 3:
						//if(!Constant.IF_CLOSING_SENSOR_LISTENING){//��Ϊ���漰��ʱ���̣߳����Բ��ؼ����������
							
						if(!Constant.LEVELFOUR_SHAKE_FLAG){
							float xValue = Math.abs(event.values[0]);
							float yValue = Math.abs(event.values[1]);
							float zValue = Math.abs(event.values[2]);
							if (xValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD 
								|| yValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD 
								|| zValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD) {
								
								////ֹͣ����������
								//Constant.IF_CLOSING_SENSOR_LISTENING=true;
								
								// ��Ϊ�û�ҡ�����ֻ�������ҡһҡ�߼�
								Constant.LEVELFOUR_SHAKE_FLAG=true;
								gv.repaint();
							}
						}
						//}
						
					break;
					
					case 4:
						
						
						
					break;
					
					
				}
				}
			}		
		};
	
		
		
		
	//�⴫����	
	private SensorEventListener lightSensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//��֤����������ֻ��GameView��Ч
				if(curr == WhichView.GAME_VIEW)
				{
				switch(Constant.LEVEL)
				{	case 0:
						
					
					break;
					
					case 1:
						
						
						
					break;
					
					case 2:
						
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){//�ܺõķ�ʽ
						if( event.values[0] < Constant.LIGHT_INTENSITY_THRESHOLD){
							
							
							if(!Constant.ALL_LEVEL_DELAY_FLAG)//��ʱ��ʼ
							{
								
								Constant.ALL_LEVEL_DELAY_FLAG=true;//��֤delayֻ����һ��
								
								Constant.LEVELTHREE_DARK_FLAG=true;
								
								new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										
										if(Constant.LEVELTHREE_DARK_FLAG){//ά����2��		
											//ֹͣ����������
											Constant.IF_CLOSING_SENSOR_LISTENING=true;//�ȫ
											gv.repaint();
										}
										
										else{
											Constant.ALL_LEVEL_DELAY_FLAG=false;
										}
										
									}
								}, Constant.ALL_LEVEL_TIME_DELAY);
								
							}
						}
						else{
							Constant.LEVELTHREE_DARK_FLAG=false;
							//Constant.ALL_LEVEL_DELAY_FLAG=false;
						}
						}
						
						
					break;
					
					case 3:
						
						
						
					break;
					
					case 4:
						
						
						
					break;
					
					
				}
				}
			}		
		};
		
		
		
		
	//���봫����		
	private SensorEventListener proximitySensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//��֤����������ֻ��GameView��Ч
				if(curr == WhichView.GAME_VIEW)
				{
				switch(Constant.LEVEL)
				{	case 0:
					
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){
						if (event.values[0] > 0) {
							//textView.setText("�豸�������������Զ");
							if(Constant.LEVELONE_NEAR_FLAG){//�ӽ���Զ�����ǳ�ʼ״̬�����ǿ��ܰ���û���2��

								if(!Constant.YINYUE1){//�Ѿ����2�룬С�����ֿ�ʼ����
									
									
									
									
									//���¹�����Ϣ
									DBUtil.upDateLevel(Constant.LEVEL);
									//���������Ϣ
									if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
									{
										DBUtil.insert(Constant.LEVEL+1, 1, 0);
									}
										
									
									
									Constant.YINYUE1=true;
									//soundutil.continued_lower_sound();
									soundutil.stop_bg_sound();
									
									//������Ч��������
									if(Constant.YINYUE_OPEN)//������ֿ�
									{
										//������Ч
										soundutil.playEffectsSound(2, 0);
										//���ű�������
										soundutil.play_bg_sound();
									}
									
									
									//ֹͣ���������������������ñ�־λ֮ǰ
									Constant.IF_CLOSING_SENSOR_LISTENING=true;
									
									//���ñ�־λ
									Constant.ALL_LEVEL_DELAY_FLAG=false;
									Constant.LEVELONE_NEAR_FLAG=false;
									
//									//���߳���ʱ1�룬ʲôҲ����
//									try {
//										Thread.sleep(1000);
//									} catch (InterruptedException e) {
//										
//										e.printStackTrace();
//									}
									
									//������һ�ؽ���
									hd.sendEmptyMessage(1);
									
									
								}
								else{//û���2��
									Constant.LEVELONE_NEAR_FLAG=false;
									//Constant.ALL_LEVEL_DELAY_FLAG=false;
								}
								
							}
							//�������LEVELONE_NEAR_FLAG=false �����������Զ ʲôҲ���ɣ����״̬��
							
						}
						else{
							//textView.setText("�豸������������Ͻ�");
							if(! Constant.LEVELONE_NEAR_FLAG){//״̬�Ǵ�Զ�����������ڱ���
								
								
								if(!Constant.ALL_LEVEL_DELAY_FLAG)//��ʱ��ʼ������ʲôҲ����
								{	
									
									Constant.ALL_LEVEL_DELAY_FLAG=true;//��֤������delayֻ������һ��
									Constant.LEVELONE_NEAR_FLAG=true;//��֤�����ظ��жϿ������
									
									new Handler().postDelayed(new Runnable() {
										@Override
										public void run() {
											//�����Ƿ�����ʱ�̱߳�־ line481
											Constant.ALL_LEVEL_DELAY_FLAG=false;
											if(Constant.LEVELONE_NEAR_FLAG){//������2��
												Constant.YINYUE1=false;
												soundutil.stop_bg_sound();
												soundutil.play_bg_sound();
												//soundutil.lower_sound();
											}
											
										}
									}, Constant.ALL_LEVEL_TIME_DELAY);
									
								}
							}
						}							
						}	
					
					
					break;
					
					case 1:
						
						
						
					break;
					
					case 2:
						
						
						
					break;
					
					case 3:
						
						
						
					break;
					
					case 4:
						
						
						
					break;
					
					
				}
				}
			}		
		};
		
		
		
		
	//���������� case 0
	public void gotoMainMenuView()
	{
	    curr=WhichView.MAIN_MENU_VIEW;
		mmv=new MainMenuView(this);   
	    setContentView(mmv);
	}
	//������ת���� case 1
    public void gotoNextView()
    {	
    	curr=WhichView.GOTO_NEXT_VIEW;
		nv=new NextView(this);   
    	setContentView(nv);
    }
	//����ѡ�ؽ��� case 2
    public void gotoLevelChooseView()
    {
    	curr=WhichView.LEVEL_CHOOSE_VIEW;
    	lcv=new LevelChooseView(this);   
    	setContentView(lcv);
    }
    //������Ϸ���� case 3
    public void gotoGameView()
    {
    	curr=WhichView.GAME_VIEW;
    	gv=new GameView(this);   
    	setContentView(gv);
    }
    //������Ϸ���� case 4
    public void gotoWelcomeView()
    {
    	curr=WhichView.WELCOME_VIEW;
    	wcv=new WelcomeView(this);   
    	setContentView(wcv);
    }
 
    
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��������
    	setVolumeControlStream(AudioManager.STREAM_MUSIC);
    	audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		//����Ϊ�ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//����Ϊȫ��
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
        WindowManager.LayoutParams. FLAG_FULLSCREEN); 
    	//����Ϊ����
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //��ȡ��Ļ�ߴ�
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        Constant.SCREEN_WIDTH=dm.widthPixels;
        Constant.SCREEN_HEIGHT=dm.heightPixels;
        //��ʼ����ʼ����(�ȱ�����������)
        Constant.scaleCL();
        //��ʼ�����ݿ�
        Constant.initDB();
        //��ʼ��ͼƬ ʹ���߳�(��Դ�Ƚ϶࣬���ܱȽϺ�ʱ)
        Constant.LOAD_FINISH=false;
        new Thread(){
      	   public void run(){
      		   Constant.initBitmap(getResources());  	
      		   Constant.LOAD_FINISH=true;
      		   //hd.sendEmptyMessage(0);//for test
      	   }
         }.start();
        //��ʼ�����������SoundPool soundPool; R.raw.*
        soundutil=new SoundUtil(this);//�����˶��󣬱���ڣ��洢����Ա��������Ա����(private public protected)
        soundutil.initSounds();   
        //��ʼ����������������������ǰ��
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometerSensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor=mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor=mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //Ĭ����Ҫ���²�������
        Constant.FROM_START=true;
        //Ĭ�ϲ��ŵ��Ǳ������֣�������level-1��С������
        Constant.YINYUE1=true;
        
        Constant.WELCOME_VIEW_ONCE=false;
        Constant.IF_CLOSING_SENSOR_LISTENING=false;
        
        //���뻶ӭ����
        hd.sendEmptyMessage(4); 
		
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();		
		mySensorManager.unregisterListener(accelerometerSensorListener);
		mySensorManager.unregisterListener(lightSensorListener);
		mySensorManager.unregisterListener(proximitySensorListener);
		
	}
    @Override
	protected void onResume() {
		super.onResume();
		mySensorManager.registerListener(accelerometerSensorListener, accelerometerSensor,
				SensorManager.SENSOR_DELAY_GAME);
		mySensorManager.registerListener(lightSensorListener, lightSensor,
				SensorManager.SENSOR_DELAY_FASTEST);
		mySensorManager.registerListener(proximitySensorListener, proximitySensor,
				SensorManager.SENSOR_DELAY_FASTEST);
	}
    
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {     
    	switch (keyCode) 
    	{     
    		case KeyEvent.KEYCODE_VOLUME_UP:         
    			audio.adjustStreamVolume(             
    					AudioManager.STREAM_MUSIC,             
    					AudioManager.ADJUST_RAISE,             
    					AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);         
    			return true;
    		case KeyEvent.KEYCODE_VOLUME_DOWN:         
    			audio.adjustStreamVolume(             
    					AudioManager.STREAM_MUSIC,             
    					AudioManager.ADJUST_LOWER,             
    					AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);         
    			return true; 
//    		case  KeyEvent.KEYCODE_HOME:
//    			int a;
//				if(Constant.YINYUE_OPEN==true)
//				{
//					a=1;
//				}
//				else
//				{
//					a=2;
//				}
//				
//				DBUtil.updateSetting(a);//�˳�ǰ�洢������Ϣ������SharedPreferences sp
//    			System.exit(0);
//    			break; 
    		case  KeyEvent.KEYCODE_BACK://���ؼ��ļ�ֵ�� 4
    			if(curr==WhichView.MAIN_MENU_VIEW)
        		{
    				
    				int a;
    				if(Constant.YINYUE_OPEN==true)
    				{
    					a=1;
    				}
    				else
    				{
    					a=2;
    				}
    				
    				DBUtil.updateSetting(a);//�˳�ǰ�洢������Ϣ������SharedPreferences sp
        			System.exit(0);
        		}
    			return true;
    		default:         
    				break;     
    	}     
    	return super.onKeyDown(keyCode, event); 
    } 
	
}
