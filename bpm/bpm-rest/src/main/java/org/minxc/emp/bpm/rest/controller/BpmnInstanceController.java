package org.minxc.emp.bpm.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.bpm.act.service.BpmImageService;
import org.minxc.emp.bpm.api.engine.data.BpmnFlowDataAccessor;
import org.minxc.emp.bpm.api.engine.data.result.FlowData;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.form.api.model.FormType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;


/**
 * 流程实例 控制器类
 *
 * @author xianchang.min
 * @email bairimeng@summer.com
 * @Time 2018-01-16 22:09:37
 */
@RestController
@RequestMapping("/bpm/instance")
public class BpmnInstanceController extends GenericController {
    @Resource
    BpmnInstanceManager bpmnInstanceManager;
    @Resource
    BpmnFlowDataAccessor bpmnFlowDataAccessor;
    @Resource
    BpmnTaskOpinionManager bpmnTaskOpinionManager;
    @Resource
    BpmImageService bpmImageService;
    @Resource
    BpmnDefinitionManager bpmDefinitionMananger;

    /**
     * 流程实例列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);

        Page<BpmnInstanceImpl> bpmInstanceList = (Page<BpmnInstanceImpl>) bpmnInstanceManager.query(queryFilter);
        return new PageJson(bpmInstanceList);
    }

    /**
     * 流程实例明细页面
     */
    @RequestMapping("getById")
    @ErrorCatching
    public ResultMessage<BpmnInstance> getBpmInstance(@RequestParam String id) throws Exception {
    	BpmnInstance bpmInstance = null;
    	if (StringUtil.isNotEmpty(id)) {
            bpmInstance = bpmnInstanceManager.get(id);
        }

    	return getSuccessResult(bpmInstance);
    }

    /**
     * 获取展示流程实例需要的数据
     *
     * @param request
     * @param response
     * @throws Exception
     * 
     */
    @RequestMapping("getInstanceData")
    @ErrorCatching
    public ResultMessage<FlowData> getInstanceData(HttpServletRequest request) throws Exception {
        String instanceId = request.getParameter("instanceId");
        Boolean readonly = RequestUtil.getBoolean(request, "readonly",false);
        
        String defId = request.getParameter("defId");
        String formType = RequestUtil.getString(request, "formType", FormType.PC.value());
        
        FlowData data = bpmnFlowDataAccessor.getStartFlowData(defId, instanceId, FormType.fromValue(formType),readonly);
        return getSuccessResult(data);
    }

    /**
     * 处理实例类型的动作
     *
     * @param request
     * @param response
     * @throws Exception void
     */
    @RequestMapping("doAction")
    @ErrorCatching
    public ResultMessage<String> doAction(@RequestParam String flowData) throws Exception {

        DefaultInstanceActionCmd instanceCmd = new DefaultInstanceActionCmd(flowData);
        String actionName = instanceCmd.executeCmd();
        
        return getSuccessResult(instanceCmd.getInstanceId(),  actionName + "成功");
    }


    @RequestMapping("getInstanceOpinion")
    @ErrorCatching
    public ResultMessage<List<BpmnTaskOpinion>> getInstanceOpinion(@RequestParam String instId) throws Exception {
        List<BpmnTaskOpinion> taskOpinion = bpmnTaskOpinionManager.getByInstId(instId);

        return getSuccessResult(taskOpinion, "获取流程意见成功");
    }


    /**
     * 读取流程图
     */
    @RequestMapping("flowImage")
    public void flowImage(@RequestParam(required=false) String instId,@RequestParam(required=false) String defId ,HttpServletResponse response) throws Exception {
    	String actDefId,actInstId=null;
    	
    	if(StringUtil.isNotEmpty(instId)) {
    		BpmnInstanceImpl inst = bpmnInstanceManager.get(instId);
    		actInstId = inst.getActInstId();
    		actDefId = inst.getActDefId();
    	}else {
    		BpmnDefinitionImpl def = bpmDefinitionMananger.get(defId);
    		actDefId = def.getActDefId();
    	}
        
        response.setContentType("image/png");
        IOUtils.copy(bpmImageService.draw(actDefId, actInstId), response.getOutputStream());
    }


    @RequestMapping("toForbidden")
    @ErrorCatching("操作失败")
    public ResultMessage<String> toForbidden(@RequestParam String id, @RequestParam Boolean forbidden) throws Exception {
        BpmnInstanceImpl inst = bpmnInstanceManager.get(id);
        String msg = "";

        if (forbidden && inst.getIsForbidden() == 0) {
            inst.setIsForbidden(BpmnInstance.INSTANCE_FORBIDDEN);
            msg = "禁用成功";
        } else if (!forbidden) {
            inst.setIsForbidden(BpmnInstance.INSTANCE_NO_FORBIDDEN);
            msg = "取消禁用成功";
        }

        bpmnInstanceManager.update(inst);
        
        return getSuccessResult(msg);
    }
}
