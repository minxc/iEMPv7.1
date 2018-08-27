package org.minxc.emp.dao;

import org.minxc.emp.model.BizLinkEntity;
import org.minxc.emp.model.BizLinkEntityKey;

public interface BizLinkEntityMapper {
    int deleteByPrimaryKey(BizLinkEntityKey key);

    int insert(BizLinkEntity record);

    int insertSelective(BizLinkEntity record);

    BizLinkEntity selectByPrimaryKey(BizLinkEntityKey key);

    int updateByPrimaryKeySelective(BizLinkEntity record);

    int updateByPrimaryKey(BizLinkEntity record);
}