package com.icarbonx.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
   
   
   
	/*
	 * 根据HTTP请求响应结果过滤食物的id
	 * @param response 响应体
	 * @param food 要查找的食物名称
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
	 * 获取食物的份量
	 */
	public static String getFoodWeight(String response)
	{
		JSONObject jsonResult;
		String weight=null;
		try {
			jsonResult = new JSONObject(response);
			String data=jsonResult.optString("data", "data不存在");
			JSONObject dataResult = new JSONObject(data);
			JSONArray list=dataResult.getJSONArray("list");
			  for(int j=0;j<list.length();j++)
		        {   
				   
		        	//取每个食物数组的第一种食物
					String food1=list.getJSONArray(j).optString(0);
					JSONObject food2 = new JSONObject(food1);
					weight = food2.optString("weight");
					
					

		        }
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return weight;
		
	}
	
	
	
	/*
	 * 过滤出食物名称，在前端可被展示的食物名称
	 * @param response HTTP请求响应结果
	 */
	public static List<String> getFoodNameList(String response)
	{
		JSONObject jsonResult;
		List foodlist=new ArrayList<>();
		try {
			jsonResult = new JSONObject(response);
			String data=jsonResult.optString("data", "data不存在");
			JSONObject dataResult = new JSONObject(data);
			JSONArray list=dataResult.getJSONArray("list");
			  for(int j=0;j<list.length();j++)
		        {   
				   String name=null;
		        	//取每个食物数组的第一种食物
					String food1=list.getJSONArray(j).optString(0);
					JSONObject food2 = new JSONObject(food1);
					name = food2.optString("name");
					foodlist.add(name);
					

		        }
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return foodlist;
	}
	
	
	
	
	/*
	 * 过滤出食物搜索结果,并以字符串的形式拼接
	 */

	public static String getFoodNameStr(String response)
	{
		StringBuffer sb=new StringBuffer();
        try {
			JSONObject jsonResult = new JSONObject(response);
			String code=jsonResult.getString("code");
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

	        }
            

	}
	 catch (Exception e) {
		e.printStackTrace();
	}
        return sb.toString();

	}
	
	/*
	 * 调用接口搜索食物
	 * @return 食物搜索结果集
	 */
	public static List<String> getFoodSearchResultList(String name)
	{
		List<String> foodlist;
		HttpRequest http=new HttpRequest("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J");
		String foodresult=http.postRequestContent("name="+name);
	    foodlist=getFoodNameList(foodresult);
		
		return foodlist;
	}
	
	/*
	 * 调用接口搜索食物
	 * @return 食物搜索结果集
	 */
	public static String getFoodSearchResultStr(String name)
	{
		String food;
		HttpRequest http=new HttpRequest("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J");
		String foodresult=http.postRequestContent("name="+name);
	    food=getFoodNameStr(foodresult);
		
		return food;
	}
	
	
/*
 * 调用搜索食物的结果，返回响应的body
 */
	public static String getFoodSearchBodyStr(String name)
	{
		String food;
		HttpRequest http=new HttpRequest("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J");
		String foodresult=http.postRequestContent("name="+name);
		return foodresult;
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
	
	public static void main(String args[])
	{
	
	}

}
