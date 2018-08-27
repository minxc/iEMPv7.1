package org.minxc.emp.basis.impl.core.manager;

import java.util.List;

import org.minxc.emp.basis.impl.core.model.WorkbenchLayout;
import org.minxc.emp.common.manager.Manager;

public interface WorkbenchLayoutManager extends Manager<String, WorkbenchLayout> {

    List<WorkbenchLayout> getByUserId(String currentUserId);

    void savePanelLayout(List<WorkbenchLayout> layOutList, String layoutKey);

}
