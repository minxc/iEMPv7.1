package org.minxc.emp.system.impl.manager.impl;

import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.system.impl.dao.SysTreeDao;
import org.minxc.emp.system.impl.manager.SystemTreeManager;
import org.minxc.emp.system.impl.manager.SystemTreeNodeManager;
import org.minxc.emp.system.impl.model.TreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
* 项目名称：system-impl   
* 类名称：SysTreeManagerImpl   
* 类  系统树 Manager处理实现类
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午5:07:12   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午5:07:12   
* 修改备注：   
* @version  1.0  
*
 */
@Service("sysTreeManager")
public class SysTreeManagerImpl extends CommonManager<String, TreeEntity> implements SystemTreeManager {
   
	@Autowired
    private SysTreeDao sysTreeDao;
	@Autowired
    private SystemTreeNodeManager sysTreeNodeManager;

    @Override
    public TreeEntity getByKey(String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("key_", key, QueryOperator.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public void removeContainNode(String id) {
        this.remove(id);
        sysTreeNodeManager.removeByTreeId(id);
    }
}
