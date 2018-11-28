package org.minxc.emp.security.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BasisSecurityCacheConfig {
	
	/**
	 * 配置Caffeine缓存
	 * @return
	 */
	@Bean(name="securityCacheManager")
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager  = new CaffeineCacheManager();
//		Caffeine<Object, Object> springSecurityCache  =  Caffeine.newBuilder().expireAfterAccess(24*60*60, TimeUnit.SECONDS).maximumSize(10000).build();
//		List<String> caheNames = new ArrayList<>();
//		caheNames.add("SPRING_SECURITY_CACHE");
//		cacheManager.setCaffeine(springSecurityCache);
//		cacheManager.setCaffeine(springSecurityCache);
		List<String> cacheNames  = new ArrayList<String>(8);
		cacheNames.add("SPRING_SECURITY_CACHE");
		cacheManager.setCacheNames(cacheNames);
		return cacheManager;
	}
}
