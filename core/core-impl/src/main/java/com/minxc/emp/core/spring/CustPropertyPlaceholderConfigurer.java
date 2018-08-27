package com.minxc.emp.core.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @version V1.0
 * @Title: CustPropertyPlaceholderConfigurer
 * @Package: com.minxc.emp.core.spring
 * @Description: TODO:获取配置中的属性
 * @author: Xianchang.min
 * @date 2018/8/26 9:34
 */

public class CustPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 属性key,value
     */
    private static Map<String, String> properties;

    public CustPropertyPlaceholderConfigurer() {
        properties = new HashMap<String, String>();
    }

    /**
     *
     */
    protected void convertProperties(Properties props) {
        Set<String> keys = props.stringPropertyNames();
        for (String key : keys) {
            String value = props.getProperty(key);
            properties.put(key, value);
        }
        super.convertProperties(props);
    }

    /**
     * 根据建获取属性中的值。
     */
    public String getValue(String key) {
        return properties.get(key);
    }

}
