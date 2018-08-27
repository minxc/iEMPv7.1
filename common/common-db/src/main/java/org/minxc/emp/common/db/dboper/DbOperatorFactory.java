package org.minxc.emp.common.db.dboper;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.PropertiesUtil;
import org.minxc.emp.common.db.api.table.DbTypeEnum;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * DbOperator的工厂类
 */
public class DbOperatorFactory {

    private DbOperatorFactory() {

    }

    /**
     * <pre>
     * 构建一个操作器
     * </pre>
     *
     * @param type
     * @param jdbcTemplate
     * @return
     */
    public static DbOperator newOperator(String type, JdbcTemplate jdbcTemplate) {
        if (DbTypeEnum.MYSQL.equalsWithKey(type)) {
            return new MysqlDbOperator(jdbcTemplate);
        }
        return null;
    }
    
    /**
     * <pre>
     * 获取本地数据库操作类
     * </pre>	
     * @return
     */
    public static DbOperator getLocal() {
    	return newOperator(PropertiesUtil.getJdbcType(), AppContextUtil.getBean(JdbcTemplate.class));
    }
}
