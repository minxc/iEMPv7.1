package org.minxc.emp.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * 项目名称：core-impl 类名称：JsonUtil 类 创建人：Xianchang.min 创建时间：2018年10月1日 下午2:38:43
 * 修改人：Xianchang.min 修改时间：2018年10月1日 下午2:38:43 修改备注：
 * 
 * @version 1.0
 **/

public class JsonUtil {
	protected static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 根据键获取值。
	 *
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return String
	 */
	public static String getString(JSONObject obj, String key, String defaultValue) {
		if (!obj.containsKey(key))
			return defaultValue;
		return obj.getString(key);
	}

	/**
	 * 根据键获取值。
	 *
	 * @param obj
	 * @param key
	 * @return String
	 */
	public static String getString(JSONObject obj, String key) {
		return getString(obj, key, "");
	}

	/**
	 * 根据键获取int值。
	 *
	 * @param obj
	 * @param key
	 * @return int
	 */
	public static int getInt(JSONObject obj, String key) {
		if (!obj.containsKey(key))
			return 0;
		return obj.getIntValue(key);
	}

	/**
	 * 根据键获取int值。
	 *
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return int
	 */
	public static int getInt(JSONObject obj, String key, int defaultValue) {
		if (!obj.containsKey(key))
			return defaultValue;
		return obj.getIntValue(key);
	}

	public static boolean getBoolean(JSONObject obj, String key) {
		if (!obj.containsKey(key))
			return false;
		return obj.getBoolean(key);
	}

	/**
	 * 根据键获取boolean值。
	 *
	 * @param obj
	 * @param key
	 * @param defaultValue
	 * @return boolean
	 */
	public static boolean getBoolean(JSONObject obj, String key, boolean defaultValue) {
		if (!obj.containsKey(key))
			return defaultValue;
		return obj.getBoolean(key);
	}

	/**
	 * 判断是jsonArray是否为空
	 *
	 * @param jsonArrStr
	 * @return
	 */
	public static boolean isNotEmptyJsonArr(String jsonArrStr) {
		return !isEmptyJsonArr(jsonArrStr);
	}

	/**
	 * 判断是jsonArray是否为空
	 *
	 * @param jsonArrStr
	 * @return
	 */
	public static boolean isEmptyJsonArr(String jsonArrStr) {
		if (StringUtil.isEmpty(jsonArrStr))
			return true;
		try {
			JSONArray jsonAry = JSONArray.parseArray(jsonArrStr);
			return jsonAry.size() > 0 ? false : true;
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			return true;
		}
	}

	/**
	 * 替换掉包含富文本的json 字符串中特殊的字符
	 *
	 * @param str
	 * @return
	 */
	public static String escapeSpecialChar(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {

			char c = str.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
	 *
	 * @param jsonObject void
	 * @throws @since 1.0.0
	 */
	public static void removeNull(JSONObject jsonObject) {
		for (String key : jsonObject.keySet()) {
			Object val = jsonObject.get(key);
			if (val == null) {
				jsonObject.put(key, "");
			}
		}
	}

	/**
	 * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
	 *
	 * @param jsonArray void
	 * @throws @since 1.0.0
	 */
	public static void removeNull(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.size(); i++) {
			removeNull(jsonArray.getJSONObject(i));
		}
	}

	/**
	 * 
	 * JSONArray转成JSONObject
	 * eg:
	 * [{id:"1",name:"a"},{id:"2",name:"b"}] 当keyName设置为id是，转换成
	 * {1:{id:"1",name:"a"},2:{id:"2",name:"b"}}
	 * 
	 *
	 * @param jsonArray
	 * @param keyName   :以哪个字段为key
	 * @return JSONObject
	 * @throws @since 1.0.0
	 */
	public static JSONObject arrayToObject(JSONArray jsonArray, String keyName) {
		JSONObject jsonObject = new JSONObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject temp = jsonArray.getJSONObject(i);
			jsonObject.put(temp.getString(keyName), temp);
		}
		return jsonObject;
	}

	/**
	 * 
	 * 把jsonObject 转到jsonArray,通常用于以下这种情况
	 * 为了保证jsonArray里的某个值是唯一的所以先用jsonObject来保存着
	 * eg:
	 * {a:{id:1,name:a},b:{id:2,name:b}}
	 * 转成：[{id:1,name:a},{id:2,name:b}]
	 * 
	 *
	 * @param jsonObject
	 * @return JSONArray
	 * @throws @since 1.0.0
	 */
	public static JSONArray objectToArray(JSONObject jsonObject) {
		JSONArray jsonArray = new JSONArray();
		for (Object key : jsonObject.keySet()) {
			jsonArray.add(jsonObject.get(key));
		}
		return jsonArray;
	}

	/**
	 * 
	 * 优化了JSON.parseObject()方法
	 * 
	 *
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T> T parseObject(String jsonStr, Class<T> cls) {
		if (StringUtil.isEmpty(jsonStr)) {
			return null;
		}
		return JSON.parseObject(jsonStr, cls);
	}

	/**
	 * 
	 * 优化了JSON.parseArray()方法
	 * 
	 *
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T> List<T> parseArray(String jsonStr, Class<T> cls) {
		if (StringUtil.isEmpty(jsonStr)) {
			return null;
		}
		return JSON.parseArray(jsonStr, cls);
	}

	/**
	 * 
	 * 优化了JSON.toJSONString()方法
	 * 
	 *
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String json = "{\"Demo\":{\"key\":\"Demo\",\"name\":\"Demo\",\"rights\":{\"r\":[{\"type\":\"everyone\",\"desc\":\"所有人\"}],\"w\":[{\"type\":\"none\",\"desc\":\"无\"}]},\"tableMap\":{\"Demo\":{\"columnMap\":{\"zjlx\":{\"comment\":\"证件类型\",\"key\":\"zjlx\",\"rights\":{}},\"zd1\":{\"comment\":\"字段1\",\"key\":\"zd1\",\"rights\":{}},\"ah\":{\"comment\":\"爱好\",\"key\":\"ah\",\"rights\":{}},\"bmId\":{\"comment\":\"部门ID\",\"key\":\"bmId\",\"rights\":{}},\"zd2\":{\"comment\":\"字段2\",\"key\":\"zd2\",\"rights\":{}},\"xb\":{\"comment\":\"性别\",\"key\":\"xb\",\"rights\":{}},\"bm\":{\"comment\":\"部门\",\"key\":\"bm\",\"rights\":{}},\"mz\":{\"comment\":\"名字\",\"key\":\"mz\",\"rights\":{}},\"nl\":{\"comment\":\"年龄\",\"key\":\"nl\",\"rights\":{}}},\"comment\":\"案例\",\"key\":\"Demo\",\"rights\":{}},\"DemoSub\":{\"columnMap\":{\"fk\":{\"comment\":\"外键\",\"key\":\"fk\",\"rights\":{}},\"ms\":{\"comment\":\"描述\",\"key\":\"ms\",\"rights\":{}},\"zd1\":{\"comment\":\"字段1\",\"key\":\"zd1\",\"rights\":{}},\"zd2\":{\"comment\":\"字段2\",\"key\":\"zd2\",\"rights\":{}},\"mz\":{\"comment\":\"名字\",\"key\":\"mz\",\"rights\":{}}},\"comment\":\"Demo子表\",\"key\":\"DemoSub\",\"rights\":{}}}}}";
		System.out.println(System.currentTimeMillis());
		Object obj = JSON.parse(json);
//		JacksonUtil.parseToJsonObject(json);
//		System.out.println(obj);
		System.out.println(System.currentTimeMillis());
	}
}
