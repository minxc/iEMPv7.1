package org.minxc.emp.form.core.manager;


import java.util.List;
import java.util.Map;

import org.minxc.emp.common.db.model.table.ColumnEntity;
import org.minxc.emp.common.db.model.table.TableEntity;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.form.core.model.FormCustomDialog;

/**
 * form_cust_dialog Manager处理接口
 *
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-01-18 19:30:51
 */
public interface FormCustomDialogManager extends Manager<String, FormCustomDialog> {

    /**
     * 
     * 根据key获取FormCustDialog
     * 
     *
     * @param key
     * @return
     */
    FormCustomDialog getByKey(String key);

    /**
     * 
     * 查询ObjName
     * 
     *
     * @param formCustDialog
     * @return
     */
    Map<String, String> searchObjName(FormCustomDialog formCustDialog);

    /**
     * 
     * 获取objName对象的表 / 视图信息
     * 
     *
     * @param formCustDialog
     * @return
     */
    TableEntity<ColumnEntity> getTable(FormCustomDialog formCustDialog);

    /**
     * 
     * 根据formCustDialog获取数据
     * 包含树形和列表
     * 
     *
     * @param formCustDialog
     * @param queryFilter    页面传参
     * @return
     */
    List<?> data(FormCustomDialog formCustDialog, QueryFilter queryFilter);

}
