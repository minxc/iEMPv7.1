package org.minxc.emp.system.impl.aop;


import com.alibaba.fastjson.JSON;
import com.minxc.emp.core.util.JacksonUtil;
import com.minxc.emp.core.util.ValidateUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.minxc.emp.core.api.aop.annotation.ParameterValidation;
import org.minxc.emp.core.api.response.impl.BaseResult;
import org.minxc.emp.core.api.response.impl.PageResult;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.springframework.stereotype.Component;

/**
 * 提供接口响应aop拦截
 * @date 2017-06-09
 */
@Aspect
@Component
@Slf4j
public class ValidateParamAspect {


    @Around(value = "@annotation(paramValidate)")
    public Object doAround(ProceedingJoinPoint pjp, ParameterValidation paramValidate) throws Throwable {
        Object result;
        Object[] objects = pjp.getArgs();
        log.debug("参数拦截开始=====" + JSON.toJSONString(objects));
        for (Object o : objects) {
            String msg = ValidateUtil.getValidateMsg(o);
            if (StringUtils.isNotEmpty(msg)) {
            	log.error("参数拦截信息" + msg);
                return getResult(pjp, msg);
            }
        }
        log.debug("======参数拦截结束=====");
        result = pjp.proceed();
        return result;
    }


    private BaseResult getResult(ProceedingJoinPoint point, String error) {
        org.aspectj.lang.Signature signature = point.getSignature();
        Class<?> returnType = ((MethodSignature) signature).getReturnType();

        //只有两种result类型
        BaseResult res;
        if (PageResult.class.equals(returnType)) {
            res = new PageResult();
        } else {
            res = new ResultMessage();
        }
        res.setSuccess(false);
        res.setMessage(error);
        return res;
    }

}
