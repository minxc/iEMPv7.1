package org.minxc.emp.common.db.id;

import org.minxc.emp.common.db.api.IdGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

/**
 * 
* 项目名称：common-db   
* 类名称：DefaultIdGenerator   
* 类   ID生成器
* 创建人：Xianchang.min   
* 创建时间：2018年11月7日 下午7:40:35   
* 修改人：Xianchang.min   
* 修改时间：2018年11月7日 下午7:40:35   
* 修改备注：   
* @version  1.0  
*
 */
@Slf4j
public class DefaultIdGenerator implements IdGenerator, InitializingBean {
	
	@Resource
    public JdbcTemplate jdbcTemplate;
    /**
     * 增长段值
     */
    private int increaseBound = 1000;
    /**
     * 当前DBID
     */
    private Long dbid = 1l;
    /**
     * 当前递增的最大值
     */
    private Long maxDbid = -1l;
    /**
     * 机器ID
     */
    private int machineDbid = 1;
    /**
     * 机器名称 多台物理机器集群部署时，需要唯一区分
     */
    private String machineName = "1";

    /**
     * ID的基准长度
     */
    private Long idBase = 10000000000000L;


    private boolean hasError = false;

    /**
     * 获取唯一的ID标识
     *
     * @return
     */
    public synchronized Long getUniqueId() {
        if (dbid >= maxDbid || hasError) {
            genNextDbIds();
        }
        Long nextId = dbid++;
        return nextId + machineDbid * idBase;
    }

    private void updateBound() {
        String sql = "UPDATE YMMG_DB_ID_GENERATOR SET START_VALUE=?,MAXIMUM=? WHERE ID=?";
        dbid = maxDbid;
        maxDbid += increaseBound;
        jdbcTemplate.update(sql, dbid, maxDbid, machineDbid);
    }

    private void genNextDbIds() {
        try {
            updateBound();
            hasError = false;
        } catch (Exception ex) {
            hasError = true;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void init() {
    	log.info("defaultIdGenerator is initilizaing ....");
        String sql = "SELECT * FROM YMMG_DB_ID_GENERATOR where MACHINE=?";
        //检查该机器是否已经存在增长的键值记录
        try {
            jdbcTemplate.queryForObject(sql, new RowMapper() {
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    dbid = rs.getLong("START_VALUE");
                    maxDbid = rs.getLong("MAXIMUM");
                    machineDbid = rs.getInt("ID");
                    return machineDbid;
                }
            }, machineName);
            //插入该机器的键值增长记录
            genNextDbIds();
        } catch (Exception ex) {
        	log.error(ex.toString());
            String maxSql = "SELECT MAX(ID) FROM YMMG_DB_ID_GENERATOR";
            Integer maxResult = jdbcTemplate.queryForObject(maxSql, Integer.class);
            if (maxResult == null || maxResult == 0) {
                maxResult = 1;
            } else {
                maxResult++;
            }
            machineDbid = maxResult;
            maxDbid = dbid + increaseBound;
            sql = "INSERT INTO YMMG_DB_ID_GENERATOR(ID,START_VALUE,MAXIMUM,MACHINE)VALUES(?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{machineDbid, dbid, maxDbid, machineName});
        }
    }

    public void afterPropertiesSet() throws Exception {
        init();
    }


    public void setIdBase(Long idBase) {
        this.idBase = idBase;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public void setIncreaseBound(int increase) {
        this.increaseBound = increase;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long getUId() {
        return getUniqueId();
    }

    public String getSuid() {
        return getUId().toString();
    }

}
