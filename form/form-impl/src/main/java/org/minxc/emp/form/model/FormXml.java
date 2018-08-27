package org.minxc.emp.form.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.minxc.emp.form.api.model.FormDef;

import java.util.List;

/**
 * 此类用于表单的导入导出。
 */
@XmlRootElement(name = "bpmForms")
@XmlAccessorType(XmlAccessType.FIELD)
public class FormXml {


    @XmlElement(name = "bpmForm", type = org.minxc.emp.form.model.FormDef.class)
    private FormDef bpmForm;

    @XmlElement(name = "formBusSet", type = FormBusSet.class)
    private FormBusSet formBusSet;

    @XmlElement(name = "boCodes")
    private List<String> boCodes;


    public List<String> getBoCodes() {
        return boCodes;
    }


    public void setBoCodes(List<String> boCodes) {
        this.boCodes = boCodes;
    }


    public FormDef getBpmForm() {
        return bpmForm;
    }
 
    public FormBusSet getFormBusSet() {
        return formBusSet;
    }


    public void setFormBusSet(FormBusSet formBusSet) {
        this.formBusSet = formBusSet;
    }


    public void setBpmForm(FormDef bpmForm) {
        this.bpmForm = bpmForm;
    }


}
