package org.minxc.emp.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 线程map的工具类
 * 让开发员随时放自己想要的东西到线程变量中
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年5月13日
 */
public class ThreadMapUtil {
	private static ThreadLocal<Map<String, Object>> threadLocalMap = new ThreadLocal<>();

	private ThreadMapUtil() {

	}

	private static Map<String, Object> map() {
		Map<String, Object> map = threadLocalMap.get();
		if (map == null) {
			threadLocalMap.set(new HashMap<String, Object>());
			map = threadLocalMap.get();
		}
		return map;
	}

	public static void put(String key, Object value) {
		map().put(key, value);
	}

	public static Object get(String key) {
		return map().get(key);
	}

	public static void remove(String key) {
		map().remove(key);
	}

	public static Object getOrDefault(String key, Object defaultValue) {
		return map().getOrDefault(key, defaultValue);
	}
	
	/**
	 * 
	 * 获取某个值，为空时创建mappingFunction
	 * 	
	 * @param key
	 * @param mappingFunction
	 * @return
	 */
	public static Object computeIfAbsent(String key, Function<? super String, ? extends Object> mappingFunction) {
		return map().computeIfAbsent(key, mappingFunction);
	}
}
