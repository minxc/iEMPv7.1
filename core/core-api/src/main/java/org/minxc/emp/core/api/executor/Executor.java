package org.minxc.emp.core.api.executor;

/**
 * @version V1.0
 * @Title: Excecutor
 * @Package: org.minxc.emp.core.api.executor
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/24 22:33
 */

public interface Executor<T> extends Comparable<Executor<T>> {

    /**
     * 执行器key
     */
    String getKey();

    /**
     * 执行器名称
     */
    String getName();

    /**
     * 返回这个执行器的类型key
     */
    String getType();

    /**
     * 返回校验器的别名
     * 多个以,分隔，eg：a,b,c,...
     */
    String getValidatorKey();

    /**
     * 序号
     * 用于某些执行器有先后顺序的
     */
    int getSequanceNum();

    /**
     * 运行这个执行器
     * 运行前要通过校验
     *
     * @param param 运行的参数
     */
    void execute(T param);

}
