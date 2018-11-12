package org.minxc.emp.form.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.minxc.emp.form.api.model.FormDefinition;
import org.minxc.emp.form.api.service.FormService;
import org.minxc.emp.form.core.manager.FormDefManager;
import org.minxc.emp.form.core.model.FormDefinitionImpl;
import org.springframework.stereotype.Service;
@Service("formService")
public class DefaultFormService implements FormService {

    @Resource
    private FormDefManager formDefManager;

    public FormDefinition getByFormKey(String formKey) {
        FormDefinition form = formDefManager.getByKey(formKey);
        return form;
    }


    @Override
    public FormDefinition getByFormId(String formId) {
        return formDefManager.get(formId);
    }


    @Override
    public String getFormExportXml(Set<String> formKeys) {
        List<String> id = new ArrayList<String>();
        for (String formKey : formKeys) {
            FormDefinitionImpl form =  formDefManager.getByKey(formKey);
            id.add(form.getId());
        }
        //	Map<String,String> map = formDefManager.exportForms(id, false);

        return null;//map.get("form.xml");
    }


    @Override
    public void importForm(String formXmlStr) {
        try {
            //formDefManager.importByFormXml(formXmlStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导入表单失败" + e.getMessage(), e);
        }
    }

}
