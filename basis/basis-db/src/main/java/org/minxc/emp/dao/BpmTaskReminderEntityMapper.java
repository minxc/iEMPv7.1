package org.minxc.emp.dao;

import org.minxc.emp.model.BpmTaskReminderEntity;

public interface BpmTaskReminderEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmTaskReminderEntity record);

    int insertSelective(BpmTaskReminderEntity record);

    BpmTaskReminderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmTaskReminderEntity record);

    int updateByPrimaryKey(BpmTaskReminderEntity record);
}