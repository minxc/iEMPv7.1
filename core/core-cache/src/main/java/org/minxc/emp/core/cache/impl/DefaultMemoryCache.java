/**
 * 
 */
package org.minxc.emp.core.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.minxc.emp.core.cache.Cache;
import org.springframework.stereotype.Component;

/**      
* 项目名称：core-cache   
* 类名称：DefaultMemoryCache   
* 类   
* 创建人：Xianchang.min   
* 创建时间：2018年10月1日 下午3:18:37   
* 修改人：Xianchang.min   
* 修改时间：2018年10月1日 下午3:18:37   
* 修改备注：   
* @version  1.0  
**/

@Component
public class DefaultMemoryCache<T> implements Cache<T> {
	
	private Map<String, T> map = new ConcurrentHashMap<String, T>();

	public void add(String key, T obj) {
		map.put(key, obj);

	}

	public void delByKey(String key) {
		map.remove(key);
	}

	public void clearAll() {
		map.clear();
	}

	public T getByKey(String key) {
		
		return map.get(key);
	}

	public boolean containKey(String key) {

		return map.containsKey(key);
	}

	public void add(String key, T obj, int timeout) {
		// TODO Auto-generated method stub

	}
}
