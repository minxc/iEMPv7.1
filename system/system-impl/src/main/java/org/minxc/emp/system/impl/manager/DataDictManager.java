package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.DataDict;

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
