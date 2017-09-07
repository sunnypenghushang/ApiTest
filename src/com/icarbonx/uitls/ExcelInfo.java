package com.icarbonx.uitls;

/*
 * 读取一个excel表格，将其每一行数据抽象成一个 bean ExcelInfo
 */
public class ExcelInfo {
	private int index;
	private String food=null;
	public int getindex()
	{
		return index;
	}
	
	public void setIndex(int index)
	{
		this.index=index;
	}
	public String getfood()
	{
		return food;
	}
	
	public void setFood(String food)
	{
		this.food=food;
	}
	
	 public String toString() 
	 { 
		 return "ExcelInfo{" + "food=" + index + ",'}'"; 
	 }
	
	


}
