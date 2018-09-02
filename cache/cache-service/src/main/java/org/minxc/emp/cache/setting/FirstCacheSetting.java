package org.minxc.emp.cache.setting;

import com.github.benmanes.caffeine.cache.CaffeineSpec;

/**
 * 
* 项目名称：cache-service   
* 类名称：FirstCacheSetting   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午7:33:45   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午7:33:45   
* 修改备注：   
* @version  1.0  
*
 */
public class FirstCacheSetting {

    /**
     * 一级缓存配置，配置项请点击这里 {@link CaffeineSpec#configure(String, String)}
     * @param cacheSpecification
     */
    public FirstCacheSetting(String cacheSpecification) {
        this.cacheSpecification = cacheSpecification;
    }

    private String cacheSpecification;

    public String getCacheSpecification() {
        return cacheSpecification;
    }
}
