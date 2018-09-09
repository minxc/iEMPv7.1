package org.minxc.emp.system.impl.scheduler;

import org.minxc.emp.system.impl.model.SystemScheduleJobEntity;
import org.quartz.JobExecutionContext;

/**
 * quartz任务执行
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SystemScheduleJobEntity sysScheduleJob)throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
