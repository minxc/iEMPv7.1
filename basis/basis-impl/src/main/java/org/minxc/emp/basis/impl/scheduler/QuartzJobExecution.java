package org.minxc.emp.basis.impl.scheduler;

import org.minxc.emp.basis.impl.core.model.SysScheduleJob;
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
