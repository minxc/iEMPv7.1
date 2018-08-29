package org.minxc.emp.bpm.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import com.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;


/**
 * 流程定义 控制器类
 *
 *
 */
@RestController
@RequestMapping("/bpm/definition")
public class BpmDefinitionController extends BaseController<BpmDefinition> {
    @Resource
   private  BpmDefinitionManager bpmDefinitionManager;

    /**
     * 流程定义列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     */
    @Override
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        queryFilter.addFilter("is_main_", "Y", QueryOperation.EQUAL);
        // 查询列表
        List<BpmDefinition> bpmDefinitionList = bpmDefinitionManager.query(queryFilter);

        return new PageJson(bpmDefinitionList);
    }
    
    @RequestMapping("save")
    @CatchError(write2response = true, value = "保存流程定义失败")
    public ResultMsg<String> save(@RequestBody BpmDefinition bpmDefinition) throws Exception {
    	
        bpmDefinitionManager.create(bpmDefinition);
        
        return this.getSuccessResult(bpmDefinition.getActModelId(), "创建成功");
    }
    

    @RequestMapping("clearSysCache")
    @CatchError("清除缓存失败")
    public ResultMsg<String> clearCache() throws Exception {
    	AppUtil.getCache().clearAll();
    	return getSuccessResult("成功清除所有系统缓存");
    }

	@Override
	protected String getModelDesc() {
		return "流程定义";
	}
    
}
