package com.example.util;

//import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;


public class PicLoadUtil 
{
	//载入图片，返回位图bitmap
	public static Bitmap loadBM(Resources res,String fname)//通过IO得到了图片的bitmap
    {
    	Bitmap result=null;    	
    	try
    	{
    		InputStream in=res.getAssets().open(fname);
			/*int ch=0;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    while((ch=in.read())!=-1)
		    {
		      	baos.write(ch);
		    }      
		    byte[] buff=baos.toByteArray();
		    baos.close();
		    in.close();
		    result=BitmapFactory.decodeByteArray(buff, 0, buff.length);//BitmapFactory
		    */
		    result = BitmapFactory.decodeStream(in);
		    //w2:result = BitmapFactory.decodeStream(in);
		    //w3:BitmapDrawable  bmpDraw = new BitmapDrawable(in);  
		    //   Bitmap bmp = bmpDraw.getBitmap();  
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    	return result;
    }
	
}