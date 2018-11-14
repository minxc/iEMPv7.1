package org.minxc.emp.biz.api.model.permission;

import java.util.Map;

public interface BusinessTablePermission extends AbstractPermission {

	String getKey();

	String getComment();

	Map<String, ? extends BusinessColumnPermission> getColumnMap();

}
