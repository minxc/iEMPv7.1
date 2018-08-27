package org.minxc.emp.basis.impl.core.manager;

import java.util.List;

import org.minxc.emp.basis.impl.core.model.DataDict;
import org.minxc.emp.common.manager.Manager;

//import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 数据字典 Manager处理接口
 * 
 * @time 2018-05-16 14:39:58
 */
public interface DataDictManager extends Manager<String, DataDict> {

	List<DataDict> getDictNodeList(String dictKey, Boolean hasRoot);

//	JSONArray getDictTree();  更改承载类为Jackson 
	ArrayNode getDictTree();

}
