package org.minxc.emp.biz.core.dao;

import com.dstz.base.dao.BaseDao;

import org.minxc.emp.biz.core.model.BusColumnCtrl;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BusColumnCtrlDao extends BaseDao<String, BusColumnCtrl> {

	public void removeByTableId(String tableId);

	public BusColumnCtrl getByColumnId(String columnId);
}