package org.minxc.emp.form.rest.controller;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.form.manager.FormTemplateManager;
import org.minxc.emp.form.model.FormTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <pre>
 * 描述：自定义对话框管理
 * </pre>
 */
@Controller
@RequestMapping("/form/formTemplate/")
public class FormTemplateController extends GenericController {
    @Autowired
    FormTemplateManager formTemplateManager;

    /**
     * <pre>
     * formTemplateEdit.html的save后端
     * </pre>
     *
     * @param request
     * @param response
     * @param formTemplate
     * @throws Exception
     */
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存自定义对话框失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody FormTemplate formTemplate) throws Exception {
        if (StringUtil.isEmpty(formTemplate.getId())) {
            formTemplate.setEditable(true);// 页面新增的能编辑
            formTemplate.setId(UniqueIdUtil.getSuid());
            formTemplateManager.create(formTemplate);
        } else {
            formTemplateManager.update(formTemplate);
        }
        writeSuccessData(response, formTemplate, "保存自定义对话框成功");
    }

    /**
     * <pre>
     * 获取formTemplate的后端
     * 目前支持根据id 获取formTemplate
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @CatchError(write2response = true, value = "获取formTemplate异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String key = RequestUtil.getString(request, "key");
        FormTemplate formTemplate = null;
        if (StringUtil.isNotEmpty(id)) {
            formTemplate = formTemplateManager.get(id);
        } else if (StringUtil.isNotEmpty(key)) {
            formTemplate = formTemplateManager.getByKey(key);
        }

        writeSuccessData(response, formTemplate);
    }

    /**
     * <pre>
     * list页的后端
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("listJson")
    @ResponseBody
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        List<FormTemplate> list = formTemplateManager.query(queryFilter);
        return new PageJson(list);
    }

    /**
     * <pre>
     * 批量删除
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @CatchError(write2response = true, value = "删除表单模板失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        formTemplateManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除表单模板成功");
    }

    /**
     * <pre>
     * 初始化模板
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("initTemplate")
    @CatchError(write2response = true, value = "初始化模板失败")
    public void initTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        formTemplateManager.initAllTemplate();
        writeSuccessResult(response, "初始化模板成功");
    }
}
