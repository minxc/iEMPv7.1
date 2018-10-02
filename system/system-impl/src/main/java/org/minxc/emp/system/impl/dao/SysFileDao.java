package org.minxc.emp.system.impl.dao;
import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.UploadedFileEntity;


/**
 * 系统附件 DAO接口
 */

@Mapper
public interface SysFileDao extends CommonDao<String, UploadedFileEntity> {

}
