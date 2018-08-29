package org.minxc.emp.biz.api.model.permission;

import java.util.Map;

public interface IBusObjPermission extends IAbstractPermission {

	String getKey();

	String getName();

	Map<String, ? extends IBusTablePermission> getTableMap();

}
