package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SystemTreeNodeEntity;


/**
 * 
* 项目名称：system-impl   
* 类名称：SysTreeNodeDao   
* 类描述： 系统树节点 DAO接口  
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午4:49:05   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午4:49:05   
* 修改备注：   
* @version  1.0  
*
 */

@Mapper
public interface SysTreeNodeDao extends CommonDao<String, SystemTreeNodeEntity> {

    /**
     * <pre>
     * 根据树id删除节点
     * </pre>
     *
     * @param treeId
     */
    void removeByTreeId(String treeId);
    
    /**
     * <pre>
     * 删除path下的全部节点
     * </pre>
     *
     * @param path
     */
    void removeByPath(String path);

}
