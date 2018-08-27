package org.minxc.emp.core.api.aop.annotation;

import java.lang.annotation.*;

/**

* @Title: ErrorCatching 

* @Package: org.minxc.emp.core.api.aop.annotation 

* @Description:  TODO:(用一句话描述该文件做什么) 

* @author: Xianchang.min  

* @date  2018/8/24 22:28 

* @version V1.0  

*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorCatching {

    String value() default "";
    String name() default "";
    boolean writeErrorToResponse() default false;
}
