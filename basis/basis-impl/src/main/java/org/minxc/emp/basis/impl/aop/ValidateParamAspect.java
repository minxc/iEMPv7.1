package org.minxc.emp.basis.impl.aop;

import com.alibaba.fastjson.JSON;
import com.minxc.emp.core.util.JacksonUtil;
//import com.dstz.base.api.aop.annotion.ParamValidate;
import com.minxc.emp.core.util.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 *
 * @author jeff
 * @date 2017-06-09
 */
@Aspect
@Component
public class ValidateParamAspect {

    private Log logger = LogFactory.getLog(ValidateParamAspect.class);

    @Around(value = "@annotation(paramValidate)")
    public Object doAround(ProceedingJoinPoint pjp, ParameterValidation paramValidate) throws Throwable {
        Object result;
        Object[] objects = pjp.getArgs();
//        logger.debug("参数拦截开始=====" + JSON.toJSONString(objects));
        logger.debug("参数拦截开始=====" + JacksonUtil.pojo2Json(objects));
        for (Object o : objects) {
            String msg = ValidateUtil.getValidateMsg(o);
            if (StringUtils.isNotEmpty(msg)) {
                logger.error("参数拦截信息" + msg);
                return getResult(pjp, msg);
            }
        }
        logger.debug("======参数拦截结束=====");
        result = pjp.proceed();
        return result;
    }


    private BaseResult getResult(ProceedingJoinPoint point, String error) {
        org.aspectj.lang.Signature signature = point.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();

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
