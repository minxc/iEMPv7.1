package org.minxc.emp.form.core.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.form.core.model.FormTemplate;

import com.alibaba.fastjson.JSONArray;

/**
 * FormTemplate的Manager
 */
public interface FormTemplateManager extends Manager<String, FormTemplate> {

    /**
     * 根据模版别名取得模版。
     *
     * @param key
     * @return
     */
    public FormTemplate getByKey(String key);

    /**
     * 获取所有的系统原始模板
     *
     * @return
     * @throws Exception
     */
    public void initAllTemplate();

    /**
     * 当模版数据为空时，将form目录下的模版添加到数据库中。
     */
    public void init();

    /**
     * 检查模板别名是否唯一
     *
     * @param key
     * @return
     */
    public boolean isExist(String key);

    /**
     * 将用户自定义模板备份
     *
     * @param id
     */
    public void backUpTemplate(String id);

    
    /**
     * <pre>
     * 根据boKey获取模板数据
     * </pre>
     * @param boKey
     * @param type 
     * @return
     */
	JSONArray templateData(String boKey, String type);


}
