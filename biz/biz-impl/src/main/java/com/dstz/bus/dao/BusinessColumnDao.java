package com.dstz.bus.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bus.model.BusinessColumn;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessColumnDao extends BaseDao<String, BusinessColumn> {
	public void removeByTableId(String tableId);

	public List<BusinessColumn> getByTableId(String tableId);
}