package org.minxc.emp.system.impl.scheduler;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.system.impl.model.SysScheduleJob;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import com.minxc.emp.core.util.AppContextUtil;

import groovy.util.logging.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 任务执行工具
 */
@Slf4j
public class JobInvokeUtil {


    /**
     * 执行方法
     *
     * @param sysScheduleJob 系统任务
     */
    public static void invokeMethod(SysScheduleJob sysScheduleJob) throws Exception {
        final String invokeTarget = sysScheduleJob.getInvokeTarget();
        Assert.notNull(invokeTarget, "执行目标方法为空");
        //如果类名超过两个调用视为静态方法
        if (StringUtils.countMatches(invokeTarget, ".") > 2) {
            invokeStaticMethod(invokeTarget);
        } else {
            invokeSpringBean(invokeTarget);
        }
    }

    /**
     * 调用spring bean方法
     *
     * @param invokeTarget 目标调用
     */
    private static void invokeSpringBean(String invokeTarget) throws IllegalAccessException, InvocationTargetException {
        final String beanId = invokeTarget.substring(0, invokeTarget.indexOf("."));
        final String methodName = invokeTarget.substring(beanId.length() + 1);
        Object bean = AppContextUtil.getApplicaitonContext().getBean(beanId);
        Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(bean.getClass(), methodName);
        method.invoke(bean);
    }


    /**
     * 调用静态方法
     *
     * @param invokeTarget 调用目标
     */
    private static void invokeStaticMethod(String invokeTarget) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        String className = invokeTarget.substring(0, invokeTarget.lastIndexOf("."));
        String methodName = invokeTarget.substring(className.length() + 1);
        Class<?> clazz = Class.forName(className);
        Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(clazz, methodName);
        method.invoke(null);
    }
}
