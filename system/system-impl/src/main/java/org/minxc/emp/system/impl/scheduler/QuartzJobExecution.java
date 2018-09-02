package org.minxc.emp.system.impl.scheduler;

import org.minxc.emp.system.impl.model.SysScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * quartz任务执行
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SysScheduleJob sysScheduleJob)throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
