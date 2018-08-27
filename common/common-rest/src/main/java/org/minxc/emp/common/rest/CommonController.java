package org.minxc.emp.common.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.model.CommonModel;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.minxc.emp.core.util.StringUtil;

/**
 * @version V1.0
 * @Title: CommonController
 * @Package: org.minxc.emp.common.rest
 * @Description:
 * @author: Xianchang.min
 * @date 2018/8/25 20:17
 */

public abstract class CommonController<T extends CommonModel> extends GenericController{
	
	protected abstract String getModelDesc();

    @Autowired
    Manager<String,T> manager;
    
    /**
     * 分页列表
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<T> pageList = (Page<T>) manager.query(queryFilter);
        return new PageJson(pageList);
    }

    /**
     * 获取对象
     */
    @RequestMapping("get")
    @ErrorCatching
    public ResultMessage<T> get(@RequestParam String id) throws Exception {
       T t = manager.get(id);
       return getSuccessResult(t);
    }

    /**
     * 保存
     */
    @RequestMapping("save")
    @ErrorCatching
    public ResultMessage<String> save(@RequestBody T t) throws Exception {
        String desc;
        if (StringUtils.isEmpty(t.getId())) {
            desc = "添加%s成功";
            manager.create(t);
        } else {
            manager.update(t);
            desc = "更新%s成功";
        }
        return getSuccessResult(String.format(desc, getModelDesc()));
    }

    /**
     * 批量删除
     */
    @RequestMapping("remove")
    @ErrorCatching
    public ResultMessage<String> remove(@RequestParam String id) throws Exception {
         String[] aryIds = StringUtil.getStringAryByStr(id);
         manager.removeByIds(aryIds);
         return getSuccessResult(String.format("删除%s成功", getModelDesc()));
    }
}
