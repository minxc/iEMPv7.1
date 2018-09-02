package org.minxc.emp.system.impl.scheduler;

import org.minxc.emp.system.impl.model.SysScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * quartz不允许并发执行
 *
 */
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, SysScheduleJob sysScheduleJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysScheduleJob);
    }
}
