package org.minxc.emp.core.impl.executor.validator;

import org.springframework.stereotype.Service;

/**
 * 免费的校验器 校验都通过
 *
 * @author min.xianchang
 */
@Service
public class FreeExecutorValidator extends AbstractExecutorValidator {

    @Override
    public String getName() {
        return "免费的校验器";
    }

    @Override
    public boolean validate(String pluginKey) {
        return true;
    }

}
