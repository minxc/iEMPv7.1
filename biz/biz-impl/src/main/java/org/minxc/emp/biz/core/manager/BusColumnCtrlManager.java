package org.minxc.emp.biz.core.manager;

import org.minxc.emp.biz.core.model.BusColumnCtrl;

import com.dstz.base.manager.Manager;

/**
 * 
*    
* 项目名称：bus-core   
* 类名称：BusColumnCtrlManager   
* 类描述：   列控制组件管理器（服务类）
* 创建人：Xianchang.min   
* 创建时间：2018年8月11日 上午12:21:13   
* 修改人：Xianchang.min   
* 修改时间：2018年8月11日 上午12:21:13   
* 修改备注：   
* @version  1.0  
*
 */
public interface BusColumnCtrlManager extends Manager<String, BusColumnCtrl> {
	public void removeByTableId(String tableId);

	public BusColumnCtrl getByColumnId(String columnId);
}