package org.minxc.emp.core.impl.executor;

import org.minxc.emp.core.api.executor.Executor;
import org.minxc.emp.core.api.executor.ExecutorType;
import org.minxc.emp.core.api.executor.validator.ExecutorValidator;
import org.minxc.emp.core.impl.executor.validator.ExecutorValidatorService;
import org.minxc.emp.core.impl.executor.validator.FreeExecutorValidator;

import java.util.List;

/**
 * 执行器的抽象类
 *
 * @param <T>
 * @author min.xianchang
 */
public abstract class AbstractExecutor<T> implements Executor<T> {
    /**
     * <pre>
     * 执行器的key
     * </pre>
     *
     * @return
     */
    @Override
    public String getKey() {
        return this.getClass().getSimpleName();
    }

    /**
     * <pre>
     * 执行器的名称
     * </pre>
     *
     * @return
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int compareTo(Executor<T> executor) {
        return Integer.valueOf(this.getSequanceNum()).compareTo(Integer.valueOf(executor.getSequanceNum()));
    }
    
    @Override
	public String getType() {
    	//默认是必要执行器
		return ExecutorType.MUST.getKey();
	}

	@Override
	public String getValidatorKey() {
		//默认免费
		return FreeExecutorValidator.class.getSimpleName();
	}
    
    /**
     * <pre>
     * 运行这个执行器
     * </pre>
     *
     * @param param
     */
    @Override
    public void execute(T param) {
        //1 先校验权限
        List<ExecutorValidator> checkers = ExecutorValidatorService.getCheckers(this.getValidatorKey());
        for (ExecutorValidator checker : checkers) {
            if (!checker.validate(getKey())) {//一个不满足就结束
                return;
            }
        }
        this.run(param);//2 运行执行器
    }

    /**
     * <pre>
     * 子类中运行这个执行器
     * </pre>
     *
     * @param param 运行的参数
     */
    protected abstract void run(T param);
}
