package org.minxc.emp.system.impl.scheduler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

import org.minxc.emp.basis.api.constant.SysStatusCode;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.system.impl.dao.SysScheduleJobDao;
import org.minxc.emp.system.impl.dao.SysScheduleJobLogDao;
import org.minxc.emp.system.impl.model.SystemScheduleJobEntity;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 * 
* 项目名称：system-impl   
* 类名称：QuartzManagerService   
* 类描述： quartz管理业务  
* 创建人：Xianchang.min   
* 创建时间：2018年9月3日 上午12:21:32   
* 修改人：Xianchang.min   
* 修改时间：2018年9月3日 上午12:21:32   
* 修改备注：   
* @version  1.0  
*
 */
@Slf4j
@Component
public class QuartzManagerService implements InitializingBean {

    @Resource
    private SysScheduleJobDao sysScheduleJobDao;

    @Resource
    private SysScheduleJobLogDao sysScheduleJobLogDao;

    @Resource
    private Scheduler scheduler;

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("==> 初始化加载系统执行计划 begin");
        int maxPage = 0, pageNum = 1;
        do {
            PageHelper.startPage(pageNum, 50);
            List<SystemScheduleJobEntity> sysScheduleJobs = sysScheduleJobDao.query();
            //第一次加载
            if (maxPage == 0) {
                maxPage = new PageInfo<>(sysScheduleJobs).getPages();
            }
            for (SystemScheduleJobEntity sysScheduleJob : sysScheduleJobs) {
                refreshScheduleJob(sysScheduleJob);
            }
        } while (++pageNum <= maxPage);
        log.info("==> 初始化加载系统执行计划 end");
    }

    public List<SystemScheduleJobEntity> selectList(QueryFilter queryFilter) {

        return sysScheduleJobDao.query(queryFilter);
    }

    /**
     * 根据id得到系统执行计划
     *
     * @param id 执行计划ID
     * @return 执行计划实体类
     */
    public SystemScheduleJobEntity getSysScheduleJobById(String id) {

        return sysScheduleJobDao.get(id);
    }

    /**
     * 得到quartz任务类
     *
     * @param sysScheduleJob 执行计划
     * @return 具体执行任务类
     */
    private Class<? extends Job> getQuartzJobClass(SystemScheduleJobEntity sysScheduleJob) {

        return sysScheduleJob.getConcurrent() ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
    }


    /**
     * 添加系统执行计划
     *
     * @param sysScheduleJob 系统执行计划
     * @throws SchedulerException 计划执行异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void addSysScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        if (sysScheduleJobDao.exists(sysScheduleJob.getName(), sysScheduleJob.getGroup())) {
            throw new BusinessException("请勿重复添加", SysStatusCode.PARAM_ILLEGAL);
        }
        sysScheduleJobDao.insertSelective(sysScheduleJob);
        //如果添加时是启用状态，往quartz中加入
        if (SysScheduleJobState.ENABLE.name().equals(sysScheduleJob.getRunningState())) {
            refreshScheduleJob(sysScheduleJob);
        }
    }

    /**
     * 更新到quartz任务
     *
     * @param sysScheduleJob 系统执行计划
     */
    @SuppressWarnings("unchecked")
    private void refreshScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        JobKey jobKey = new JobKey(sysScheduleJob.getName(), sysScheduleJob.getGroup());
        //判断是添加还是更新
        if (scheduler.checkExists(jobKey)) {
            //先移除，然后做更新操作，保证每次重启都是最新的值
            scheduler.deleteJob(jobKey);
        }

        //如果启用状态，则添加
        if (SysScheduleJobState.ENABLE.name().equals(sysScheduleJob.getRunningState())) {

            Class<? extends Job> jobClass = getQuartzJobClass(sysScheduleJob);
            JobDataMap jobDataMap = new JobDataMap();
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(sysScheduleJob.getName(), sysScheduleJob.getGroup());
            jobDataMap.put(SchedulerConstants.EXECUTION_TARGET_KEY, sysScheduleJob);
            jobBuilder.usingJobData(jobDataMap);
            JobDetail jobDetail = jobBuilder.build();
            @SuppressWarnings("rawtypes")
			ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysScheduleJob.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);

        }
    }

    /**
     * 移除系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @throws SchedulerException 执行计划异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeSysScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        sysScheduleJobLogDao.removeByJobId(sysScheduleJob.getId());
        sysScheduleJobDao.remove(sysScheduleJob.getId());
        JobKey jobKey = new JobKey(sysScheduleJob.getName(), sysScheduleJob.getGroup());
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 禁用系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @throws SchedulerException 执行计划异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void disableSysScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        SystemScheduleJobEntity entity = new SystemScheduleJobEntity();
        entity.setId(sysScheduleJob.getId());
        entity.setRunningState(SysScheduleJobState.DISABLE.name());
        sysScheduleJobDao.updateByPrimaryKeySelective(entity);

        JobKey jobKey = new JobKey(sysScheduleJob.getName(), sysScheduleJob.getGroup());
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 启用系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @throws SchedulerException 执行计划异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void enableSysScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        SystemScheduleJobEntity entity = new SystemScheduleJobEntity();
        entity.setId(sysScheduleJob.getId());
        entity.setRunningState(SysScheduleJobState.ENABLE.name());
        sysScheduleJobDao.updateByPrimaryKeySelective(entity);

        sysScheduleJob.setRunningState(entity.getRunningState());
        refreshScheduleJob(sysScheduleJob);
    }

    /**
     * 更新系统执行计划
     *
     * @param sysScheduleJob 执行计划
     * @throws SchedulerException 执行计划异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSysScheduleJob(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        SystemScheduleJobEntity oldSysScheduleJob = sysScheduleJobDao.get(sysScheduleJob.getId());
        //如果旧的分组与新的分组不一样
        if (!oldSysScheduleJob.getGroup().equals(sysScheduleJob.getGroup())) {
            if (sysScheduleJobDao.exists(sysScheduleJob.getName(), sysScheduleJob.getGroup())) {
                throw new BusinessException("修改分组后的任务名称冲突，请更换名称", CommonStatusCode.DATA_EXISTS);
            }
        }

        sysScheduleJobDao.updateByPrimaryKeySelective(sysScheduleJob);

        //更新
        refreshScheduleJob(sysScheduleJob);
    }

    /**
     * 执行一次执行计划
     *
     * @param sysScheduleJob 执行计划
     * @throws SchedulerException 执行计划异常
     */
    public void runOnce(SystemScheduleJobEntity sysScheduleJob) throws SchedulerException {
        JobKey jobKey = new JobKey(sysScheduleJob.getName(), sysScheduleJob.getGroup());
        if (!scheduler.checkExists(jobKey)) {
            throw new BusinessException("执行计划未启用", CommonStatusCode.PARAM_ILLEGAL);
        }
        scheduler.triggerJob(jobKey);
    }

    /**
     * 检查是否存在
     *
     * @param name  名称
     * @param group 分组
     * @return true 存在  false 不存在
     */
    public boolean checkExists(String name, String group) {

        return sysScheduleJobDao.exists(name, group);
    }
}
