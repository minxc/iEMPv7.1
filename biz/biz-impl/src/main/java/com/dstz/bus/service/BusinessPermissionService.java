package com.dstz.bus.service;

import com.alibaba.fastjson.JSONArray;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bus.api.constant.RightsType;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.service.IBusinessPermissionService;
import com.dstz.bus.manager.BusinessObjectManager;
import com.dstz.bus.manager.BusinessPermissionManager;
import com.dstz.bus.model.BusinessPermission;
import com.dstz.bus.model.permission.AbstractPermission;
import com.dstz.bus.model.permission.BusColumnPermission;
import com.dstz.bus.model.permission.BusObjPermission;
import com.dstz.bus.model.permission.BusTablePermission;
import com.dstz.sys.api2.permission.PermissionCalculatorFactory;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessPermissionService implements IBusinessPermissionService {
	@Autowired
	private BusinessPermissionManager T;
	@Autowired
	private BusinessObjectManager k;

	public BusinessPermission a(String objType, String objVal, String defalutBoKeys, boolean calculate) {
		BusinessPermission businessPermission = null;
		businessPermission = StringUtil.isNotEmpty((String) defalutBoKeys)
				? this.T.getByObjTypeAndObjVal(objType, objVal, defalutBoKeys)
				: this.T.getByObjTypeAndObjVal(objType, objVal);
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