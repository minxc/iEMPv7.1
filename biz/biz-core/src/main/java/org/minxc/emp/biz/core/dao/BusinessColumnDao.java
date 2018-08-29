package org.minxc.emp.biz.core.dao;

import com.dstz.base.dao.BaseDao;

import java.util.List;

import org.minxc.emp.biz.core.model.BusinessColumn;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusinessColumnDao extends BaseDao<String, BusinessColumn> {
	public void removeByTableId(String tableId);

	public List<BusinessColumn> getByTableId(String tableId);
}