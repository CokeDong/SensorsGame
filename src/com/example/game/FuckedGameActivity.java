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
	
	WhichView curr;		//当前的界面
	
	MainMenuView 		mmv;//主界面
	NextView 			nv;//下一关界面
	LevelChooseView 	lcv;//关卡选择界面
	GameView 			gv;//游戏界面
	WelcomeView			wcv;//欢迎界面
	
	//声音类，利用soundutil进行声音控制
	public SoundUtil soundutil;
	
	//声音，调整音量大小
	AudioManager audio;
		
	//消息接收，不同view跳转  Handler使用（git TEST）
	public Handler hd=new Handler()
		{
			public void handleMessage(Message msg)
			{
				switch(msg.what)
				{
					case 0:
						gotoMainMenuView();//去主界面
						break;
					case 1:
						gotoNextView();//去跳转界面
						break;
					case 2:
						gotoLevelChooseView();//去选关界面
						break;
					case 3:
						gotoGameView();//去游戏界面
						break;	
					case 4:
						gotoWelcomeView();//去游戏界面
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
	
		
	//传感器相关
	SensorManager mySensorManager;
	Sensor accelerometerSensor;
	Sensor lightSensor;
	Sensor proximitySensor;
	
	
	//以下均为不同的传感器监听代码
	
	
	//加速度传感器
	private SensorEventListener accelerometerSensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//保证传感器监听只在GameView生效
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
////							Thread.sleep(1500);//毫秒   
////						    }   
////						    catch(Exception e){
////						    	
////						    }  
						
//							Constant.SENSOR_DELAY_COUNTER++;
//						    
//							if(Constant.SENSOR_DELAY_COUNTER>Constant.SENSOR_DELAY_COUNTER1){
//								if(Constant.YINYUE_OPEN)//如果音乐开
//								{
//									soundutil.playEffectsSound(2, 0);
//								}
//								//加入解锁信息
//								if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
//								{
//									DBUtil.insert(Constant.LEVEL+1, 0, 1);
//								}
//								//重置计数器
//								Constant.SENSOR_DELAY_COUNTER=0;
//								//进入下一关界面
//								hd.sendEmptyMessage(1);
//							}
//						}
//						else{
//							Constant.SENSOR_DELAY_COUNTER=0;
//						}
						
						
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){
							
						if( event.values[1] < -1.0*Constant.GRAVITYPRODUCT){
							
							if(!Constant.ALL_LEVEL_DELAY_FLAG)//延时开始
							{
								Constant.LEVELTWO_UPSIDEDOWN_FLAG=true;//保证delay的2秒内的任何非法动作会使其变为false，是前后无法进行衔接
								Constant.ALL_LEVEL_DELAY_FLAG=true;//保证delay的2秒内不会在被创建
								
								//已知阻塞主线程，是否阻塞传感器监听线程？	-否
								new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										
										
										
										
										
										if(Constant.LEVELTWO_UPSIDEDOWN_FLAG){//维持了2秒
											
											
											
											//过关音效
											if(Constant.YINYUE_OPEN)//如果音乐开
											{
												//播放音效
												soundutil.playEffectsSound(2, 0);
											}
											
											//更新过关信息
											DBUtil.upDateLevel(Constant.LEVEL);
											//加入解锁信息
											if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
											{
												DBUtil.insert(Constant.LEVEL+1, 1, 0);
											}
											
											//停止传感器监听；必须在重置标志位之前
											Constant.IF_CLOSING_SENSOR_LISTENING=true;
											
											//重置标志位
											Constant.LEVELTWO_UPSIDEDOWN_FLAG=false;
											Constant.ALL_LEVEL_DELAY_FLAG=false;
											
											//进入下一关界面
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
						//if(!Constant.IF_CLOSING_SENSOR_LISTENING){//因为不涉及延时的线程，所以不必加上这个条件
							
						if(!Constant.LEVELFOUR_SHAKE_FLAG){
							float xValue = Math.abs(event.values[0]);
							float yValue = Math.abs(event.values[1]);
							float zValue = Math.abs(event.values[2]);
							if (xValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD 
								|| yValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD 
								|| zValue > Constant.SHAKE_ACCELEROMETER_THRESHOLD) {
								
								////停止传感器监听
								//Constant.IF_CLOSING_SENSOR_LISTENING=true;
								
								// 认为用户摇动了手机，触发摇一摇逻辑
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
	
		
		
		
	//光传感器	
	private SensorEventListener lightSensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//保证传感器监听只在GameView生效
				if(curr == WhichView.GAME_VIEW)
				{
				switch(Constant.LEVEL)
				{	case 0:
						
					
					break;
					
					case 1:
						
						
						
					break;
					
					case 2:
						
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){//很好的方式
						if( event.values[0] < Constant.LIGHT_INTENSITY_THRESHOLD){
							
							
							if(!Constant.ALL_LEVEL_DELAY_FLAG)//延时开始
							{
								
								Constant.ALL_LEVEL_DELAY_FLAG=true;//保证delay只创建一次
								
								Constant.LEVELTHREE_DARK_FLAG=true;
								
								new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										
										if(Constant.LEVELTHREE_DARK_FLAG){//维持了2秒		
											//停止传感器监听
											Constant.IF_CLOSING_SENSOR_LISTENING=true;//最安全
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
		
		
		
		
	//距离传感器		
	private SensorEventListener proximitySensorListener = new SensorEventListener(){
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
			@Override
			public void onSensorChanged(SensorEvent event) {
				//保证传感器监听只在GameView生效
				if(curr == WhichView.GAME_VIEW)
				{
				switch(Constant.LEVEL)
				{	case 0:
					
						if(!Constant.IF_CLOSING_SENSOR_LISTENING){
						if (event.values[0] > 0) {
							//textView.setText("设备与对象间距离距离较远");
							if(Constant.LEVELONE_NEAR_FLAG){//从近到远，不是初始状态，但是可能包含没坚持2秒

								if(!Constant.YINYUE1){//已经坚持2秒，小冰音乐开始播放
									
									
									
									
									//更新过关信息
									DBUtil.upDateLevel(Constant.LEVEL);
									//加入解锁信息
									if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
									{
										DBUtil.insert(Constant.LEVEL+1, 1, 0);
									}
										
									
									
									Constant.YINYUE1=true;
									//soundutil.continued_lower_sound();
									soundutil.stop_bg_sound();
									
									//过关音效及背景乐
									if(Constant.YINYUE_OPEN)//如果音乐开
									{
										//播放音效
										soundutil.playEffectsSound(2, 0);
										//播放背景音乐
										soundutil.play_bg_sound();
									}
									
									
									//停止传感器监听；必须在重置标志位之前
									Constant.IF_CLOSING_SENSOR_LISTENING=true;
									
									//重置标志位
									Constant.ALL_LEVEL_DELAY_FLAG=false;
									Constant.LEVELONE_NEAR_FLAG=false;
									
//									//主线程延时1秒，什么也不做
//									try {
//										Thread.sleep(1000);
//									} catch (InterruptedException e) {
//										
//										e.printStackTrace();
//									}
									
									//进入下一关界面
									hd.sendEmptyMessage(1);
									
									
								}
								else{//没坚持2秒
									Constant.LEVELONE_NEAR_FLAG=false;
									//Constant.ALL_LEVEL_DELAY_FLAG=false;
								}
								
							}
							//否则就是LEVELONE_NEAR_FLAG=false 传感器测得是远 什么也不干（最初状态）
							
						}
						else{
							//textView.setText("设备与对象间距离距离较近");
							if(! Constant.LEVELONE_NEAR_FLAG){//状态是从远到近，不是在保持
								
								
								if(!Constant.ALL_LEVEL_DELAY_FLAG)//延时开始；否则什么也不干
								{	
									
									Constant.ALL_LEVEL_DELAY_FLAG=true;//保证靠近后delay只被创建一次
									Constant.LEVELONE_NEAR_FLAG=true;//保证不再重复判断靠近情况
									
									new Handler().postDelayed(new Runnable() {
										@Override
										public void run() {
											//重置是否开启延时线程标志 line481
											Constant.ALL_LEVEL_DELAY_FLAG=false;
											if(Constant.LEVELONE_NEAR_FLAG){//保持了2秒
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
		
		
		
		
	//进入主界面 case 0
	public void gotoMainMenuView()
	{
	    curr=WhichView.MAIN_MENU_VIEW;
		mmv=new MainMenuView(this);   
	    setContentView(mmv);
	}
	//进入跳转界面 case 1
    public void gotoNextView()
    {	
    	curr=WhichView.GOTO_NEXT_VIEW;
		nv=new NextView(this);   
    	setContentView(nv);
    }
	//进入选关界面 case 2
    public void gotoLevelChooseView()
    {
    	curr=WhichView.LEVEL_CHOOSE_VIEW;
    	lcv=new LevelChooseView(this);   
    	setContentView(lcv);
    }
    //进入游戏界面 case 3
    public void gotoGameView()
    {
    	curr=WhichView.GAME_VIEW;
    	gv=new GameView(this);   
    	setContentView(gv);
    }
    //进入游戏界面 case 4
    public void gotoWelcomeView()
    {
    	curr=WhichView.WELCOME_VIEW;
    	wcv=new WelcomeView(this);   
    	setContentView(wcv);
    }
 
    
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//声音控制
    	setVolumeControlStream(AudioManager.STREAM_MUSIC);
    	audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		//设置为无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
        WindowManager.LayoutParams. FLAG_FULLSCREEN); 
    	//设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //获取屏幕尺寸
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        Constant.SCREEN_WIDTH=dm.widthPixels;
        Constant.SCREEN_HEIGHT=dm.heightPixels;
        //初始化起始坐标(等比例缩放留白)
        Constant.scaleCL();
        //初始化数据库
        Constant.initDB();
        //初始化图片 使用线程(资源比较多，可能比较耗时)
        Constant.LOAD_FINISH=false;
        new Thread(){
      	   public void run(){
      		   Constant.initBitmap(getResources());  	
      		   Constant.LOAD_FINISH=true;
      		   //hd.sendEmptyMessage(0);//for test
      	   }
         }.start();
        //初始化声音缓冲池SoundPool soundPool; R.raw.*
        soundutil=new SoundUtil(this);//创建了对象，便存在（存储）成员变量、成员函数(private public protected)
        soundutil.initSounds();   
        //初始化传感器，监听器代码在前面
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometerSensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor=mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor=mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //默认需要重新播放音乐
        Constant.FROM_START=true;
        //默认播放的是背景音乐，而不是level-1的小冰唱歌
        Constant.YINYUE1=true;
        
        Constant.WELCOME_VIEW_ONCE=false;
        Constant.IF_CLOSING_SENSOR_LISTENING=false;
        
        //进入欢迎界面
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
//				DBUtil.updateSetting(a);//退出前存储设置信息，不用SharedPreferences sp
//    			System.exit(0);
//    			break; 
    		case  KeyEvent.KEYCODE_BACK://返回键的键值是 4
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
    				
    				DBUtil.updateSetting(a);//退出前存储设置信息，不用SharedPreferences sp
        			System.exit(0);
        		}
    			return true;
    		default:         
    				break;     
    	}     
    	return super.onKeyDown(keyCode, event); 
    } 
	
}
