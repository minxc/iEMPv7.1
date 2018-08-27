package com.minxc.emp.core.impl.executor;


import org.minxc.emp.core.api.executor.ExecutorChain;

/**
 * 抽象的执行链
 * 这里只是作为一种子类会有多个实现的标记
 * 具体看例子吧-。-
 *
 * @param <T>
 * @author min.xianchang
 */
public abstract class AbstractExecuteChain<T> extends AbstractExecutor<T> implements ExecutorChain<T> {

}
