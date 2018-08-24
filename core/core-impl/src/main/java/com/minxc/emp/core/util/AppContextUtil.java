
/**  

* @Title: ApplicationContextUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 上午12:39:36 

* @version V1.0  

*/ 

package com.minxc.emp.core.util;

import com.google.common.collect.Lists;
import org.minxc.emp.core.api.exception.BusinessException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**      
* 项目名称：core-impl   
* 类名称：ApplicationContextUtil   
* 类描述：系统进行上下文查找Bean工具类
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 上午12:39:36   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 上午12:39:36   
* 修改备注：   
* @version  1.0  
**/

@Slf4j
public class AppContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext _context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AppContextUtil._context = applicationContext;
	}

	
	/**
     * 获取spring容器上下文。
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicaitonContext() {
        return _context;
    }

    /**
     * 根据beanId获取配置在系统中的对象实例。
     *
     * @param beanId
     * @return Object
     * @throws
     * @since 1.0.0
     */
    public static Object getBean(String beanId) {
        try {
            return _context.getBean(beanId);
        } catch (Exception ex) {
            log.debug("getBean:" + beanId + "," + ex.getMessage());
        }
        return null;
    }

    /**
     * 获取Spring容器的Bean
     *
     * @param beanClass
     * @return T
     * @throws
     * @since 1.0.0
     */
    public static <T> T getBean(Class<T> beanClass) {
        try {
            return _context.getBean(beanClass);
        } catch (Exception ex) {

            log.debug("getBean:" + beanClass + "," + ex.getMessage());
        }
        return null;
    }

    /**
     * 根据指定的接口或基类获取实现类列表。
     *
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class> getImplClass(Class clazz) throws ClassNotFoundException {

        List<Class> list = Lists.newArrayList();

        Map map = _context.getBeansOfType(clazz);
        for (Object obj : map.values()) {
            String name = obj.getClass().getName();
            int pos = name.indexOf("$$");
            if (pos > 0) {
                name = name.substring(0, name.indexOf("$$"));
            }
            Class cls = Class.forName(name);

            list.add(cls);
        }
        return list;
    }


    /**
     * 获取接口的实现类实例。
     *
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> getImplInstance(Class<T> clazz){
        return _context.getBeansOfType(clazz);
    }
    
    public static <T> List<T> getImplInstanceArray(Class<T> clazz){
    	List<T> list = new ArrayList<>();
    	
    	Map<String,T> map = _context.getBeansOfType(clazz);
    	
    	for (T t : map.values()) {
    		list.add(t);
		}
    	return list;
    }

//    /**
//     * 获取系统中的ICache实现类。
//     *
//     * @return
//     */
//    public static Cache getCache() {
//        return (Cache) getBean("iCache");
//    }


    /**
     * 发布事件。
     *
     * @param event void
     */
    public static void publishEvent(ApplicationEvent event) {
        if (_context != null) {
            _context.publishEvent(event);
        }
    }


    /**
     * 获取当前系统环境<br>
     * 目前仅支持单一环境配置。请勿配置多个参数 dev sit 之类。 环境配置的判断参考下面代码<br>
     * doGetActiveProfiles().contains(profile) || (doGetActiveProfiles().isEmpty() && doGetDefaultProfiles().contains(profile))
     *
     * @return
     */
    private static String currentProfiles = null;

    public static String getCtxEnvironment() {
        if (currentProfiles != null) {
            return currentProfiles;
        }

        Environment environment = _context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();

        if (!ArrayUtil.isEmpty(activeProfiles)) {
            currentProfiles = activeProfiles[0];
            return currentProfiles;
        }

        String[] defaultProfiles = environment.getDefaultProfiles();
        if (!ArrayUtil.isEmpty(defaultProfiles)) {
            currentProfiles = defaultProfiles[0];
            return defaultProfiles[0];
        }

        throw new BusinessException("查找不到正确的环境属性配置！", BaseStatusCode.SYSTEM_ERROR);
    }
}
