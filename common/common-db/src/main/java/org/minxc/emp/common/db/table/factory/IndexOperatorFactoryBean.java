package org.minxc.emp.common.db.table.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;

import org.minxc.emp.common.db.api.table.IndexOperator;
import org.minxc.emp.common.db.table.util.SQLConst;

/**
 * IndexOperator factorybean，用户创建IIndexOperator对象。
 *
 * <pre>
 * 配置文件：app-beans.xml
 * &lt;bean id="indexOperator" class="org.minxc.emp.common.db.table.factory.IndexOperatorFactoryBean">
 * 		&lt;property name="dbType" value="${jdbc.dbType}"/>
 * 		&lt;property name="jdbcTemplate" ref="jdbcTemplate"/>
 * &lt;/bean>
 * </pre>
 *
 * @author ray
 */
public class IndexOperatorFactoryBean implements FactoryBean<IndexOperator> {

    private IndexOperator indexOperator;

    private String dbType = SQLConst.DB_MYSQL;

    private JdbcTemplate jdbcTemplate;

    @Override
    public IndexOperator getObject() throws Exception {
        indexOperator = DatabaseFactory.getIndexOperator(dbType);

        indexOperator.setJdbcTemplate(jdbcTemplate);
        return indexOperator;
    }

    /**
     * 设置数据库类型
     *
     * @param dbType
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 设置jdbcTemplate
     *
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return IndexOperator.class;
    }

    @Override
    public boolean isSingleton() {

        return true;
    }

}
