package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.WorkbenchLayout;

public interface WorkbenchLayoutManager extends Manager<String, WorkbenchLayout> {

    List<WorkbenchLayout> getByUserId(String currentUserId);

    void savePanelLayout(List<WorkbenchLayout> layOutList, String layoutKey);

}
