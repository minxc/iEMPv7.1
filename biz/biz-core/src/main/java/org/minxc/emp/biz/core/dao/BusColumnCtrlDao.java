package org.minxc.emp.biz.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.biz.core.model.BusColumnCtrl;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BusColumnCtrlDao extends CommonDao<String, BusColumnCtrl> {

	public void removeByTableId(String tableId);

	public BusColumnCtrl getByColumnId(String columnId);
}