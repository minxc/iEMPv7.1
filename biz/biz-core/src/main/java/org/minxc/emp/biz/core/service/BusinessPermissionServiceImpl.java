package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.IBusinessPermission;
import org.minxc.emp.biz.api.service.BusinessPermissionService;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessPermissionManager;
import org.minxc.emp.biz.core.model.BusinessPermission;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusColumnPermission;
import org.minxc.emp.biz.core.model.permission.BusObjPermission;
import org.minxc.emp.biz.core.model.permission.BusTablePermission;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.system.api.permission.PermissionCalculatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessPermissionServiceImpl implements BusinessPermissionService {
	@Autowired
	private BusinessPermissionManager businessPermissionManager;
	@Autowired
	private BusinessObjectManager businessObjectManager;

	public BusinessPermission a(String objType, String objVal, String defalutBoKeys, boolean calculate) {
		BusinessPermission businessPermission = null;
		businessPermission = StringUtils.isNotEmpty((String) defalutBoKeys)
				? this.businessPermissionManager.getByObjTypeAndObjVal(objType, objVal, defalutBoKeys)
				: this.businessPermissionManager.getByObjTypeAndObjVal(objType, objVal);
		if (businessPermission == null) {
			return new BusinessPermission();
		}
		if (calculate) {
			this.a(businessPermission);
		}
		return businessPermission;
	}

	private void a(BusinessPermission businessPermission) {
		for (Map.Entry entry : businessPermission.getBusObjMap().entrySet()) {
			BusObjPermission busObjPermission = (BusObjPermission) entry.getValue();
			this.b((AbstractPermission) busObjPermission);
			for (Map.Entry etry : busObjPermission.getTableMap().entrySet()) {
				BusTablePermission busTablePermission = (BusTablePermission) etry.getValue();
				if (BeanUtils.isEmpty((Object) busTablePermission.getRights())) {
					busTablePermission.setResult(busObjPermission.getResult());
				} else {
					this.b((AbstractPermission) busTablePermission);
				}
				for (Map.Entry ery : busTablePermission.getColumnMap().entrySet()) {
					BusColumnPermission busColumnPermission = (BusColumnPermission) ery.getValue();
					if (BeanUtils.isEmpty((Object) busColumnPermission.getRights())) {
						busColumnPermission.setResult(busTablePermission.getResult());
						continue;
					}
					this.b((AbstractPermission) busColumnPermission);
				}
			}
		}
	}

	private void b(AbstractPermission permission) {
		for (RightsType rightsType : RightsType.values()) {
			JSONArray jsonArray = (JSONArray) permission.getRights().get(rightsType.getKey());
			boolean b = PermissionCalculatorFactory.haveRights((JSONArray) jsonArray);
			if (!b)
				continue;
			permission.setResult(rightsType.getKey());
			break;
		}
		if (StringUtil.isEmpty((String) permission.getResult())) {
			permission.setResult(RightsType.values()[RightsType.values().length - 1].getKey());
		}
	}

	public IBusinessPermission getByObjTypeAndObjVal(String string, String string2, String string3, boolean bl) {
		return this.a(string, string2, string3, bl);
	}
}