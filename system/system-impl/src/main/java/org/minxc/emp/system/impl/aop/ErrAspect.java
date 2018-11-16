package org.minxc.emp.system.impl.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.ExceptionUtil;
import org.minxc.emp.core.util.JacksonUtil;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.impl.manager.LogErrorManager;
import org.minxc.emp.system.impl.model.SystemLogErrorEntity;
import org.minxc.emp.system.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 *  使用AOP拦截出现异常的Controller, service的方法，并反馈标准的异常描述，记录日志
 * 在需要拦截的方法之前添加注解  {@ErrorCatching }
 * 一般使用在【不需要事物控制】的方法中，比如controller或者服务接口
 *eg: 创建用户方法@ErrorCatching 新增的账户存在 则可以throw new BusException("账户已存在");
 * 则前端会接受到这个result的标准json。服务接口也是如此
 * 该方法避免了所有服务接口捕获异常反馈信息的重复操作
 */
@Slf4j
@Aspect
@Component
public class ErrAspect {


    @Resource
    LogErrorManager logErrManager;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Pointcut("@annotation(com.dstz.base.aop.annotion.ErrorCatching)")
    public void controllerAspect() {

    }


    @Around(value = "@annotation(ErrorCatching)")
    public Object doAudit(ProceedingJoinPoint point, ErrorCatching ErrorCatching) throws Throwable {
        Object returnVal = null;
        try {
            returnVal = point.proceed();
        } catch (Exception ex) {
            //如果非业务异常则记录日志
            String error = ExceptionUtil.getRootErrorMessage(ex);
            String exception = ExceptionUtil.getExceptionMessage(ex);

            ResultMessage ResultMessage = null;
            if (!(ex instanceof BusinessException)) {
                log.error("{}.{}出错", point.getTarget().getClass(), point.getSignature().getName(), ex);
                String errorId = logError(point, error, exception);
                error = "errorCode[" + errorId + "] : " + error;
                ResultMessage = new ResultMessage(CommonStatusCode.SYSTEM_ERROR, error);
            } else {
                BusinessException busEx = (BusinessException) ex;
                error = ex.getMessage();
                ResultMessage = new ResultMessage(busEx.getStatusCode(), error);
            }

            writeResultMessage2Writer(point, ResultMessage, ErrorCatching.writeErrorToResponse());

            // 若返回值是resultType 则返回错误
            org.aspectj.lang.Signature signature = point.getSignature();
            Class returnType = ((MethodSignature) signature).getReturnType();
            if (returnType.isAssignableFrom(ResultMessage.class)) {
                returnVal = ResultMessage;
            }
        }

        return returnVal;
    }

    /**
     * 假如是void
     *
     * @param point
     * @param ResultMessage
     * @throws Exception
     */
    private void writeResultMessage2Writer(ProceedingJoinPoint point, ResultMessage ResultMessage, boolean iswrite) throws Exception {
        org.aspectj.lang.Signature signature = point.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        HttpServletResponse response = null;

        Object[] objects = point.getArgs();
        for (Object o : objects) {
            if (o instanceof HttpServletResponse) {
                response = (HttpServletResponse) o;
            }
        }
        //假如http 请求，且void方法时，写入response
        if (void.class.equals(returnType) && response != null) {
            iswrite = true;
        }

        if (!iswrite) return;

        if (response == null) {
            //	response = RequestContext.getHttpServletResponse();
        }

        if (response != null) {
            response.getWriter().write(JSON.toJSONString(ResultMessage));
        }
    }

    @SuppressWarnings("unchecked")
    private String logError(ProceedingJoinPoint point, String error, String exception) {
//        	HttpServletRequest request = RequestContext.getHttpServletRequest();
//            String errorurl = request.getRequestURI();
//	    String ip =RequestUtil.getIpAddr(request);
        User sysUser = ContextUtil.getCurrentUser();
        String account = "未知用户";
        if (BeanUtils.isNotEmpty(sysUser)) {
            account = sysUser.getAccount();
        }
        String id = UniqueIdUtil.getSuid();
        SystemLogErrorEntity logErr = new SystemLogErrorEntity();
        logErr.setId(id);
        logErr.setAccount(account);
        //	logErr.setIp(ip);
        logErr.setContent(error);
//        logErr.setUrl(StringUtils.substring(errorurl, 0, 1000));
        logErr.setCreateTime(new Date());
        logErr.setStackTrace(exception);

        //异步写入到数据库当中
        threadPoolTaskExecutor.execute(()-> logErrManager.create(logErr));

        return id;
    }

}