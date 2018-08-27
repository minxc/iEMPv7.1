package org.minxc.emp.system.impl.permission.impl;

import java.util.List;
import java.util.Map;

import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.system.api.permission.IPermissionCalculator;

import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.ThreadMapUtil;

import ch.qos.logback.core.util.ContextUtil;

/**
 * 描述：组 抽象类
 */
public abstract class GroupPermissionCalculator implements IPermissionCalculator {
	/**
	 * 线程变量ThreadMapUtil中关于当前权限解析器的线程变量
	 */
	private static String threadMapKey = "com.dstz.sys.permission.impl.GroupPermission";

	@Override
	public boolean haveRights(JSONObject json) {
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
			if (json.getString("id").contains(group.getGroupId())) {
				return true;
			}
		}

		return false;
	}

}
