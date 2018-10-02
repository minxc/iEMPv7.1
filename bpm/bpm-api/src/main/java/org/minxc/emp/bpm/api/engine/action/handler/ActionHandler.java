package org.minxc.emp.bpm.api.engine.action.handler;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;

/**
 * 动作执行处理器
 */
public interface ActionHandler<T extends ActionCmd> {
	/**
	 * 执行入口
	 */
	void execute(T model);

	/**
	 * 定义动作的key name beanId
	 *
	 */
	ActionType getActionType();

	// ========生成按钮配置项========

	/**
	 * 排序
	 *
	 * @return
	 */
	int getSn();

	/**
	 * 判断是否支持改任务类型
	 *
	 * @param nodeDef
	 * @return
	 */
	Boolean isSupport(BpmNodeDef nodeDef);

	/**
	 * 是否默认展示
	 *
	 * @return
	 */
	Boolean isDefault();

	/**
	 * 该action特殊配置获取页
	 *
	 * @return
	 */
	String getConfigPage();

	/**
	 * 默认groovy脚本
	 *
	 * @return
	 */
	String getDefaultGroovyScript();

	/**
	 * 默认前置脚本
	 *
	 * @return
	 */
	String getDefaultBeforeScript();
}
