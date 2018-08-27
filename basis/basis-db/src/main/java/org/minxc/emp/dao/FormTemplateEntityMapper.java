package org.minxc.emp.dao;

import org.minxc.emp.model.FormTemplateEntity;

public interface FormTemplateEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(FormTemplateEntity record);

    int insertSelective(FormTemplateEntity record);

    FormTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FormTemplateEntity record);

    int updateByPrimaryKeyWithBLOBs(FormTemplateEntity record);

    int updateByPrimaryKey(FormTemplateEntity record);
}