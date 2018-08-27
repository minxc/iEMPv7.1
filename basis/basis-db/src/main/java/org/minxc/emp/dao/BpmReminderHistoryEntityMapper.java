package org.minxc.emp.dao;

import org.minxc.emp.model.BpmReminderHistoryEntity;

public interface BpmReminderHistoryEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(BpmReminderHistoryEntity record);

    int insertSelective(BpmReminderHistoryEntity record);

    BpmReminderHistoryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BpmReminderHistoryEntity record);

    int updateByPrimaryKey(BpmReminderHistoryEntity record);
}