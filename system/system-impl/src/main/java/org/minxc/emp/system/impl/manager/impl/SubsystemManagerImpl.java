package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.impl.dao.SubsystemDao;
import org.minxc.emp.system.impl.manager.SubsystemManager;
import org.minxc.emp.system.impl.model.Subsystem;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Service;

import com.minxc.emp.core.util.BeanUtils;

/**
 * <pre>
 * 描述：子系统定义 处理实现类
 * </pre>
 */
@Service("subsystemManager")
public class SubsystemManagerImpl extends CommonManager<String, Subsystem> implements SubsystemManager {
    @Resource
    SubsystemDao subsystemDao;

    @Override
    public boolean isExist(Subsystem subsystem) {
        return subsystemDao.isExist(subsystem)>0;
    }

    @Override
    public List<Subsystem> getList() {
        return subsystemDao.getList();
    }

    @Override
    public Subsystem getDefaultSystem(String userId) {
        List<Subsystem> list = subsystemDao.getSystemByUser(userId);
        if (BeanUtils.isEmpty(list)) return null;

        for (Subsystem system : list) {
            if (1 == system.getIsDefault()) return system;
        }
        return list.get(0);
    }

    @Override
    public void setDefaultSystem(String systemId) {
        Subsystem subSystem = subsystemDao.get(systemId);
        if (subSystem.getIsDefault() == 1) {
            subSystem.setIsDefault(0);
        } else {
            subsystemDao.updNoDefault();
            subSystem.setIsDefault(1);
        }
        subsystemDao.update(subSystem);
    }


    @Override
    public List<Subsystem> getCurrentUserSystem() {
        User user = ContextUtil.getCurrentUser();
        if (ContextUtil.isAdmin(user)) {
            return subsystemDao.getList();
        }

        return subsystemDao.getSystemByUser(user.getUserId());
    }
}
