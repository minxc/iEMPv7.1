package org.minxc.emp.biz.core.util;


import java.util.HashMap;
import java.util.Map;

import org.minxc.emp.biz.core.model.BusinessTableImpl;
import org.minxc.emp.core.util.AppContextUtil;

public class BusinessTableCacheUtil {
	private static String U = "businessTableMap";

	private BusinessTableCacheUtil() {
	}

	public static void put(BusinessTableImpl businessTable) {
		HashMap<String, BusinessTableImpl> map = (HashMap<String, BusinessTableImpl>) AppContextUtil.getCache().getByKey(U);
		if (map == null) {
			map = new HashMap<String, BusinessTableImpl>();
		}
		map.put(businessTable.getKey(), businessTable);
		AppContextUtil.getCache().add(U, map);
	}

	public static BusinessTableImpl get(String key) {
		Map map = (Map) AppContextUtil.getCache().getByKey(U);
		if (map == null) {
			return null;
		}
		return (BusinessTableImpl) map.get(key);
	}
}