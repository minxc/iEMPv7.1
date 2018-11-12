package org.minxc.emp.bpm.api.engine.plugin.runtime;


/**
 * 插件运行时接口定义
 *
 * @param <S> session
 * @param <M> modelDef
 * @param <R> return
 * @author xianchang.min
 */
public interface RunTimePlugin<S, M, R> {

    R execute(S pluginSession, M pluginDef);
}
