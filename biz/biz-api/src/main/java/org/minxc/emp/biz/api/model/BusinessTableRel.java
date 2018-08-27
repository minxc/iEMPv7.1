package org.minxc.emp.biz.api.model;

import java.util.List;

/**
 * <pre>
 * 描述：BusTableRel接口类
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月26日 下午5:29:16
 * 版权:summer
 * </pre>
 */
public interface BusinessTableRel {
	/**
	 * <pre>
	 * 子级
	 * </pre>
	 * 
	 * @return
	 */
	List<? extends BusinessTableRel> getChildren();

	/**
	 * <pre>
	 * 业务表的key
	 * </pre>
	 * 
	 * @return
	 */
	String getTableKey();

	/**
	 * <pre>
	 * 业务表的描述
	 * </pre>
	 * 
	 * @return
	 */
	String getTableComment();

	/**
	 * <pre>
	 * 类型 枚举 BusTableRelType
	 * </pre>
	 * 
	 * @return
	 */
	String getType();

	/**
	 * <pre>
	 * 外键设置
	 * </pre>
	 * 
	 * @return
	 */
	List<? extends BusinessTableRelForeignKey> getFks();

	/**
	 * <pre>
	 * 获取busTableRel的list模式
	 * 包含根节点
	 * </pre>
	 * 
	 * @return
	 */
	List<? extends BusinessTableRel> list();

	BusinessTable getTable();
	
	/**
	 * <pre>
	 * 以当前为根节点，递归获取指定tableKey
	 * </pre>
	 * 
	 * @param tableKey
	 * @return
	 */
	BusinessTableRel find(String tableKey);

	BusinessTableRel getParent();

	BusinessObject getBusObj();

	List<BusinessTableRel> getChildren(String type);
}
