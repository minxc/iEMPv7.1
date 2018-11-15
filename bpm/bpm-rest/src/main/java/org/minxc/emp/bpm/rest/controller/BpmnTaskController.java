package org.minxc.emp.bpm.rest.controller;


import com.github.pagehelper.Page;

import org.minxc.emp.bpm.api.engine.data.BpmnFlowDataAccessor;
import org.minxc.emp.bpm.api.engine.data.result.FlowData;
import org.minxc.emp.bpm.core.manager.BpmnTaskManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.form.api.model.FormType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 流程任务 控制器类
 *
 * @author xianchang.min
 * @email bairimeng@summer.com
 * @Time 2018-01-16 00:03:46
 */
@RestController
@RequestMapping("/bpm/task")
public class BpmnTaskController extends GenericController {
    @Resource
    BpmnTaskManager bpmnTaskManager;

    @Resource
    BpmnFlowDataAccessor bpmnFlowDataAccessor;

    /**
     * 流程任务列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);

        Page<BpmnTaskImpl> bpmnTaskList = (Page) bpmnTaskManager.query(queryFilter);
        return new PageJson(bpmnTaskList);
    }


    /**
     * 流程任务明细页面
     */
    @RequestMapping("getBpmTask")
    @ErrorCatching
    public ResultMessage<BpmnTaskImpl> getBpmTask(@RequestParam String id) throws Exception {
    	BpmnTaskImpl bpmnTask = null ;
        if (StringUtil.isNotEmpty(id)) {
        	bpmnTask = bpmnTaskManager.get(id);
        }

        return getSuccessResult(bpmnTask);
    }

    /**
     * 批量删除流程任务记录
     */
    @RequestMapping("remove")
    @ErrorCatching("删除流程任务失败")
    public ResultMessage<String> remove(@RequestParam String id) throws Exception {
        String[] aryIds = StringUtil.getStringAryByStr(id);

        bpmnTaskManager.removeByIds(aryIds);
        
        return getSuccessResult("删除流程任务成功");
    }


    @RequestMapping("getTaskData")
    @ErrorCatching
    public ResultMessage<FlowData> getTaskData(@RequestParam String taskId,@RequestParam(required=false) String formType) throws Exception {
    	if(StringUtil.isEmpty(formType)) {
    		formType = FormType.PC.value();
    	}
    	
        FlowData data = bpmnFlowDataAccessor.getFlowTaskData(taskId, FormType.fromValue(formType));
        return getSuccessResult(data);
    }

    /**
     * 处理任务类型的动作
     *
     * @param request
     * @param response
     * @throws Exception void
     */
    @RequestMapping("doAction")
    @ErrorCatching
    public ResultMessage<String> doAction(@RequestParam String flowData) throws Exception {
        DefualtTaskActionCmd taskModel = new DefualtTaskActionCmd(flowData);

        String actionName = taskModel.executeCmd();
        return getSuccessResult(String.format("执行%s操作成功", actionName));
    }
    
    
    
    
    
 
    @RequestMapping("unLock")
    @ErrorCatching
    public ResultMessage<String> unLock(@RequestParam String taskId) throws Exception {
        bpmnTaskManager.unLockTask(taskId);
        return getSuccessResult("取消指派成功");
    }
    
    @RequestMapping("assignTask")
    @ErrorCatching
    public ResultMessage<String> assignTask(@RequestParam String taskId,@RequestParam String userName,@RequestParam String userId) throws Exception {
        bpmnTaskManager.assigneeTask(taskId, userId, userName);
        return getSuccessResult("指派成功");
    }
}
