package org.minxc.emp.basis.impl.scheduler;

import org.minxc.emp.basis.impl.core.manager.SysScheduleJobLogManager;
import org.minxc.emp.basis.impl.core.model.SysScheduleJob;
import org.minxc.emp.basis.impl.core.model.SysScheduleJobLog;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.ExceptionUtil;

import groovy.util.logging.Slf4j;

import java.util.Date;

/**
 * 抽象quartz调用
 *
 */

@Slf4j
public abstract class AbstractQuartzJob implements Job {

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysScheduleJob sysScheduleJob = (SysScheduleJob) context.getMergedJobDataMap().get(SchedulerConstants.EXECUTION_TARGET_KEY);
        try {
            before(context, sysScheduleJob);
            if (sysScheduleJob != null) {
                doExecute(context, sysScheduleJob);
            }
            after(context, sysScheduleJob, null);
        } catch (Exception e) {
            after(context, sysScheduleJob, e);
        }
    }

    /**
     * 执行前
     *
     * @param context        工作执行上下文对象
     * @param sysScheduleJob 系统计划任务
     */
    protected void before(JobExecutionContext context, SysScheduleJob sysScheduleJob) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context        工作执行上下文对象
     * @param sysScheduleJob 系统计划任务
     */
    protected void after(JobExecutionContext context, SysScheduleJob sysScheduleJob, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysScheduleJobLog sysScheduleJobLog = new SysScheduleJobLog();
        sysScheduleJobLog.setJobId(sysScheduleJob.getId());
        sysScheduleJobLog.setStartTime(startTime);
        sysScheduleJobLog.setEndTime(new Date());
        sysScheduleJobLog.setRunMs(sysScheduleJobLog.getEndTime().getTime() - sysScheduleJobLog.getStartTime().getTime());
        if (e != null) {
            sysScheduleJobLog.setRunState("FAIL");
            sysScheduleJobLog.setContent(ExceptionUtil.getExceptionMessage(e));
        } else {
            sysScheduleJobLog.setRunState("SUC");
        }

        //异步写入数据库当中
        AppContextUtil.getBean(SysScheduleJobLogManager.class).insertSelective(sysScheduleJobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context        工作执行上下文对象
     * @param sysScheduleJob 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysScheduleJob sysScheduleJob) throws Exception;
}
