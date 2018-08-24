
/**  

* @Title: ArrayUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 上午1:33:41 

* @version V1.0  

*/ 

package com.minxc.emp.core.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

/**      
* 项目名称：core-impl   
* 类名称：ArrayUtil   
* 类描述： 数组工具类  
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 上午1:33:41   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 上午1:33:41   
* 修改备注：   
* @version  1.0  
**/

public class ArrayUtil {
	
	 private transient static final String DEFAULT_JOIN_STR = ",";

	    /**
	     * 连接字符串 如：[1,2,3,4]，将会返回"1,2,3,4"
	     *
	     * @param arr
	     *            an array of {@link java.lang.Object} objects.
	     * @return a {@link java.lang.String} object.
	     */
	    public static final String join(final Object[] arr) {
	        return join(arr, DEFAULT_JOIN_STR);
	    }

	    /**
	     * 连接字符串 如: join([1,2,3,4],"x"]) 将会返回"1x2x3x4"
	     *
	     * @param arr
	     *            an array of {@link java.lang.Object} objects.
	     * @param joinStr
	     *            a {@link java.lang.String} object.
	     * @return a {@link java.lang.String} object.
	     */
	    public static final String join(final Object[] arr, final String joinStr) {
	        if (arr == null || arr.length < 1) {
	            return StringUtils.EMPTY;
	        }
	        final StringBuffer sb = new StringBuffer(String.valueOf(arr[0]));
	        for (int i = 1, len = arr.length; i < len; i++) {
	            sb.append(StringUtils.isNotEmpty(joinStr) ? joinStr : StringUtils.EMPTY).append(String.valueOf(arr[i]));
	        }
	        return sb.toString();
	    }

	    /**
	 * 集合转数组
	 *
	 * @param collection 集合
	 * @param clazz      类型
	 * @return 数组
	 * @param <T> a T object.
	 */
	    @SuppressWarnings("unchecked")
	    public static final <T> T[] toArray(final Collection<T> collection, final Class<T> clazz) {
	        if (collection == null) {
	            return null;
	        }
	        final T[] arr = (T[]) Array.newInstance(clazz, collection.size());
	        return collection.toArray(arr);
	    }

	    /**
	     * 集合转对象数组
	     *
	     * @param collection
	     *            集合
	     * @return 对象数组
	     */
	    public static final Object[] toObjectArray(final Collection<?> collection) {
	        if (collection == null) {
	            return null;
	        }
	        final Object[] arr = (Object[]) Array.newInstance(Object.class, collection.size());
	        int i = 0;
	        for (Iterator<?> it = collection.iterator(); it.hasNext();) {
	            arr[i++] = it.next();
	        }
	        return arr;
	    }
	    
	  /**
	   * 
	  
	  * @Title: isEmpty 
	  
	  * @Description: 判断数组是否为空
	  
	  * @param @param array
	  * @param @return    设定文件 
	  
	  * @return boolean    返回类型 
	  
	  * @throws
	   */
	    public static boolean isEmpty(Object[] array) {
	        return array == null || array.length == 0;
	    }
	    
	    
}
