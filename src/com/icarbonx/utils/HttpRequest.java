package com.icarbonx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

/**
 * 创建HTTP请求
 */
public class HttpRequest {
	private URL url;
	private String param;

	/*
	 * 如果是Post请求则用此构造方法 
	 * @param urlstr HTTP地址
	 * @param param 请求参数
	 */
	public HttpRequest(String urlstr) {

		try {
			this.param = param;
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * get请求构造方法
	 * @param urlstr URL地址
	 * @param param 传递的参数
	 * 
	 */
	public HttpRequest(String urlstr, String param) {

		try {
			String urlReal = urlstr + "?" + param;
			url = new URL(urlReal);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 成员方员，获取连接
	 */
	public HttpURLConnection getConnection() {
		HttpURLConnection connection = null;
		try {

			URLConnection urlconnection = null;
			urlconnection = url.openConnection();
			if (urlconnection instanceof HttpURLConnection)
				connection = (HttpURLConnection) urlconnection;
			else
				System.out.println("请输入正确的URL地址:");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	/*
	 * 成员方法，get方法获取请求结果
	 */
	public String getRequesContent() {
		String result = "";
		BufferedReader in = null;
		String target = null;
		HttpURLConnection conn = getConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

		try {
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("isTarget")) {
					target = line.split(",")[2].split(":")[1];
				}
				result += line;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

	/*
	 *成员方法, post方法获取请求结果，以name=参数的形式传参
	 */

	public String postRequestContent(String param) {
		OutputStreamWriter out = null;
		String result = "";
		BufferedReader in = null;
		String target = null;
		HttpURLConnection conn = getConnection();

		try {
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			out.write(param);

			// flush输出流的缓冲
			out.flush();
			
			// 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
           
            }
            //System.out.print("最终结果"+result);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // POST方法
		 finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
		return result;

	}
	
	
	
	/*
	 * 打印请求头
	 */
	public void printHeader() {
		HttpURLConnection conn = getConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

		Map<String, List<String>> map = conn.getHeaderFields();

		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));

		}

	}

	
	
	
	/*
	 * 类成员，post方法，传递json数据,HttpClient
	 * @param jsonstr 要传弟的json数据，String类型
	 */
	public static JSONObject doPostJson(String URL,String jsonstr)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		JSONObject response=null;
		HttpPost httpPost=new HttpPost(URL);
		  try {
	        	 JSONArray array=new JSONArray(jsonstr);
	        	 StringEntity s=new StringEntity(array.toString());
			
				//httpPost.setEntity(formEntity);
			
				s.setContentEncoding("UTF-8");
	            s.setContentType("application/json");
	            httpPost.setEntity(s);
	            HttpResponse res=httpclient.execute(httpPost);
	            if(res.getStatusLine().getStatusCode() == 200){
	                HttpEntity entity = res.getEntity();
	                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	                response= new JSONObject(result);	                
	            }
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
			return response;

	}
	
	/*
	 * Post方法传递键值对参数,通过HttpClient
	 */
	public static JSONObject doPostForm(String URL,List<NameValuePair> formparams)
	{   
		JSONObject result=null;
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(URL);
		try {
            //参数转码  
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");    
			httppost.setEntity(uefEntity);
			HttpResponse response=httpclient.execute(httppost);
			if(response.getStatusLine().getStatusCode() == 200){
			result=new JSONObject(EntityUtils.toString(response.getEntity()));
			}
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return result;
		
	}

	





	// 传入URL和参数获取食物
	public static void main(String args[]) {
		 List<NameValuePair> formparams = new ArrayList<NameValuePair>();    
         formparams.add(new BasicNameValuePair("name", "鸡蛋"));    
		 JSONObject a=doPostForm("http://60.205.107.6/oramirror_cloud/api/analysisData.do?app_key=xN12cQL0a6Ui2Aw1az1J", formparams);
		 System.out.println(a.toString());
		 
		

	}
	



}
