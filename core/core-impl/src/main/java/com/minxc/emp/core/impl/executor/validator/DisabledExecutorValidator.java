package com.minxc.emp.core.impl.executor.validator;

import org.springframework.stereotype.Service;

/**
 * 不可用 的 校验都不通过
 *
 * @author min.xianchang
 */
@Service
public class DisabledExecutorValidator extends AbstractExecutorValidator {

    @Override
    public String getName() {
        return "不可用的校验器";
    }

    @Override
    public boolean validate(String pluginKey) {
        return false;
    }

}
