package org.minxc.emp.system.impl.manager.impl;

import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.ResRoleDao;
import org.minxc.emp.system.impl.manager.ResRoleManager;
import org.minxc.emp.system.impl.model.ResRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
* 项目名称：basis-impl   
* 类名称：ResRoleManagerImpl   
* 类描述： 角色资源分配 处理实现类  
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午8:16:48   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午8:16:48   
* 修改备注：   
* @version  1.0  
*
 */
@Service("resRoleManager")
public class ResRoleManagerImpl extends CommonManager<String, ResRole> implements ResRoleManager {
   
	
	@Resource
    ResRoleDao resRoleDao;
//    @Resource
//    ICache iCache;

    public final String RESOURCE_URL = "RES_URL_";

    public final String RESOURCE_RES = "SYS_RES_";

    @Override
    public List<ResRole> getAllByRoleId(String roleId) {

        return resRoleDao.getByRoleId(roleId);
    }


    @Override
    public void assignResByRoleSys(String resIds, String systemId, String roleId) {
        resRoleDao.removeByRoleAndSystem(roleId, systemId);

        String[] aryRes = resIds.split(",");
        for (String resId : aryRes) {
            if ("0".equals(resId)) continue;
            ResRole resRole = new ResRole();
            resRole.setId(UniqueIdUtil.getSuid());
            resRole.setRoleId(roleId);
            resRole.setSystemId(systemId);
            resRole.setResId(resId);
            resRoleDao.create(resRole);
        }

    }

    @Override
    public Map<String, Set<String>> getResRoleBySystem(String systemId) {
        String resStr = RESOURCE_RES + systemId;
//        if (iCache.containKey(resStr)) {
//            return (Map<String, Set<String>>) iCache.getByKey(resStr);
//        }

        List<ResRole> list = resRoleDao.getResRoleBySystemId(systemId);
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        for (ResRole res : list) {
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
//        iCache.add(resStr, map);
        return map;
    }

    @Override
    public Map<String, Set<String>> getUrlRoleBySystem(String systemId) {
        String urlStr = RESOURCE_URL + systemId;
//        if (iCache.containKey(urlStr)) {
//            return (Map<String, Set<String>>) iCache.getByKey(urlStr);
//        }

        List<ResRole> list = resRoleDao.getResRoleBySystemId(systemId);
        List<ResRole> urlList = resRoleDao.getUrlRoleBySystemId(systemId);

        urlList.addAll(list);

        Map<String, Set<String>> map = new HashMap<String, Set<String>>();

        for (ResRole res : list) {
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
        //添加到缓存
//        iCache.add(urlStr, map);
        return map;
    }

    @Override
    public void cleanResCache(String systemId) {
        String urlStr = RESOURCE_URL + systemId;
        String resStr = RESOURCE_RES + systemId;
//        iCache.delByKey(urlStr);
//        iCache.delByKey(resStr);
    }


}
