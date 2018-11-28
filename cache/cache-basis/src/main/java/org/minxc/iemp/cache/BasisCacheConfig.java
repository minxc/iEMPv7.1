package org.minxc.iemp.cache;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BasisCacheConfig {

//	@Bean(name="basisCacheManager")
//	public CacheManager cacheManager() {
//		CacheManager cacheManager  = new CaffeineCacheManager();
//		Caffeine<String, UserDetails> springSecurityCache  =  Caffeine.newBuilder().expireAfterAccess(24*60*60, TimeUnit.SECONDS).maximumSize(10000).build();
//		List<String> caheNames = new ArrayList<>();
//		caheNames.add("SPRING_SECURITY_CACHE");
//		return cacheManager;
//	}
	
}