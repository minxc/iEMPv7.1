package org.minxc.emp.biz.api.model.permission;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public interface AbstractPermission extends Serializable{

	String getResult();

	Map<String, JSONArray> getRights();

}
