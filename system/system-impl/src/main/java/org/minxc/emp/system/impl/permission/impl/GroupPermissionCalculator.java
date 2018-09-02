package org.minxc.emp.system.impl.permission.impl;

import java.util.List;
import java.util.Map;

import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.system.api.permission.IPermissionCalculator;
import org.minxc.emp.system.util.ContextUtil;

//import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.ThreadMapUtil;


/**
 * 描述：组 抽象类
 */
public abstract class GroupPermissionCalculator implements IPermissionCalculator {
	
	/**
	 * 线程变量ThreadMapUtil中关于当前权限解析器的线程变量
	 */
	private static String threadMapKey = " org.minxc.emp.system.impl.permission.impl.GroupPermission";

//	@Override
//	public boolean haveRights(JSONObject json) {
//		Map<String, List<Group>> allGroup = (Map<String, List<Group>>) ThreadMapUtil.get(threadMapKey);
//		if (allGroup == null) {
//			GroupService groupService = AppContextUtil.getBean(GroupService.class);
//			allGroup = groupService.getAllGroupByUserId(ContextUtil.getCurrentUserId());
//			ThreadMapUtil.put(threadMapKey, allGroup);
//		}
//
//		List<Group> groups;
//		if ("post".equals(this.getType())) {// 岗位的命名不一致
//			groups = allGroup.get("position");
//		} else {
//			groups = allGroup.get(this.getType());
//		}
//
//		for (Group group : groups) {
//			if (json.getString("id").contains(group.getGroupId())) {
//				return true;
//			}
//		}
//
//		return false;
//	}
	
	
	@Override
	public boolean haveRights(JsonNode json) {
		
		Map<String, List<Group>> allGroup = (Map<String, List<Group>>) ThreadMapUtil.get(threadMapKey);
		if (allGroup == null) {
			GroupService groupService = AppContextUtil.getBean(GroupService.class);
			allGroup = groupService.getAllGroupByUserId(ContextUtil.getCurrentUserId());
			ThreadMapUtil.put(threadMapKey, allGroup);
		}
		
		List<Group> groups;
		if ("post".equals(this.getType())) {// 岗位的命名不一致
			groups = allGroup.get("position");
		} else {
			groups = allGroup.get(this.getType());
		}
		
		for (Group group : groups) {
			JsonNode node  = json.get("id");
			if (json.get("id").contains(group.getGroupId())) {
				return true;
			}
		}
		
		return false;
	}

}
