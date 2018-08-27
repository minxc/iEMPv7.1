package org.minxc.emp.dao;

import org.minxc.emp.model.WorkbenchTemplateEntity;

public interface WorkbenchTemplateEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkbenchTemplateEntity record);

    int insertSelective(WorkbenchTemplateEntity record);

    WorkbenchTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkbenchTemplateEntity record);

    int updateByPrimaryKeyWithBLOBs(WorkbenchTemplateEntity record);

    int updateByPrimaryKey(WorkbenchTemplateEntity record);
}