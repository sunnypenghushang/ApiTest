package com.icarbonx.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析json内容
 * @author Administrator
 *
 */
public class Common {
	public static String getJsonSimpleData(String response, String key) {
		JSONObject json;
		String value = null;
		try {
			json = new JSONObject(response);
			value = json.optString("key");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

	public static JSONArray getJsonArrayData(String response, String key) {
		JSONObject json;
		JSONArray array = null;
		String value = null;
		try {
			json = new JSONObject(response);
			array = json.getJSONArray(key);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;

	}

}
