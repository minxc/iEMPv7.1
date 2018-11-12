package org.minxc.emp.common.db.model.query;

import org.minxc.emp.core.api.query.FieldLogic;
import org.minxc.emp.core.api.query.FieldRelation;
import org.minxc.emp.core.api.query.WhereClause;

import java.util.ArrayList;
import java.util.List;


/**
 * 
* 项目名称：common-db   
* 类名称：DefaultFieldLogic   
* 类字段查询的条件
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午4:15:32   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午4:15:32   
* 修改备注：   
* @version  1.0  
*
 */
public class DefaultFieldLogic implements FieldLogic {

    /**
     * 查询字段组合列表
     */
    private List<WhereClause> whereClauses = new ArrayList<WhereClause>();
    
    
    /**
     * 字段关系
     */
    private FieldRelation fieldRelation = FieldRelation.AND;

    public DefaultFieldLogic() {
    }

    public DefaultFieldLogic(FieldRelation fieldRelation) {
        this.fieldRelation = fieldRelation;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public FieldRelation getFieldRelation() {
        return fieldRelation;
    }

    public void setFieldRelation(FieldRelation fieldRelation) {
        this.fieldRelation = fieldRelation;
    }

    public String getSql() {
        if (whereClauses.size() == 0) return "";
        if (whereClauses.size() == 1 && !FieldRelation.NOT.equals(fieldRelation)) return whereClauses.get(0).getSql();

        StringBuffer sqlBuf = new StringBuffer("(");
        int i = 0;
        if (whereClauses.size() > 0 && FieldRelation.NOT.equals(fieldRelation)) {
            sqlBuf.append(" NOT (");
            for (WhereClause clause : whereClauses) {
                if (i++ > 0) {
                    sqlBuf.append(" ").append(FieldRelation.AND).append(" ");
                }
                sqlBuf.append(clause.getSql());
            }
            sqlBuf.append(")");

            return sqlBuf.toString();
        }

        for (WhereClause clause : whereClauses) {
            if (i++ > 0) {
                sqlBuf.append(" ").append(fieldRelation).append(" ");
            }
            sqlBuf.append(clause.getSql());
        }
        sqlBuf.append(")");

        return sqlBuf.toString();
    }

}
