package org.minxc.emp.biz.core.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.constant.RightsType;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessPermissionService;
import org.minxc.emp.biz.core.manager.BusinessObjectManager;
import org.minxc.emp.biz.core.manager.BusinessPermissionManager;
import org.minxc.emp.biz.core.model.BusinessPermissionImpl;
import org.minxc.emp.biz.core.model.permission.AbstractPermission;
import org.minxc.emp.biz.core.model.permission.BusinessColumnPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessObjectPermissionImpl;
import org.minxc.emp.biz.core.model.permission.BusinessTablePermissionImpl;
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

	public BusinessPermissionImpl a(String objType, String objVal, String defalutBoKeys, boolean calculate) {
		BusinessPermissionImpl businessPermission = null;
		businessPermission = StringUtils.isNotEmpty((String) defalutBoKeys)
				? this.businessPermissionManager.getByObjTypeAndObjVal(objType, objVal, defalutBoKeys)
				: this.businessPermissionManager.getByObjTypeAndObjVal(objType, objVal);
		if (businessPermission == null) {
			return new BusinessPermissionImpl();
		}
		if (calculate) {
			this.a(businessPermission);
		}
		return businessPermission;
	}

	private void a(BusinessPermissionImpl businessPermission) {
		for (Map.Entry entry : businessPermission.getBusinessObjectMap().entrySet()) {
			BusinessObjectPermissionImpl busObjPermission = (BusinessObjectPermissionImpl) entry.getValue();
			this.b((AbstractPermission) busObjPermission);
			for (Map.Entry etry : busObjPermission.getTableMap().entrySet()) {
				BusinessTablePermissionImpl busTablePermission = (BusinessTablePermissionImpl) etry.getValue();
				if (BeanUtils.isEmpty((Object) busTablePermission.getRights())) {
					busTablePermission.setResult(busObjPermission.getResult());
				} else {
					this.b((AbstractPermission) busTablePermission);
				}
				for (Map.Entry ery : busTablePermission.getColumnMap().entrySet()) {
					BusinessColumnPermissionImpl busColumnPermission = (BusinessColumnPermissionImpl) ery.getValue();
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

	public BusinessPermission getByObjTypeAndObjVal(String string, String string2, String string3, boolean bl) {
		return this.a(string, string2, string3, bl);
	}
}