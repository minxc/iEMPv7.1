package org.minxc.emp.system.impl.scheduler;

import org.minxc.emp.system.impl.model.SystemScheduleJobEntity;
import org.quartz.JobExecutionContext;

/**
 * quartz不允许并发执行
 *
 */
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SystemScheduleJobEntity sysScheduleJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
