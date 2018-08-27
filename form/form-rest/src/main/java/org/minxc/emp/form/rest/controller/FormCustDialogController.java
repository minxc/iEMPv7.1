package org.minxc.emp.form.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.dao.CommonDao;
import org.minxc.emp.base.db.datasource.DbContextHolder;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.form.manager.FormCustDialogManager;
import org.minxc.emp.form.model.FormCustDialog;
import org.minxc.emp.system.api2.model.SystemDataSource;
import org.minxc.emp.system.api2.service.SystemDataSourceService;

/**
 * <pre>
 * 描述：自定义对话框管理
 * </pre>
 */
@RestController
@RequestMapping("/form/formCustDialog/")
public class FormCustDialogController extends BaseController<FormCustDialog> {
    @Autowired
   private  FormCustDialogManager formCustDialogManager;
    @Autowired
    private  CommonDao<?> commonDao;
    
    @Autowired
    private SystemDataSourceService sysDataSourceService;

    /**
     * <pre>
     * 获取formCustDialog的后端
     * 目前支持根据id 获取formCustDialog
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getObject")
    @CatchError(write2response = true, value = "获取formCustDialog异常")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        String key = RequestUtil.getString(request, "key");
        FormCustDialog formCustDialog = null;
        if (StringUtil.isNotEmpty(id)) {
            formCustDialog = formCustDialogManager.get(id);
        } else if (StringUtil.isNotEmpty(key)) {
            formCustDialog = formCustDialogManager.getByKey(key);
        }

        writeSuccessData(response, formCustDialog);
    }

    /**
     * <pre>
     * edit页面的searchObjName
     * 其他页面使用可以参数传一个json:
     * {dsKey:"数据源key",objType:"枚举在 org.minxc.emp.form.api.model.FormCustDialogOBJTYPE ",objName:"要查询的名字"}
     * </pre>
     *
     * @param request
     * @param response
     * @param formCustDialog
     * @throws Exception
     */
    @RequestMapping("searchObjName")
    @CatchError(write2response = true, value = "根据数据源获取objName信息失败")
    public void searchObjName(HttpServletRequest request, HttpServletResponse response, @RequestBody FormCustDialog formCustDialog) throws Exception {
        writeSuccessData(response, formCustDialogManager.searchObjName(formCustDialog), "根据数据源获取objName信息成功");
    }

    /**
     * <pre>
     * 获取objName对象的信息
     * 其他页面使用可以参数传一个json:
     * {dsKey:"数据源key",objType:"枚举在org.minxc.emp.form.model.FormCustDialog$OBJTYPE",objName:"要查询的名字"}
     * </pre>
     *
     * @param request
     * @param response
     * @param formCustDialog
     * @throws Exception
     */
    @RequestMapping("getTable")
    @CatchError(write2response = true, value = "根据数据源获取objName的字段信息失败")
    public void getTable(HttpServletRequest request, HttpServletResponse response, @RequestBody FormCustDialog formCustDialog) throws Exception {
        writeSuccessData(response, formCustDialogManager.getTable(formCustDialog), "根据数据源获取objName的字段信息成功");
    }

    /**
     * <pre>
     * 获取对话框的列表数据后端
     * </pre>
     *
     * @param request
     * @param response
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("listData_{key}")
    @CatchError(write2response = true, value = "获取对话框的列表数据失败")
    public PageJson listData(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "key") String key) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        // 页面来的参数
        FormCustDialog formCustDialog = formCustDialogManager.getByKey(key);
        SystemDataSource sysDataSource = sysDataSourceService.getByKey(formCustDialog.getDsKey());
        // 切换数据源
        DbContextHolder.setDataSource(sysDataSource.getKey(), sysDataSource.getDbType());
        List<?> list = formCustDialogManager.data(formCustDialog, queryFilter);
        return new PageJson(list);
    }

    /**
     * <pre>
     * 获取对话框的树数据后端
     * </pre>
     *
     * @param request
     * @param response
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("treeData_{key}")
    public List<?> treeData(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "key") String key) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        // 页面来的参数
        FormCustDialog formCustDialog = formCustDialogManager.getByKey(key);
        SystemDataSource sysDataSource = sysDataSourceService.getByKey(formCustDialog.getDsKey());
        // 切换数据源
        DbContextHolder.setDataSource(sysDataSource.getKey(), sysDataSource.getDbType());
        return formCustDialogManager.data(formCustDialog, queryFilter);
    }

    @Override
    protected String getModelDesc() {
        return "自定义对话框";
    }

}
