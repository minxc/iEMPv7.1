/**
 * 
 */
package org.minxc.emp.core.cache;


/**      
* 项目名称：core-cache   
* 类名称：Cache   
* 类描述： 缓存操作接口   定义了增加缓存，删除缓存，清除缓存，读取缓存接口
* 创建人：Xianchang.min   
* 创建时间：2018年10月1日 下午2:51:55   
* 修改人：Xianchang.min   
* 修改时间：2018年10月1日 下午2:51:55   
* 修改备注：   
* @version  1.0  
**/
public interface Cache <T extends Object> {
	
	 /**
     * 添加缓存。
     *
     * @param key
     * @param obj
     * @param timeout
     */
    void add(String key, T obj, int timeout);

    /**
     * 添加缓存。
     *
     * @param key
     * @param obj
     */
    void add(String key, T obj);

    /**
     * 根据键删除缓存
     *
     * @param key
     */
    void delByKey(String key);

    /**
     * 清除所有的缓存
     */
    void clearAll();

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    T getByKey(String key);

    /**
     * 包含key。
     *
     * @param key
     * @return
     */
    boolean containKey(String key);
}
