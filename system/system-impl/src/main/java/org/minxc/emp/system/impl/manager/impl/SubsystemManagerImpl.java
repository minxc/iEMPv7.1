package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.impl.dao.SubsystemDao;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.model.ApplicationEntity;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 描述：子系统定义 处理实现类
 * </pre>
 */
@Service("subsystemManager")
public class SubsystemManagerImpl extends CommonManager<String, ApplicationEntity> implements SubsystemManager {
    @Resource
    SubsystemDao subsystemDao;

    @Override
    public boolean isExist(ApplicationEntity subsystem) {
        return subsystemDao.isExist(subsystem)>0;
    }

    @Override
    public List<ApplicationEntity> getList() {
        return subsystemDao.getList();
    }

    @Override
    public ApplicationEntity getDefaultSystem(String userId) {
        List<ApplicationEntity> list = subsystemDao.getSystemByUser(userId);
        if (BeanUtils.isEmpty(list)) return null;

        for (ApplicationEntity system : list) {
            if (1 == system.getIsDefault()) return system;
        }
        return list.get(0);
    }

    @Override
    public void setDefaultSystem(String systemId) {
        ApplicationEntity subSystem = subsystemDao.get(systemId);
        if (subSystem.getIsDefault() == 1) {
            subSystem.setIsDefault(0);
        } else {
            subsystemDao.updNoDefault();
            subSystem.setIsDefault(1);
        }
        subsystemDao.update(subSystem);
    }


    @Override
    public List<ApplicationEntity> getCurrentUserSystem() {
        User user = ContextUtil.getCurrentUser();
        if (ContextUtil.isAdmin(user)) {
            return subsystemDao.getList();
        }

        return subsystemDao.getSystemByUser(user.getUserId());
    }
}
