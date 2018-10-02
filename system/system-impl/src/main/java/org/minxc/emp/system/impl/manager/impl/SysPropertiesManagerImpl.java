package org.minxc.emp.system.impl.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.api.constant.EnvironmentConstant;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.system.api.service.PropertyService;
import org.minxc.emp.system.impl.dao.SysPropertiesDao;
import org.minxc.emp.system.impl.manager.SysPropertiesManager;
import org.minxc.emp.system.impl.model.SystemPropertiesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 描述：SYS_PROPERTIES 处理实现类
 * </pre>
 */

@Slf4j
@Service("sysPropertiesManager")
public class SysPropertiesManagerImpl extends CommonManager<String, SystemPropertiesEntity>
		implements SysPropertiesManager, PropertyService {

	@Resource
	private SysPropertiesDao sysPropertiesDao;
	
	// TODO:需要添加缓存支持
//    @Resource
//    ICache cache;

	private static final String PROPERTIES_CACHE_KEY = "PROPERTIES_CACHE_";

	@Override
	public List<String> getGroups() {
		return sysPropertiesDao.getGroups();
	}

	@Override
	public boolean isExist(SystemPropertiesEntity sysProperties) {
		return sysPropertiesDao.isExist(sysProperties) > 0;
	}

	/**
	 * 将所有系统属性通过不同环境分组加入进缓存中
	 */

	public Map<String, Map<String, String>> reloadProperty() {
		List<SystemPropertiesEntity> list = this.getAll();

		Map<String, Map<String, String>> propertiesCache = new HashMap<String, Map<String, String>>();
		for (SystemPropertiesEntity property : list) {
			String environment = property.getEnvironment();
			if (!EnvironmentConstant.contain(environment)) {
				log.warn("当前系统属性的环境参数“{}”非系统定义参数{}请注意！", environment, EnvironmentConstant.getKes());
			}

			Map<String, String> proerties = propertiesCache.get(environment);

			if (proerties == null) {
				proerties = new HashMap<String, String>();
				propertiesCache.put(environment, proerties);
			}

			proerties.put(property.getAlias().toLowerCase(), property.getRealVal());
		}
		// TODO:需要添加缓存支持
//		cache.add(PROPERTIES_CACHE_KEY, propertiesCache);
		return propertiesCache;
	}
	
	
	
	@Override
	public String getByAlias(String alias) {
		alias = alias.toLowerCase();
		
		Map<String, Map<String, String>> enviromentProps =  reloadProperty();
		// TODO:需要添加缓存支持
//		Map<String, Map<String, String>> enviromentProps = (Map<String, Map<String, String>>) cache.getByKey(PROPERTIES_CACHE_KEY);
//		if (BeanUtils.isEmpty(enviromentProps)) {
//			enviromentProps = reloadProperty();
//		}

		String currentEnviroment = AppContextUtil.getCtxEnvironment();
		// 获取activeEnviroment中的属性
		if (enviromentProps.containsKey(currentEnviroment)) {
			Map<String, String> currentEnviromentProp = enviromentProps.get(currentEnviroment);
			if (currentEnviromentProp.containsKey(alias)) {
				return currentEnviromentProp.get(alias);
			}
		}

		// 获取默认 DEV 环境的配置
		if (!enviromentProps.containsKey(EnvironmentConstant.DEV.key())) {
			return null;
		}

		return enviromentProps.get(EnvironmentConstant.DEV.key()).get(alias);
	}

	@Override
	public String getByAlias(String alias, String defaultValue) {
		String val = getByAlias(alias);
		if (StringUtils.isEmpty(val))
			return defaultValue;
		return val;
	}

	@Override
	public Integer getIntByAlias(String alias) {
		String val = getByAlias(alias);
		if (StringUtils.isEmpty(val))
			return 0;
		Integer rtn = Integer.parseInt(val);
		return rtn;
	}

	@Override
	public Integer getIntByAlias(String alias, Integer defaulValue) {
		String val = getByAlias(alias);
		if (StringUtils.isEmpty(val))
			return defaulValue;
		Integer rtn = Integer.parseInt(val);
		return rtn;
	}

	@Override
	public Long getLongByAlias(String alias) {
		String val = getByAlias(alias);
		if (StringUtils.isEmpty(val))
			return 0L;
		Long rtn = Long.parseLong(val);
		return rtn;
	}

	@Override
	public boolean getBooleanByAlias(String alias) {
		String val = getByAlias(alias);
		return Boolean.parseBoolean(val);
	}

	@Override
	public boolean getBooleanByAlias(String alias, boolean defaulValue) {
		String val = getByAlias(alias);
		if (StringUtils.isEmpty(val))
			return defaulValue;
		if ("1".equals(val))
			return true;
		return Boolean.parseBoolean(val);
	}
}
