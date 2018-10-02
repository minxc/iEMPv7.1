package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.system.impl.dao.WorkbenchLayoutDao;
import org.minxc.emp.system.impl.manager.WorkbenchLayoutManager;
import org.minxc.emp.system.impl.model.WorkbenchLayout;
import org.springframework.stereotype.Service;


@Service("workbenchLayoutManager")
public class WorkbenchLayoutManagerImpl extends CommonManager<String, WorkbenchLayout> implements WorkbenchLayoutManager {
    @Resource
    WorkbenchLayoutDao workbenchLayoutDao;

    @Override
    public void savePanelLayout(List<WorkbenchLayout> layOutList, String userId) {
        workbenchLayoutDao.removeByUserId(userId);

        for (WorkbenchLayout layOut : layOutList) {
            layOut.setUserId(userId);
            workbenchLayoutDao.create(layOut);
        }
    }


    @Override
    public List<WorkbenchLayout> getByUserId(String userId) {
        List<WorkbenchLayout> list = workbenchLayoutDao.getByUserId(userId);

        if (BeanUtils.isEmpty(list)) {
            list = workbenchLayoutDao.getByUserId(WorkbenchLayout.DEFAULT_LAYOUT);
        }
        return list;
    }


}
