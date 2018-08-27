package org.minxc.emp.dao;

import org.minxc.emp.model.UploadedFileEntity;

public interface UploadedFileEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(UploadedFileEntity record);

    int insertSelective(UploadedFileEntity record);

    UploadedFileEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UploadedFileEntity record);

    int updateByPrimaryKey(UploadedFileEntity record);
}