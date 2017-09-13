package com.icarbonx.kujing;

import java.util.Random;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


/*
 * 随机返回数组中的食物
 * 
 */
public class createFood{
    
	static String[] vegetables={"豆角","茄子","土豆"};
	static String[] fruit={"苹果","香蕉","梨"};
	static String[] meat={"牛肉","羊肉","猪肉","豆腐"};
	static String[] drink={"牛奶","咖啡","澄汁"};
	static String[] stapleFood={"米饭","面包","肉包"};
	
	
	
	
	//随机生成蔬菜
	public static String getVegetables()
	{
		return vegetables[new Random().nextInt(vegetables.length)];
	}
	
	
	//随机生成水果
	public static String getFruit()
	{

		return fruit[new Random().nextInt(fruit.length)];
	}
	
	//随机生成肉类
	public static String getMeat()
	{

		return meat[new Random().nextInt(meat.length)];
	}
	
	//随机生成饮料
	public static String getDrink()
	{

		return drink[new Random().nextInt(drink.length)];
		
	}
	
	//随机短成主食
	public static String getStapleFood()
	{

		return stapleFood[new Random().nextInt(stapleFood.length)];
	}
	
	//返回啊蔬菜列表的长度
	public static int getVeglength()
	{
			return vegetables.length;
	}
		
		/*
		 * 返回肉类列表长度
		 */
	public static int getMeatlength()
	{
			return meat.length;
	}
		
	
	/*
	 * 返回饮料列表长度
	 */
	public static int getDrinklength()
	{
			return drink.length;
	}
		
	
	/*
	 * 返回主食列表长度
	 */
	public static int getStaplength()
	{
		return stapleFood.length;
	}
		
	
	/*
	 * 返回水果列表长度
	 */
	
	public static int getFruitlength()
	{
		return fruit.length;
	}
	
	
	
	
	/*
	 * 返回水果列表
	 */
	public static String[] getVegList()
	{
			return vegetables;
	}
		
	/*
	 * 返回肉类列表
	 */
	public static String[] getMeatList()
	{
			return meat;
	}
		
	/*
	 * 返回饮料列表
	 */
	public static String[] getDrinkList()
	{
			return drink;
	}
		
	/*
	 * 返回主食列表
	 */
	public static String[] getStapList()
	{
		return stapleFood;
	}
		
	/*
	 * 返回水果列表
	 */
	
	public static String[] getFruitList()
	{
		return fruit;
	}
	
	public static void main(String args[])
	{
	
	}
	
	/*
	 * 返回语音文本
	 */
	public static String getMultFood()
	{   
		return "我今天喝了一杯"+drink[new Random().nextInt(drink.length)]+"吃了100克"+
		         stapleFood[new Random().nextInt(stapleFood.length)]+"和100克"+meat[new Random().nextInt(meat.length)];
		
	}
	
	
	

}
