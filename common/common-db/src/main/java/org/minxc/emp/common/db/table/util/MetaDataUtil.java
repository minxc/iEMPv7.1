package org.minxc.emp.common.db.table.util;

import org.springframework.jdbc.core.JdbcTemplate;

import org.minxc.emp.common.db.api.table.ViewOperator;
import org.minxc.emp.common.db.table.BaseTableMeta;
import org.minxc.emp.common.db.table.factory.DatabaseFactory;
import org.minxc.emp.core.util.AppContextUtil;

public class MetaDataUtil {

    /**
     * 获取一个BaseTableMeta，已经设置好方言和jdbcTemplate
     *
     * @param dbType
     * @return BaseTableMeta
     * @throws
     * @since 1.0.0
     */
    public static BaseTableMeta getBaseTableMetaAfterSetDT(String dbType) {
        JdbcTemplate jdbcTemplate = AppContextUtil.getBean(JdbcTemplate.class);
        BaseTableMeta baseTableMeta = null;
        try {
            baseTableMeta = DatabaseFactory.getTableMetaByDbType(dbType);

            /**
             * 配置文件中的对象
             *
             * @Resource JdbcTemplate jdbcTemplate;
             */
            baseTableMeta.setJdbcTemplate(jdbcTemplate);
        } catch (Exception e) {
        }
        return baseTableMeta;
    }

    /**
     * 获取一个IViewOperator，已经设置好方言和jdbcTemplate
     *
     * @param dbType
     * @return IViewOperator
     * @throws
     * @since 1.0.0
     */
    public static ViewOperator getIViewOperatorAfterSetDT(String dbType) {
        JdbcTemplate jdbcTemplate = AppContextUtil.getBean(JdbcTemplate.class);
        ViewOperator iViewOperator = null;
        try {
            iViewOperator = DatabaseFactory.getViewOperator(dbType);

            /**
             * 配置文件中的对象
             *
             * @Resource JdbcTemplate jdbcTemplate;
             */
            iViewOperator.setJdbcTemplate(jdbcTemplate);

        } catch (Exception e) {

        }

        return iViewOperator;
    }
}
