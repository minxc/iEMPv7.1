package org.minxc.emp.core.api.aop.annotation;

import java.lang.annotation.*;

/**

* @Title: ParameterValidation 

* @Package: org.minxc.emp.core.api.aop.annotation 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/24 22:29 

* @version V1.0  

*/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParameterValidation {

    String value() default "";

}
