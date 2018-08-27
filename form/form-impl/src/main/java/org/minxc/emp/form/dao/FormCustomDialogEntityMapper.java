package org.minxc.emp.dao;

import org.minxc.emp.model.FormCustomDialogEntity;
import org.minxc.emp.model.FormCustomDialogEntityWithBLOBs;

public interface FormCustomDialogEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(FormCustomDialogEntityWithBLOBs record);

    int insertSelective(FormCustomDialogEntityWithBLOBs record);

    FormCustomDialogEntityWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FormCustomDialogEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FormCustomDialogEntityWithBLOBs record);

    int updateByPrimaryKey(FormCustomDialogEntity record);
}