package org.minxc.emp.system.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.system.impl.model.SerialNoEntity;


@Mapper
public interface SerialNoDao extends CommonDao<String, SerialNoEntity> {


    /**
     * 判读流水号别名是否已经存在
     *
     * @param id    id为null 表明是新增的流水号，否则为更新流水号
     * @param alias
     * @return
     */
    Integer isAliasExisted(@Param("id")String id, @Param("alias")String alias);

    /**
     * 根据别名获取流水号数据（数据库锁定了对应的行数据）
     *
     * @param alias
     * @return
     */
    SerialNoEntity getByAlias(String alias);


    /**
     * 根据流程别名 。
     *
     * @param SerialNo void
     */
    int updByAlias(SerialNoEntity SerialNo);
}
