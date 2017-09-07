package com.icarbonx.uitls;

import java.util.BitSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.testng.internal.thread.TestNGThreadFactory;

class Test1 implements Runnable {
	private String threadname;
	private Thread t;
	
	
	 public Test1(String threadname) {
		// TODO Auto-generated constructor stub
		 this.threadname=threadname;
		 System.out.println("Creating "+threadname);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(threadname+"开始运行了");
		for(int i=1;i<5;i++)
		{
			System.out.println("Thraed:"+threadname+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void start()
	{
		System.out.println(threadname+"正在运行");
		
		if(t== null)
		{
			t=new Thread(this,threadname);
			t.start();
		}
		
	}
	
}
	
public class Test
{
	public static void main(String args[])
	{

		
		Hashtable table=new Hashtable();
		table.put("name", "penghong");
		table.put("age", "25");
		Enumeration i=table.keys();
		while(i.hasMoreElements())
		{
			System.out.println(table.get(i.nextElement()));
		}
		
	
		
		
		
	}
}
	

	
	
