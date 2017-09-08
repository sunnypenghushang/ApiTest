package com.icarbonx.api;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
 * 获取HTTP请求结果
 */
public class foodUtil {
	
   static String refood="";
   
	/*
	 * 从类型为String的返回数据中过滤出食物
	 * @param response 接口返回的请求结果
	 */

	public static String getResponseFood(String response)
	{
		StringBuffer sb=new StringBuffer();
        try {
			JSONObject jsonResult = new JSONObject(response);
			String code=jsonResult.getString("code");
		//	sb.append(code+".返回的食物为：");
			
			String data=jsonResult.optString("data", "data不存在");//optString会在得不到你想要的值时候返回空字符串”“，而getString会抛出异常

			JSONObject dataResult = new JSONObject(data);
			JSONArray list=dataResult.getJSONArray("list");
					//optString("list","list不存在");
			if(list.length()==0)
			{
				sb.append("搜索结果为空");
			}
	        for(int j=0;j<list.length();j++)
	        {   
	        	//取每个食物数组的第一种食物
				String food=list.getJSONArray(j).optString(0);
				JSONObject food2 = new JSONObject(food);
				refood=food2.optString("name");
				sb.append(refood);
				//判断返回的食物是否与输入食物相等
                  
				//食物的份量
		//		sb.append(food2.optString("weight")+",");

	        }
            

	}
	 catch (Exception e) {
		e.printStackTrace();
	}
        return sb.toString();

	}
	
	
	/*
	 * 获取食物的id
	 */
	public static String getFoodId(String response,String food)
	{
		JSONObject jsonResult;
		String id=null;
		try {
			jsonResult = new JSONObject(response);
			String data=jsonResult.optString("data", "data不存在");
			JSONObject dataResult = new JSONObject(data);
			JSONArray list=dataResult.getJSONArray("list");
					//optString("list","list不存在");
		       for(int j=0;j<list.length();j++)
		        {   
		        	//取每个食物数组的第一种食物
					String food1=list.getJSONArray(j).optString(0);
				
					JSONObject food2 = new JSONObject(food1);
					id = food2.optString("id");
					

		        }
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;

	}
	

	/*
	 *打印出请求的食物结果
	 */
	public static void printFoodresult(String parame,String weight)
	{   
		
		

     
  
    } 

    
	
	/*
	 * 比较字符串是否相等
	 */
	public static boolean compareStr(String source,String target)
	{   
		if(source.equals(target))
			return true;
		else
			return false;
	}

}
