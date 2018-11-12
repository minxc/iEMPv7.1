package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.DataDictEntity;

import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 
* 项目名称：system-impl   
* 类名称：DataDictManager   
* 类 数据字典 Manager处理接口  
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午5:37:57   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午5:37:57   
* 修改备注：   
* @version  1.0  
*
 */
public interface DataDictManager extends Manager<String, DataDictEntity> {

	List<DataDictEntity> getDictNodeList(String dictKey, Boolean hasRoot);

	JSONArray getDictTree();  

}
