package org.minxc.emp.system.impl.manager.impl;

import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.cache.Cache;
import org.minxc.emp.system.impl.dao.ResourceRoleLinkDao;
import org.minxc.emp.system.impl.manager.ResRoleManager;
import org.minxc.emp.system.impl.model.RoleResouceLinkEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
* 项目名称：basis-impl   
* 类名称：ResRoleManagerImpl   
* 类 角色资源分配 处理实现类  
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午8:16:48   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午8:16:48   
* 修改备注：   
* @version  1.0  
*
 */
@Service("resRoleManager")
public class ResRoleManagerImpl extends CommonManager<String, RoleResouceLinkEntity> implements ResRoleManager {

	@Resource
	private ResourceRoleLinkDao resRoleDao;
	
	@Resource
	private Cache  cache;    //内控缓存，生存周期是和应用服务器启动维持一致

	public final String RESOURCE_URL = "RES_URL_";

	public final String RESOURCE_RES = "SYS_RES_";

	@Override
	public List<RoleResouceLinkEntity> getAllByRoleId(String roleId) {
		return resRoleDao.getByRoleId(roleId);
	}

	@Override
	public void assignResByRoleSys(String resIds, String applicationId, String roleId) {
		resRoleDao.deleteByRoleAndApplicationId(roleId, applicationId);
		String[] aryRes = resIds.split(",");
		for (String resId : aryRes) {
			if ("0".equals(resId))
				continue;
			RoleResouceLinkEntity resRole = new RoleResouceLinkEntity();
			resRole.setId(UniqueIdUtil.getSuid());
			resRole.setRoleId(roleId);
			resRole.setApplicationId(applicationId);
			resRole.setResId(resId);
			resRoleDao.create(resRole);
		}

	}

	@Override
	public Map<String, Set<String>> getResRoleBySystem(String applicationId) {
		String resStr = RESOURCE_RES + applicationId;
		if (cache.containKey(resStr)) {
			return (Map<String, Set<String>>) cache.getByKey(resStr);
		}

		List<RoleResouceLinkEntity> list = resRoleDao.getResRoleByApplicationId(applicationId);
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		for (RoleResouceLinkEntity res : list) {
			String resAlias = res.getResAlias();
			if (map.containsKey(resAlias)) {
				Set<String> set = map.get(resAlias);
				set.add(res.getRoleAlias());
			} else {
				Set<String> set = new HashSet<String>();
				set.add(res.getRoleAlias());
				map.put(resAlias, set);
			}
		}
		cache.add(resStr, map);
		return map;
	}

	@Override
	public Map<String, Set<String>> getUrlRoleBySystem(String applicationId) {
		String urlStr = RESOURCE_URL + applicationId;
		if (cache.containKey(urlStr)) {
			return (Map<String, Set<String>>) cache.getByKey(urlStr);
		}

		List<RoleResouceLinkEntity> list = resRoleDao.getResRoleByApplicationId(applicationId);
		List<RoleResouceLinkEntity> urlList = resRoleDao.getUrlRoleByApplicationId(applicationId);

		urlList.addAll(list);

		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		for (RoleResouceLinkEntity res : list) {
			String url = res.getUrl();
			if (map.containsKey(url)) {
				Set<String> set = map.get(url);
				set.add(res.getRoleAlias());
			} else {
				Set<String> set = new HashSet<String>();
				set.add(res.getRoleAlias());
				map.put(url, set);
			}
		}
		// 添加到缓存
		cache.add(urlStr, map);
		return map;
	}

	@Override
	public void cleanResCache(String systemId) {
		String urlStr = RESOURCE_URL + systemId;
		String resStr = RESOURCE_RES + systemId;
		cache.delByKey(urlStr);
		cache.delByKey(resStr);
	}
}
