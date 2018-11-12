package org.minxc.emp.biz.api.model;

import java.util.List;

/**
 * 
 * BusTableRel接口类
 * 
 * 
 * 日期:2018年3月26日 下午5:29:16
 * 
 * 
 */
public interface IBusTableRel {
	/**
	 * 
	 * 子级
	 * 
	 * 
	 * @return
	 */
	List<? extends IBusTableRel> getChildren();

	/**
	 * 
	 * 业务表的key
	 * 
	 * 
	 * @return
	 */
	String getTableKey();

	/**
	 * 
	 * 业务表的描述
	 * 
	 * 
	 * @return
	 */
	String getTableComment();

	/**
	 * 
	 * 类型 枚举 BusTableRelType
	 * 
	 * 
	 * @return
	 */
	String getType();

	/**
	 * 
	 * 外键设置
	 * 
	 * 
	 * @return
	 */
	List<? extends IBusTableRelFk> getFks();

	/**
	 * 
	 * 获取busTableRel的list模式
	 * 包含根节点
	 * 
	 * 
	 * @return
	 */
	List<? extends IBusTableRel> list();

	IBusinessTable getTable();
	
	/**
	 * 
	 * 以当前为根节点，递归获取指定tableKey
	 * 
	 * 
	 * @param tableKey
	 * @return
	 */
	IBusTableRel find(String tableKey);

	IBusTableRel getParent();

	IBusinessObject getBusObj();

	List<IBusTableRel> getChildren(String type);
}
