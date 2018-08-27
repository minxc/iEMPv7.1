package org.minxc.emp.security.processor;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: EmpCompositeObjectPostProcessor
 * @Package: org.minxc.emp.security.processor
 * @Description: TODO:ObjectPostProcessor 解决自定义Security配置缺少ObjectPostProcessor的问题
 * 禁用Spring boot 的安全自动配置，启用自定义配置，省略该类作为一个必须配置项
 * @author: Xianchang.min
 * @date 2018/8/19 21:29
 * @version V1.0
 */

//@Component
public class EmpCompositeObjectPostProcessor implements
        ObjectPostProcessor<Object> {


    private List<ObjectPostProcessor<? extends Object>> postProcessors = new ArrayList<ObjectPostProcessor<?>>();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object postProcess(Object object) {
        for (ObjectPostProcessor opp : postProcessors) {
            Class<?> oppClass = opp.getClass();
            Class<?> oppType = GenericTypeResolver.resolveTypeArgument(oppClass,
                    ObjectPostProcessor.class);
            if (oppType == null || oppType.isAssignableFrom(object.getClass())) {
                object = opp.postProcess(object);
            }
        }
        return object;
    }

    /**
     * Adds an {@link ObjectPostProcessor} to use
     * @param objectPostProcessor the {@link ObjectPostProcessor} to add
     * @return true if the {@link ObjectPostProcessor} was added, else false
     */
    public boolean addObjectPostProcessor(
            ObjectPostProcessor<? extends Object> objectPostProcessor) {
        boolean result = this.postProcessors.add(objectPostProcessor);
        Collections.sort(postProcessors, AnnotationAwareOrderComparator.INSTANCE);
        return result;
    }
}
