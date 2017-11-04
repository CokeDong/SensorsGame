package com.example.util;

import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	
	//create	
		//�������ߴ����ݿ�
		public static SQLiteDatabase createOrOpenDatabase()
		{
			SQLiteDatabase sld=null;
			try
	    	{
				sld=SQLiteDatabase.openDatabase
		    	(
		    			"/data/data/com.example.game/gamedb",
		    			null, 							//�α깤��
		    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //��дȨ�ޣ����������򴴽�
		    	);	    		
				String sql="create table if not exists gaterecord(gate integer,lock integer,pass interger);";
		    	sld.execSQL(sql);
		    	sql="create table if not exists setting(yinyue integer);";
		    	sld.execSQL(sql);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
			return sld;
		}
		
		//�ر����ݿ�ķ���
	    public static void closeDatabase(SQLiteDatabase sld)
	    {
	    	try
	    	{
		    	sld.close();     		
	    	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	    }
	    
	//insert  
	    //��gaterecord���в��� �����ڲŲ���
	    public static void insert(int gate,int lock,int pass)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    			sld=createOrOpenDatabase();//�����ݿ�
	    			String sql="insert into gaterecord values("+gate+","+lock+","+pass+");";
	            	sld.execSQL(sql);            	
	    	}  
			catch(Exception e)
			{
				e.printStackTrace(); 
			}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }
	    
	    //��setting���в��� �����ڲŲ���
	    public static void insertSetting(int yinyue)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//�����ݿ�
	    		String sql="insert into setting values("+yinyue+");";
	        	sld.execSQL(sql);            	
			}  
			catch(Exception e)
			{
				e.printStackTrace(); 
			}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
		}
	    
	//update    
	    
	    //����levelrecord�� ���ڲŸ���
	    public static void upDateLevel(int gate)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//�����ݿ�
	    		String sql="update levelrecord set pass="+1+"where gate="+gate+";";
	    		sld.execSQL(sql);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }
	    
	    //����setting�� ���ڲŸ���
	    public static void updateSetting(int yinyue)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//�����ݿ�
	    		String sql="update setting set yinyue="+yinyue+";";
	    		sld.execSQL(sql);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }

	//inquiry
	    //��ȡlock����¼�����ڷ���0
	    public static Integer getLock(int gate)  
	    {
	    	SQLiteDatabase sld=null;
	    	int lock=0;//Ҫ��ʼ����������Ϊ����ֵ��
	    	try
	    	{
	    		sld=createOrOpenDatabase();//�������ߴ����ݿ�
	    		String sql="select lock from gaterecord where gate ="+gate;
	    		Cursor cur=sld.rawQuery(sql, new String[]{});//��ȡ�α�
	    		while(cur.moveToNext())//������������Ҳ�������ѭ����
	    		{
	    			lock=cur.getInt(0);
	    		}
	    		cur.close();        	
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	} 
	    	finally
	    	{
	    		try{closeDatabase(sld);}
	    		catch(Exception e)
	    		{e.printStackTrace();}
	    	}
	    	return lock;
	    }
	    
	    //��ȡyinyue,����¼�����ڷ���0
	    public static Integer getYinyue()  
	    {
	    	SQLiteDatabase sld=null;
	    	int yinyue2=0;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//�������ߴ����ݿ�
	    		String sql="select yinyue from setting";
	    		Cursor cur=sld.rawQuery(sql, new String[]{});//��ȡ�α�
	    		while(cur.moveToNext())//������������Ҳ�������ѭ����
	    		{
	    			yinyue2=cur.getInt(0);
	    		}
	    		cur.close();        	
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	} 
	    	finally
	    	{
	    		try{closeDatabase(sld);}
	    		catch(Exception e)
	    		{e.printStackTrace();}
	    	}
	    	return yinyue2;
	    }
	 
	}