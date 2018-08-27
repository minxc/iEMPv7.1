package org.minxc.emp.core.api.executor.validator;
/**  

* @Title: ExecutorValidator 

* @Package: org.minxc.emp.core.api.executor.validator 

* @Description:  TODO:执行器校验器

* @author: Xianchang.min  

* @date  2018/8/24 22:44 

* @version V1.0  

*/ 

public interface ExecutorValidator {

    //校验器key
    String getKey();
    //校验器名称
    String getName();

    /**
     * 校验执行器
     * @param executorKey  执行器KEY
     * @return
     */
    boolean validate(String executorKey);
}
