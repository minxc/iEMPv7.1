package com.minxc.emp.core.impl.executor.validator;

import org.minxc.emp.core.api.executor.validator.ExecutorValidator;

/**
 * 执行器的校验者的抽象类
 *
 * @author min.xianchang
 */
public abstract class AbstractExecutorValidator implements ExecutorValidator {
    /**
     * <pre>
     * 校验器的key
     * 默认是类名
     * </pre>
     *
     * @return
     */
    @Override
    public String getKey() {
        return this.getClass().getSimpleName();
    }
}
