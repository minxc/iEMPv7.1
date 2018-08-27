package org.minxc.emp.basis.impl.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.basis.impl.core.model.DataDict;
import org.minxc.emp.common.db.dao.CommonDao;


/**
 * 数据字典 DAO接口
 * 
 * @time 2018-05-16 14:39:58
 */


public interface DataDictDao extends CommonDao<String, DataDict> {

	/**
	 * 通过dicKey获取字典项。若hasRoot则包含字典本身
	 * 
	 * @param dictKey
	 * @param hasRoot
	 * @return
	 */
	List<DataDict> getDictNodeList(@Param("dictKey") String dictKey, @Param("hasRoot") Boolean hasRoot);

	/**
	 * 判断字典是否存在，
	 * 
	 * @param dictKey
	 * @param id      若不为null，则排除id进行判断是否存在。用于更新时
	 * @return
	 */
	Integer isExistDict(@Param("key") String key, @Param("id") String id);

	/**
	 * 判断字典项是否存在
	 * 
	 * @param dictKey
	 * @param key
	 * @param id      id 若不为null，则排除id进行判断是否存在。用于更新时
	 * @return
	 */
	Integer isExistNode(@Param("dictKey") String dictKey, @Param("key") String key, @Param("id") String id);

}
