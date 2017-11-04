package com.example.game;

import static com.example.util.Constant.*;


import com.example.util.Constant;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainMenuView extends SurfaceView implements SurfaceHolder.Callback{
	FuckedGameActivity fuckedGameActivity;//��ȡactivity
	Paint paint;//��������
	
	//������
	public MainMenuView(FuckedGameActivity fuckedGameActivity)
	{ 
		super(fuckedGameActivity);
		this.fuckedGameActivity=fuckedGameActivity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
		paint=new Paint();
		paint.setAntiAlias(true);
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		//���Ʊ���
		canvas.drawBitmap(TP_ARRAY[6], xyoffset[6][0], xyoffset[6][1],paint);
		//��������
		canvas.drawBitmap(TP_ARRAY[1], xyoffset[1][0], xyoffset[1][1],paint);
		//����play��ť
		canvas.drawBitmap(TP_ARRAY[2], xyoffset[2][0], xyoffset[2][1],paint);
		//����������ť
		if(YINYUE_OPEN)//������ֿ�
		{
			canvas.drawBitmap(TP_ARRAY[3],xyoffset[3][0], xyoffset[3][1],paint);
		}
		else
		{
			canvas.drawBitmap(TP_ARRAY[4],xyoffset[4][0], xyoffset[4][1],paint);
		}
		//��������ѼͼƬ
		canvas.drawBitmap(TP_ARRAY[5], xyoffset[5][0], xyoffset[5][1],paint);
	}
	
	//���ü���
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNum=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNum)
		{
			case MotionEvent.ACTION_DOWN:
				//play��ť
				if(x>xyoffsetEvent[2][0]&&x<xyoffsetEvent[2][0]+TP_ARRAY[2].getWidth()*Constant.screenScaleResult.ratio&&
				   y>xyoffsetEvent[2][1]&&y<xyoffsetEvent[2][1]+TP_ARRAY[2].getHeight()*Constant.screenScaleResult.ratio)
				{
					fuckedGameActivity.hd.sendEmptyMessage(2);//ȥ�ؿ�ѡ�����
					
				}
				//������ť
				else if(x>xyoffsetEvent[3][0]&&x<xyoffsetEvent[3][0]+TP_ARRAY[3].getWidth()*Constant.screenScaleResult.ratio&&
					y>xyoffsetEvent[3][1]&&y<xyoffsetEvent[3][1]+TP_ARRAY[3].getHeight()*Constant.screenScaleResult.ratio)
				{
					if(Constant.YINYUE_OPEN)//������ֿ�
					{
						this.fuckedGameActivity.soundutil.stop_bg_sound();
						Constant.YINYUE_OPEN=!Constant.YINYUE_OPEN;
					}
					else if(!Constant.YINYUE_OPEN)//������ֹ�
					{
						this.fuckedGameActivity.soundutil.play_bg_sound();
						Constant.YINYUE_OPEN=!Constant.YINYUE_OPEN;
					}
					this.repaint();
					
				}
				
				
			break;
		}
		return true;
	}
	

	public void surfaceCreated(SurfaceHolder holder) {
		
		//�������֣�BGM��
		if(Constant.YINYUE_OPEN)//���������ť��
		{
			if(Constant.FROM_START)//�����Ҫ���²���
			{
				fuckedGameActivity.soundutil.stop_bg_sound();//ֹͣ���ű�������
				fuckedGameActivity.soundutil.play_bg_sound();//��ʼֹͣ���ű�������
			}
			
		}
		Constant.FROM_START=false;
		
		//�滭
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
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
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
