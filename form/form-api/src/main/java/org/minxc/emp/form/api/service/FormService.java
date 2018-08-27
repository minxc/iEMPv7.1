package org.minxc.emp.form.api.service;

import java.util.Set;

import org.minxc.emp.form.api.model.FormDef;

public interface FormService {

    /**
     * 根据formkey获取表单。
     *
     * @param formKey
     * @return IFormDef
     */
    FormDef getByFormKey(String formKey);

    /**
     * 根据表单ID取得表单对象。
     *
     * @param formId
     * @return IFormDef
     */
    FormDef getByFormId(String formId);

    /**
     * 根据formKey 导出表单
     *
     * @param formKeys
     * @return
     */
    String getFormExportXml(Set<String> formKeys);

    void importForm(String formXmlStr);
}
