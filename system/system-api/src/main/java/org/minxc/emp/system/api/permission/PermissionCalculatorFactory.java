package org.minxc.emp.system.api.permission;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.minxc.emp.core.api.exception.BusinessException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.util.AppContextUtil;

/**
 * 描述：权限的工厂类
 */

public class PermissionCalculatorFactory {
	/**
	 * Map<type,permission>
	 */
	private static Map<String, IPermissionCalculator> permissionMap;

	private PermissionCalculatorFactory() {

	}

	private static Map<String, IPermissionCalculator> permissionMap() {
		if (permissionMap == null) {
			permissionMap = new HashMap<>();
			for (Entry<String, IPermissionCalculator> entry : AppContextUtil.getImplInstance(IPermissionCalculator.class).entrySet()) {
				permissionMap.put(entry.getValue().getType(), entry.getValue());
			}
		}
		return permissionMap;
	}

	/**
	 * <pre>
	 * 判断当前用户是否有这权限串
	 * </pre>
	 * 
	 * @param jsonArray
	 *            :[{type:"user",id:"a,b,.."},{type:"org",id:"a,b,..."}]
	 * @return
	 */
//	public static boolean haveRights(JSONArray jsonArray) {
//		if(jsonArray==null) {
//			return false;
//		}
//		for (Object obj : jsonArray) {
//			JSONObject json = (JSONObject) obj;
//			IPermissionCalculator permission = permissionMap().get(json.getString("type"));
//			if(permission==null) {
//				throw new BusinessException("权限类型["+json.getString("type")+"]找不到处理器");
//			}
//			if (permission.haveRights(json)) {// 有一个满足就true
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	public static boolean haveRights(JsonNode jsonArray) {
		
		if(jsonArray==null) {
			return false;
		}
		Iterator<JsonNode> iter = jsonArray.elements();
		
		while(iter.hasNext()) {
			JsonNode node  = iter.next();
			IPermissionCalculator permission = permissionMap().get(node.get("type"));
			if(permission==null) {
				throw new BusinessException("权限类型["+ node.get("type")+"]找不到处理器");
			}
			if (permission.haveRights(node)) {// 有一个满足就true
				return true;
			}
		}
		
		return false;
		
		
//		for (Object obj : jsonArray) {
//			JSONObject json = (JSONObject) obj;
//			IPermissionCalculator permission = permissionMap().get(json.getString("type"));
//			if(permission==null) {
//				throw new BusinessException("权限类型["+json.getString("type")+"]找不到处理器");
//			}
//			if (permission.haveRights(json)) {// 有一个满足就true
//				return true;
//			}
//		}
//		return false;
	}
}
