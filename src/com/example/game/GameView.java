package com.example.game;

import static com.example.util.Constant.*;

import com.example.util.Constant;
import com.example.util.DBUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	FuckedGameActivity fuckedGameActivity;
	Paint paint;

	public GameView(FuckedGameActivity fuckedGameActivity ) {
		super(fuckedGameActivity);
		this.fuckedGameActivity=fuckedGameActivity;
		this.getHolder().addCallback(this);
		paint=new Paint();
		paint.setAntiAlias(true);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		//绘制背景
		canvas.drawBitmap(TP_ARRAY[0], xyoffset[0][0], xyoffset[0][1],paint);
		//绘制选关
		canvas.drawBitmap(TP_ARRAY[26],xyoffset[0][0], xyoffset[0][1],paint);
		//绘制题号
		canvas.drawBitmap(GAMEVIEW_NUM_ARRAY[LEVEL], xyoffset[0][0]+TP_ARRAY[26].getWidth(), xyoffset[0][1],paint);
		//绘制提示语
		canvas.drawBitmap(GAMEVIEW_QUESTION_ARRAY[LEVEL], xyoffset[0][0], xyoffset[0][1]+TP_ARRAY[26].getHeight(),paint);

		switch(Constant.LEVEL)
		{	case 0:
				
				//绘制小冰
				canvas.drawBitmap(TP_ARRAY[12], xyoffset[12][0], xyoffset[12][1],paint);
			
			break;
			
			case 1:
				
				//绘制老人-少女图像
				canvas.drawBitmap(TP_ARRAY[14], xyoffset[14][0], xyoffset[14][1],paint);
				
				
			break;
			
			case 2:
				
				if(!Constant.LEVELTHREE_DARK_FLAG){
					//绘制白天森林
					canvas.drawBitmap(TP_ARRAY[16], xyoffset[16][0], xyoffset[16][1],paint);}
				else{
					//绘制黑夜森林
					canvas.drawBitmap(TP_ARRAY[17], xyoffset[17][0], xyoffset[17][1],paint);
					//绘制猫头鹰
					canvas.drawBitmap(TP_ARRAY[18], xyoffset[18][0], xyoffset[18][1],paint);
				}
				
			break;
			
			case 3:
				
				//绘制瓶子1
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[20][0], xyoffset[20][1],paint);
				//绘制瓶子3
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[20][0],xyoffset[20][1]+6*TP_ARRAY[20].getHeight()/4,paint);
				//绘制瓶子4
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[21][0], xyoffset[21][1]+6*TP_ARRAY[21].getHeight()/4,paint);
				if(!Constant.LEVELFOUR_SHAKE_FLAG){
					//绘制瓶子（没沫）
					canvas.drawBitmap(TP_ARRAY[20], xyoffset[21][0], xyoffset[21][1],paint);}
				else{
					//绘制瓶子（有沫）
					canvas.drawBitmap(TP_ARRAY[21], xyoffset[21][0], xyoffset[21][1],paint);
					}
				
				
			break;
			
			case 4:
				
				
				if(!Constant.LEVELFIVE_LAMP_FLAG){
					//绘制灯泡（不亮）
					canvas.drawBitmap(TP_ARRAY[23], xyoffset[23][0], xyoffset[23][1],paint);}
				else{
					//绘制灯泡（亮）
					canvas.drawBitmap(TP_ARRAY[24], xyoffset[24][0], xyoffset[24][1],paint);
					}
				
				
			break;
			
			
		}
	}
	
	
	public boolean onTouchEvent(MotionEvent e)
	{
		
		
		{//一对大括号用于分块
		//整体监听
		int currentNum=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNum)
		{
		case MotionEvent.ACTION_DOWN:
				
			//关卡选择
			if(x>xyoffsetEvent[0][0]&&x<xyoffsetEvent[0][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是选关
				  y>xyoffsetEvent[0][1]&&y<xyoffsetEvent[0][1]+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
			{
				//关闭传感器监听
				IF_CLOSING_SENSOR_LISTENING=true;
				
				//注意！重置监听标志位
				ALL_LEVEL_DELAY_FLAG=false;
				
				switch(Constant.LEVEL)
				{	
					case 0:
						
						LEVELONE_NEAR_FLAG=false;
						if(!YINYUE1){
							YINYUE1=true;
							fuckedGameActivity.soundutil.stop_bg_sound();
							if(YINYUE_OPEN)//如果音乐开
							{
								fuckedGameActivity.soundutil.play_bg_sound();	
							}
							
						}
						
						
					break;
					
					case 1:
						LEVELTWO_UPSIDEDOWN_FLAG=false;
						
						
					break;
					case 2:
						LEVELTHREE_DARK_FLAG=false;
						
						
					break;
					case 3:
						LEVELFOUR_SHAKE_FLAG=false;
						
						
					break;
					case 4:
						LEVELFIVE_LAMP_FLAG=false;
						LEVELFIVE_LAMP_FLAG2=false;
						
					break;
				}
				//进入选关界面
				fuckedGameActivity.hd.sendEmptyMessage(2);
			}
				
		break;
		}
		}
		//分不同关卡监听
		switch(Constant.LEVEL)
		{	
			case 0:
			
			
			break;
			
			case 1:
				
				
				
			break;
			
			case 2:
				if(Constant.IF_CLOSING_SENSOR_LISTENING){
					int currentNum=e.getAction();
					float x=e.getX();
					float y=e.getY();		
					switch(currentNum)
					{
						case MotionEvent.ACTION_DOWN:
						
						//关卡选择
						if(x>xyoffsetEvent[18][0]&&x<xyoffsetEvent[18][0]+TP_ARRAY[18].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是重新开始
						   y>xyoffsetEvent[18][1]&&y<xyoffsetEvent[18][1]+TP_ARRAY[18].getHeight()*Constant.screenScaleResult.ratio)
						{
							//更新过关信息
							DBUtil.upDateLevel(Constant.LEVEL);
							//加入解锁信息
							if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
							{
								DBUtil.insert(Constant.LEVEL+1, 1, 0);
							}
							
							//音效
							if(Constant.YINYUE_OPEN)//如果音乐开
							{
								fuckedGameActivity.soundutil.playEffectsSound(2, 0);
							}
							
							//注意在过关判断处加入标志位的重置，以便下次正确进入关卡（即恢复全局变量初始状态）所以引入IF_FIRST_SENSOR_LISTENING
							Constant.LEVELTHREE_DARK_FLAG=false;
							Constant.ALL_LEVEL_DELAY_FLAG=false;
							
							//Constant.LEVELTHREE_STOP_SENDING_FLAG=false;//开始监听,在nextview里面开启监听
							
							//进入下一关界面
							fuckedGameActivity.hd.sendEmptyMessage(1);
							}
						break;
					}
				}		
			break;
			
			case 3:
				if(Constant.LEVELFOUR_SHAKE_FLAG){
					int currentNum=e.getAction();
					float x=e.getX();
					float y=e.getY();		
					switch(currentNum)
					{
					case MotionEvent.ACTION_DOWN:
						
						//关卡选择
						if(x>xyoffsetEvent[21][0]&&x<xyoffsetEvent[21][0]+TP_ARRAY[21].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是重新开始
						   y>xyoffsetEvent[21][1]&&y<xyoffsetEvent[21][1]+TP_ARRAY[21].getHeight()*Constant.screenScaleResult.ratio)
						{
							//更新过关信息
							DBUtil.upDateLevel(Constant.LEVEL);
							//加入解锁信息
							if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
							{
								DBUtil.insert(Constant.LEVEL+1, 1, 0);
							}
							
							//音效
							if(Constant.YINYUE_OPEN)//如果音乐开
							{
								fuckedGameActivity.soundutil.playEffectsSound(2, 0);
							}
							
							//注意在过关判断处加入标志位的重置，以便下次正确进入关卡（即恢复全局变量初始状态）
							Constant.LEVELFOUR_SHAKE_FLAG=false;
							
							//进入下一关界面
							fuckedGameActivity.hd.sendEmptyMessage(1);
						}
						
					break;
					}
		
				}
				
			break;
			
			case 4:
				int currentNum=e.getActionMasked();
	
				switch(currentNum)
				{				
					case MotionEvent.ACTION_POINTER_DOWN:
						//过关条件只有这样，没有其他方式
						 if (e.getPointerCount() == 2) {  
							 	float x0=e.getX(0);
								float y0=e.getY(0);
								float x1=e.getX(1);
								float y1=e.getY(1);
								if( 
									(
										x0>(xyoffsetEvent[23][0]+240*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										x0<(xyoffsetEvent[23][0]+240*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										y0>(xyoffsetEvent[23][1]+460*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										y0<(xyoffsetEvent[23][1]+460*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										x1>(xyoffsetEvent[23][0]+325*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										x1<(xyoffsetEvent[23][0]+325*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										y1>(xyoffsetEvent[23][1]+245*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										y1<(xyoffsetEvent[23][1]+245*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) 
									) ||
									(	
										x1>(xyoffsetEvent[23][0]+240*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										x1<(xyoffsetEvent[23][0]+240*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										y1>(xyoffsetEvent[23][1]+460*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										y1<(xyoffsetEvent[23][1]+460*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										x0>(xyoffsetEvent[23][0]+325*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										x0<(xyoffsetEvent[23][0]+325*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL) &&
										y0>(xyoffsetEvent[23][1]+245*Constant.screenScaleResult.ratio-Constant.DELTA_PIXEL) && 
										y0<(xyoffsetEvent[23][1]+245*Constant.screenScaleResult.ratio+Constant.DELTA_PIXEL)
									)	
								  ){
									//已经被点亮
									Constant.LEVELFIVE_LAMP_FLAG=true;
									repaint();
									
									if(!Constant.ALL_LEVEL_DELAY_FLAG)//延时开始
									{
										Constant.LEVELFIVE_LAMP_FLAG2=true;
										Constant.ALL_LEVEL_DELAY_FLAG=true;//保证delay只创建一次

										new Handler().postDelayed(new Runnable() {
											@Override
											public void run() {
												
												if(Constant.LEVELFIVE_LAMP_FLAG2){//维持了2秒
													//repaint();
													if(Constant.YINYUE_OPEN)//如果音乐开
													{
														fuckedGameActivity.soundutil.playEffectsSound(2, 0);
													}
													
													//更新过关信息
													DBUtil.upDateLevel(Constant.LEVEL);
													//加入解锁信息
													if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
													{
														DBUtil.insert(Constant.LEVEL+1, 1, 0);
													}
													//注意在过关判断处加入标志位的重置，以便下次正确进入关卡（即恢复全局变量初始状态）
													Constant.LEVELFIVE_LAMP_FLAG=false;
													Constant.ALL_LEVEL_DELAY_FLAG=false;
													
													//进入下一关界面
													fuckedGameActivity.hd.sendEmptyMessage(1);
												}
												else{
													//避免恶意行为导致前后创建多个线程
													Constant.ALL_LEVEL_DELAY_FLAG=false;
												}
												
											}
										}, Constant.ALL_LEVEL_TIME_DELAY);
										
										}
							
								}
						 
						 	}
						 else{
							//熄灭灯泡
							Constant.LEVELFIVE_LAMP_FLAG=false;
							//重新绘图
							this.repaint(); 
							Constant.LEVELFIVE_LAMP_FLAG2=false;
							 
						 }
					break;
					case MotionEvent.ACTION_DOWN:
						
					break;
					case MotionEvent.ACTION_UP:
						//熄灭灯泡
						Constant.LEVELFIVE_LAMP_FLAG=false;
						//重新绘图
						this.repaint(); 
						Constant.LEVELFIVE_LAMP_FLAG2=false;
					break;
					case MotionEvent.ACTION_POINTER_UP:
						//熄灭灯泡
						Constant.LEVELFIVE_LAMP_FLAG=false;
						//重新绘图
						this.repaint(); 
						Constant.LEVELFIVE_LAMP_FLAG2=false;
					break;
					
				}
				
				
			break;
			
		}
		
		
		
		return true;
	}
	
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		Canvas canvas=holder.lockCanvas();
		try
		{
			synchronized(holder)
			{
				draw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//获取画布
		try{
				synchronized(holder){
				draw(canvas);//绘制 
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

}
