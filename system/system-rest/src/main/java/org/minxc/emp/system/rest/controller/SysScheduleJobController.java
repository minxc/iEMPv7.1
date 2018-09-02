package org.minxc.emp.system.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.QueryOperator;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.system.impl.manager.SysScheduleJobLogManager;
import org.minxc.emp.system.impl.model.SysScheduleJob;
import org.minxc.emp.system.impl.model.SysScheduleJobLog;
import org.minxc.emp.system.impl.scheduler.QuartzManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统任务计划
 *
 * @author didi
 */
@RestController
@RequestMapping("/sys/scheduleJob")
public class SysScheduleJobController extends GenericController {

    private final Logger LOGGER = LoggerFactory.getLogger(SysScheduleJobController.class);

    @Resource
    private QuartzManagerService quartzManagerService;

    @Resource(name = "sysScheduleJobLogManager")
    private SysScheduleJobLogManager sysScheduleJobLogManager;

    /**
     * 查询列表
     */
    @RequestMapping("list")
    public PageJson list(HttpServletRequest request) {
        QueryFilter queryFilter = getQueryFilter(request);
        return new PageJson(quartzManagerService.selectList(queryFilter));
    }

    /**
     * 根据系统执行计划ID得到
     *
     * @param id 执行计划ID
     */
    @RequestMapping("get")
    public ResultMessage<SysScheduleJob> get(@RequestParam("id") String id) {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        return new ResultMessage<>(sysScheduleJob);
    }

    /**
     * 修改系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @return
     */
    @PostMapping("edit")
    public ResultMessage<String> edit(@RequestBody SysScheduleJob sysScheduleJob) {
        try {
            if (StringUtils.isEmpty(sysScheduleJob.getId())) {
                quartzManagerService.addSysScheduleJob(sysScheduleJob);
            } else {
                quartzManagerService.updateSysScheduleJob(sysScheduleJob);
            }
            ResultMessage<String> ResultMessage = new ResultMessage<>(sysScheduleJob.getId());
            ResultMessage.setMessage("操作成功");
            return ResultMessage;
        } catch (BusinessException e) {

            return new ResultMessage<>(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new ResultMessage<>(CommonStatusCode.SYSTEM_ERROR, CommonStatusCode.SYSTEM_ERROR.getDescription());
        }
    }

    /**
     * 检查是否存在
     *
     * @param name  计划名称
     * @param group 计划分组
     * @return
     */
    @PostMapping("checkExists")
    public boolean checkExists(@RequestParam(name = "name") String name, @RequestParam("group") String group) {

        return quartzManagerService.checkExists(name, group);
    }

    /**
     * 启用任务计划
     *
     * @param id
     * @param enable
     * @return
     * @throws Exception
     */
    @ErrorCatching(writeErrorToResponse = true)
    @RequestMapping("enable")
    public ResultMessage<Void> enable(@RequestParam("id") String id, @RequestParam("enable") boolean enable) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        if (enable) {
            quartzManagerService.enableSysScheduleJob(sysScheduleJob);
        } else {
            quartzManagerService.disableSysScheduleJob(sysScheduleJob);
        }

        return new ResultMessage<>(null);
    }

    /**
     * 立即运行一次
     *
     * @param id 执行计划ID
     * @return
     * @throws Exception
     */
    @RequestMapping("runOnce")
    @ErrorCatching(writeErrorToResponse = true)
    public ResultMessage<?> runOnce(@RequestParam("id") String id) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        quartzManagerService.runOnce(sysScheduleJob);
        return new ResultMessage<>();
    }

    /**
     * 移除
     *
     * @param id 执行计划ID
     * @return
     * @throws Exception
     */
    @RequestMapping("remove")
    @ErrorCatching(writeErrorToResponse = true)
    public ResultMessage<?> remove(@RequestParam("id") String id) throws Exception {
        SysScheduleJob sysScheduleJob = quartzManagerService.getSysScheduleJobById(id);
        quartzManagerService.removeSysScheduleJob(sysScheduleJob);
        return new ResultMessage<>();
    }

    /**
     * 执行计划日志列表
     *
     * @param jobId   任务编号
     * @param request
     * @return
     */
    @RequestMapping("log/list")
    @ErrorCatching(writeErrorToResponse = true)
    public PageJson listSysScheduleJobLog(@RequestParam("jobId") String jobId, HttpServletRequest request) {
        QueryFilter queryFilter = getQueryFilter(request);
        queryFilter.addFilter("job_id", jobId, QueryOperator.EQUAL);
        return new PageJson(sysScheduleJobLogManager.query(queryFilter));
    }

    /**
     * 执行计划详细日志
     * @param id
     *          日志ID
     * @return
     */
    @RequestMapping("log/detail")
    @ErrorCatching(writeErrorToResponse = true)
    public ResultMessage<?> getLogDetail(@RequestParam("id")String id){
       SysScheduleJobLog sysScheduleJobLog = sysScheduleJobLogManager.get(id);
       return new ResultMessage<>(sysScheduleJobLog);
    }
}
