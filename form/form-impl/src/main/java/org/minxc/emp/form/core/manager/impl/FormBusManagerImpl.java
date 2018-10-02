package org.minxc.emp.form.core.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.IGroovyScriptEngine;
import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.api.service.IBusinessDataService;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.core.util.ThreadMsgUtil;
import org.minxc.emp.form.api.model.IFormDef;
import org.minxc.emp.form.core.manager.FormBusManager;
import org.minxc.emp.form.core.manager.FormBusSetManager;
import org.minxc.emp.form.core.manager.FormDefManager;
import org.minxc.emp.form.core.model.FormBusSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

/**
 * <pre>
 * 描述：form_bus_set 处理实现类
 * </pre>
 */
@Service("formBusManager")
public class FormBusManagerImpl implements FormBusManager {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    IBusinessDataService bizDataService;
    @Resource
    FormBusSetManager formBusSetManager;
    @Resource
    FormDefManager formDefManager;
    @Resource
    IGroovyScriptEngine groovyScriptEngine;

    @Override
    public IBusinessData getBoData(String boKey, Map param) {

        try {
            if (BeanUtils.isNotEmpty(param)) {
                return null; //bizDataService.getBusDataByParam(boKey, param);
            }
        } catch (Exception e) {
            if (!(e.getCause() instanceof EmptyResultDataAccessException)) {
            } else {
                log.error(e.getMessage(), e);
            }
        }

        return null; //bizDataService.getInitBizData(boKey);

    }

    @Override
    public void saveData(String formKey, String json) {

        IFormDef form = null;// formDefManager.getByFormKey(formKey);
        String boCode = null;// form.getBoCodes();

        IBusinessData bizData = null;//bizDataService.getBusDataByBoData(boCode, JSON.parseObject(json));

        FormBusSet busSet = formBusSetManager.getByFormKey(formKey);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bizData", bizData);

        // 前置脚本
        if (busSet != null && StringUtil.isNotEmpty(busSet.getPreScript())) {
            groovyScriptEngine.execute(busSet.getPreScript(), param);
        }

        boolean isCreate = false;
        if (BeanUtils.isEmpty(bizData.getPk())) {
            isCreate = true;
        }

        // 保存
       // bizDataService.saveData(bizData);
        // 后置脚本
        if (busSet != null && StringUtil.isNotEmpty(busSet.getAfterScript())) {
            groovyScriptEngine.execute(busSet.getAfterScript(), param);
        }

        if (isCreate) {
            ThreadMsgUtil.addMsg("添加成功！");
        } else {
            ThreadMsgUtil.addMsg("编辑成功！");
        }
    }

    @Override
    public void removeByIds(String[] aryIds, String formKey) {
        IFormDef form = null;// formDefManager.getByFormKey(formKey);
        String boCode = null;// form.getBoCodes();

        //bizDataService.removeBusData(boCode, aryIds);
    }

    @Override
    public JSONArray getList(String formKey, Map<String, Object> param) {
        IFormDef form = null;// formDefManager.getByFormKey(formKey);
        String boCode = null;// form.getBoCodes();

        return null; //bizDataService.getDataList(boCode, param);
    }

}
