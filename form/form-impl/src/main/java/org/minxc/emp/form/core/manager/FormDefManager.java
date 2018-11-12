package org.minxc.emp.form.core.manager;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.form.core.model.FormDefinitionImpl;


/**
 * 表单 Manager处理接口
 *
 * @time 2018-03-19 20:30:46
 */
public interface FormDefManager extends Manager<String, FormDefinitionImpl> {

    /**
     * 
     * 根据别名获取表单
     * 
     *
     * @param key
     * @return
     */
    FormDefinitionImpl getByKey(String key);

    /**
     * 
     * 处理保存表单的html到文件的逻辑
     * 备份用
     * 
     *
     * @param formDef
     */
    void saveBackupHtml(FormDefinitionImpl formDef);

    /**
     * 
     * 读取备份文件中的表单html
     * 
     *
     * @param formDef
     * @return
     */
    String getBackupHtml(FormDefinitionImpl formDef);

}
