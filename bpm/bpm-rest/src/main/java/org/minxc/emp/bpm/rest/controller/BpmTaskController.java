package org.minxc.emp.bpm.rest.controller;


import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.bpm.api.engine.data.BpmnFlowDataAccessor;
import org.minxc.emp.bpm.api.engine.data.result.FlowData;
import org.minxc.emp.bpm.core.manager.BpmTaskManager;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
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
 * @author jeff
 * @email bairimeng@summer.com
 * @Time 2018-01-16 00:03:46
 */
@RestController
@RequestMapping("/bpm/task")
public class BpmTaskController extends GenericController {
    @Resource
    private BpmTaskManager bpmTaskManager;

    @Resource
    private BpmnFlowDataAccessor bpmFlowDataAccessor;

    /**
     * 流程任务列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);

        Page<BpmTask> bpmTaskList = (Page) bpmTaskManager.query(queryFilter);
        return new PageJson(bpmTaskList);
    }


    /**
     * 流程任务明细页面
     */
    @RequestMapping("getBpmTask")
    @CatchError
    public ResultMsg<BpmTask> getBpmTask(@RequestParam String id) throws Exception {
    	BpmTask bpmTask = null ;
        if (StringUtil.isNotEmpty(id)) {
        	bpmTask = bpmTaskManager.get(id);
        }

        return getSuccessResult(bpmTask);
    }

    /**
     * 批量删除流程任务记录
     */
    @RequestMapping("remove")
    @CatchError("删除流程任务失败")
    public ResultMsg<String> remove(@RequestParam String id) throws Exception {
        String[] aryIds = StringUtil.getStringAryByStr(id);

        bpmTaskManager.removeByIds(aryIds);
        
        return getSuccessResult("删除流程任务成功");
    }


    @RequestMapping("getTaskData")
    @CatchError
    public ResultMsg<FlowData> getTaskData(@RequestParam String taskId,@RequestParam(required=false) String formType) throws Exception {
    	if(StringUtil.isEmpty(formType)) {
    		formType = FormType.PC.value();
    	}
    	
        FlowData data = bpmFlowDataAccessor.getFlowTaskData(taskId, FormType.fromValue(formType));
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
    @CatchError
    public ResultMsg<String> doAction(@RequestParam String flowData) throws Exception {
        DefualtTaskActionCmd taskModel = new DefualtTaskActionCmd(flowData);

        String actionName = taskModel.executeCmd();
        return getSuccessResult(String.format("执行%s操作成功", actionName));
    }
    
    
    
    
    
 
    @RequestMapping("unLock")
    @CatchError
    public ResultMsg<String> unLock(@RequestParam String taskId) throws Exception {
        bpmTaskManager.unLockTask(taskId);
        return getSuccessResult("取消指派成功");
    }
    
    @RequestMapping("assignTask")
    @CatchError
    public ResultMsg<String> assignTask(@RequestParam String taskId,@RequestParam String userName,@RequestParam String userId) throws Exception {
        bpmTaskManager.assigneeTask(taskId, userId, userName);
        return getSuccessResult("指派成功");
    }
}
