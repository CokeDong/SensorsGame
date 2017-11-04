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
		
		//���Ʊ���
		canvas.drawBitmap(TP_ARRAY[0], xyoffset[0][0], xyoffset[0][1],paint);
		//����ѡ��
		canvas.drawBitmap(TP_ARRAY[26],xyoffset[0][0], xyoffset[0][1],paint);
		//�������
		canvas.drawBitmap(GAMEVIEW_NUM_ARRAY[LEVEL], xyoffset[0][0]+TP_ARRAY[26].getWidth(), xyoffset[0][1],paint);
		//������ʾ��
		canvas.drawBitmap(GAMEVIEW_QUESTION_ARRAY[LEVEL], xyoffset[0][0], xyoffset[0][1]+TP_ARRAY[26].getHeight(),paint);

		switch(Constant.LEVEL)
		{	case 0:
				
				//����С��
				canvas.drawBitmap(TP_ARRAY[12], xyoffset[12][0], xyoffset[12][1],paint);
			
			break;
			
			case 1:
				
				//��������-��Ůͼ��
				canvas.drawBitmap(TP_ARRAY[14], xyoffset[14][0], xyoffset[14][1],paint);
				
				
			break;
			
			case 2:
				
				if(!Constant.LEVELTHREE_DARK_FLAG){
					//���ư���ɭ��
					canvas.drawBitmap(TP_ARRAY[16], xyoffset[16][0], xyoffset[16][1],paint);}
				else{
					//���ƺ�ҹɭ��
					canvas.drawBitmap(TP_ARRAY[17], xyoffset[17][0], xyoffset[17][1],paint);
					//����èͷӥ
					canvas.drawBitmap(TP_ARRAY[18], xyoffset[18][0], xyoffset[18][1],paint);
				}
				
			break;
			
			case 3:
				
				//����ƿ��1
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[20][0], xyoffset[20][1],paint);
				//����ƿ��3
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[20][0],xyoffset[20][1]+6*TP_ARRAY[20].getHeight()/4,paint);
				//����ƿ��4
				canvas.drawBitmap(TP_ARRAY[20], xyoffset[21][0], xyoffset[21][1]+6*TP_ARRAY[21].getHeight()/4,paint);
				if(!Constant.LEVELFOUR_SHAKE_FLAG){
					//����ƿ�ӣ�ûĭ��
					canvas.drawBitmap(TP_ARRAY[20], xyoffset[21][0], xyoffset[21][1],paint);}
				else{
					//����ƿ�ӣ���ĭ��
					canvas.drawBitmap(TP_ARRAY[21], xyoffset[21][0], xyoffset[21][1],paint);
					}
				
				
			break;
			
			case 4:
				
				
				if(!Constant.LEVELFIVE_LAMP_FLAG){
					//���Ƶ��ݣ�������
					canvas.drawBitmap(TP_ARRAY[23], xyoffset[23][0], xyoffset[23][1],paint);}
				else{
					//���Ƶ��ݣ�����
					canvas.drawBitmap(TP_ARRAY[24], xyoffset[24][0], xyoffset[24][1],paint);
					}
				
				
			break;
			
			
		}
	}
	
	
	public boolean onTouchEvent(MotionEvent e)
	{
		
		
		{//һ�Դ��������ڷֿ�
		//�������
		int currentNum=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNum)
		{
		case MotionEvent.ACTION_DOWN:
				
			//�ؿ�ѡ��
			if(x>xyoffsetEvent[0][0]&&x<xyoffsetEvent[0][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//�����������ѡ��
				  y>xyoffsetEvent[0][1]&&y<xyoffsetEvent[0][1]+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
			{
				//�رմ���������
				IF_CLOSING_SENSOR_LISTENING=true;
				
				//ע�⣡���ü�����־λ
				ALL_LEVEL_DELAY_FLAG=false;
				
				switch(Constant.LEVEL)
				{	
					case 0:
						
						LEVELONE_NEAR_FLAG=false;
						if(!YINYUE1){
							YINYUE1=true;
							fuckedGameActivity.soundutil.stop_bg_sound();
							if(YINYUE_OPEN)//������ֿ�
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
				//����ѡ�ؽ���
				fuckedGameActivity.hd.sendEmptyMessage(2);
			}
				
		break;
		}
		}
		//�ֲ�ͬ�ؿ�����
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
						
						//�ؿ�ѡ��
						if(x>xyoffsetEvent[18][0]&&x<xyoffsetEvent[18][0]+TP_ARRAY[18].getWidth()*Constant.screenScaleResult.ratio&&//��������������¿�ʼ
						   y>xyoffsetEvent[18][1]&&y<xyoffsetEvent[18][1]+TP_ARRAY[18].getHeight()*Constant.screenScaleResult.ratio)
						{
							//���¹�����Ϣ
							DBUtil.upDateLevel(Constant.LEVEL);
							//���������Ϣ
							if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
							{
								DBUtil.insert(Constant.LEVEL+1, 1, 0);
							}
							
							//��Ч
							if(Constant.YINYUE_OPEN)//������ֿ�
							{
								fuckedGameActivity.soundutil.playEffectsSound(2, 0);
							}
							
							//ע���ڹ����жϴ������־λ�����ã��Ա��´���ȷ����ؿ������ָ�ȫ�ֱ�����ʼ״̬����������IF_FIRST_SENSOR_LISTENING
							Constant.LEVELTHREE_DARK_FLAG=false;
							Constant.ALL_LEVEL_DELAY_FLAG=false;
							
							//Constant.LEVELTHREE_STOP_SENDING_FLAG=false;//��ʼ����,��nextview���濪������
							
							//������һ�ؽ���
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
						
						//�ؿ�ѡ��
						if(x>xyoffsetEvent[21][0]&&x<xyoffsetEvent[21][0]+TP_ARRAY[21].getWidth()*Constant.screenScaleResult.ratio&&//��������������¿�ʼ
						   y>xyoffsetEvent[21][1]&&y<xyoffsetEvent[21][1]+TP_ARRAY[21].getHeight()*Constant.screenScaleResult.ratio)
						{
							//���¹�����Ϣ
							DBUtil.upDateLevel(Constant.LEVEL);
							//���������Ϣ
							if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
							{
								DBUtil.insert(Constant.LEVEL+1, 1, 0);
							}
							
							//��Ч
							if(Constant.YINYUE_OPEN)//������ֿ�
							{
								fuckedGameActivity.soundutil.playEffectsSound(2, 0);
							}
							
							//ע���ڹ����жϴ������־λ�����ã��Ա��´���ȷ����ؿ������ָ�ȫ�ֱ�����ʼ״̬��
							Constant.LEVELFOUR_SHAKE_FLAG=false;
							
							//������һ�ؽ���
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
						//��������ֻ��������û��������ʽ
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
									//�Ѿ�������
									Constant.LEVELFIVE_LAMP_FLAG=true;
									repaint();
									
									if(!Constant.ALL_LEVEL_DELAY_FLAG)//��ʱ��ʼ
									{
										Constant.LEVELFIVE_LAMP_FLAG2=true;
										Constant.ALL_LEVEL_DELAY_FLAG=true;//��֤delayֻ����һ��

										new Handler().postDelayed(new Runnable() {
											@Override
											public void run() {
												
												if(Constant.LEVELFIVE_LAMP_FLAG2){//ά����2��
													//repaint();
													if(Constant.YINYUE_OPEN)//������ֿ�
													{
														fuckedGameActivity.soundutil.playEffectsSound(2, 0);
													}
													
													//���¹�����Ϣ
													DBUtil.upDateLevel(Constant.LEVEL);
													//���������Ϣ
													if(DBUtil.getLock(Constant.LEVEL+1)==0 && Constant.LEVEL!=4)
													{
														DBUtil.insert(Constant.LEVEL+1, 1, 0);
													}
													//ע���ڹ����жϴ������־λ�����ã��Ա��´���ȷ����ؿ������ָ�ȫ�ֱ�����ʼ״̬��
													Constant.LEVELFIVE_LAMP_FLAG=false;
													Constant.ALL_LEVEL_DELAY_FLAG=false;
													
													//������һ�ؽ���
													fuckedGameActivity.hd.sendEmptyMessage(1);
												}
												else{
													//���������Ϊ����ǰ�󴴽�����߳�
													Constant.ALL_LEVEL_DELAY_FLAG=false;
												}
												
											}
										}, Constant.ALL_LEVEL_TIME_DELAY);
										
										}
							
								}
						 
						 	}
						 else{
							//Ϩ�����
							Constant.LEVELFIVE_LAMP_FLAG=false;
							//���»�ͼ
							this.repaint(); 
							Constant.LEVELFIVE_LAMP_FLAG2=false;
							 
						 }
					break;
					case MotionEvent.ACTION_DOWN:
						
					break;
					case MotionEvent.ACTION_UP:
						//Ϩ�����
						Constant.LEVELFIVE_LAMP_FLAG=false;
						//���»�ͼ
						this.repaint(); 
						Constant.LEVELFIVE_LAMP_FLAG2=false;
					break;
					case MotionEvent.ACTION_POINTER_UP:
						//Ϩ�����
						Constant.LEVELFIVE_LAMP_FLAG=false;
						//���»�ͼ
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
		Canvas canvas = holder.lockCanvas();//��ȡ����
		try{
				synchronized(holder){
				draw(canvas);//���� 
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
