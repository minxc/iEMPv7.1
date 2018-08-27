package com.minxc.emp.core.impl.executor.validator;

import com.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.api.executor.validator.ExecutorValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 执行器校验器的服务类
 *
 * @author min.xianchang
 */
public class ExecutorValidatorService {
    /**
     * 校验器map
     */
    private static Map<String, ExecutorValidator> checkerMap = new HashMap<>();

    /**
     * 私有化构建方法
     */
    private ExecutorValidatorService() {

    }

    /**
     * <pre>
     * 获取指定校验器
     * </pre>
     *
     * @param key
     * @return
     */
    public static ExecutorValidator getChecker(String key) {
        //初始化校验器
        if (checkerMap.isEmpty()) {
            Map<String, ExecutorValidator> map = AppContextUtil.getImplInstance(ExecutorValidator.class);
            for (Entry<String, ExecutorValidator> entry : map.entrySet()) {
                ExecutorValidator checker = entry.getValue();
                checkerMap.put(checker.getKey(), checker);
            }
        }
        if (checkerMap.get(key) == null) {
            throw new RuntimeException("找不到执行器校验器[" + key + "]");
        } else {
            return checkerMap.get(key);
        }
    }

    public static List<ExecutorValidator> getCheckers(String keys) {
        List<ExecutorValidator> checkers = new ArrayList<>();
        for (String key : keys.split(",")) {
            checkers.add(getChecker(key));
        }
        return checkers;
    }
}
