package com.dstz.bus.service;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.AppUtil;
import com.dstz.bus.model.BusinessData;
import com.dstz.bus.model.BusinessObject;
import com.dstz.bus.service.BusinessDataPersistenceService;
import java.util.Map;

public class BusinessDataPersistenceServiceFactory {
	private BusinessDataPersistenceServiceFactory() {
	}

	private static BusinessDataPersistenceService getBusinessDataPersistenceServiceByType(String type) {
		Map<String, BusinessDataPersistenceService> map = AppUtil.getImplInstance(BusinessDataPersistenceService.class);
		
		
		for (Map.Entry<String, BusinessDataPersistenceService> entry : map.entrySet()) {
			if (!(entry.getValue().type().equals(type))) {
				continue;
			}else {
				return entry.getValue();
			}
		}
		throw new BusinessException(String.format("找不到类型[%s]的业务数据持久化的实现类", type));
	}

	public static void saveData(BusinessData businessData) {
		BusinessObject businessObject = (BusinessObject)businessData.getBusTableRel().getBusObj();
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.getBusinessDataPersistenceServiceByType(businessObject.getPersistenceType());
		businessDataPersistenceService.saveData(businessData);
	}

	public static BusinessData loadData(BusinessObject businessObject, Object id) {
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.getBusinessDataPersistenceServiceByType(businessObject.getPersistenceType());
		return businessDataPersistenceService.loadData(businessObject, id);
	}

	public static void removeData(BusinessObject businessObject, Object id) {
		BusinessDataPersistenceService businessDataPersistenceService = BusinessDataPersistenceServiceFactory
				.getBusinessDataPersistenceServiceByType(businessObject.getPersistenceType());
		businessDataPersistenceService.removeData(businessObject, id);
	}
}