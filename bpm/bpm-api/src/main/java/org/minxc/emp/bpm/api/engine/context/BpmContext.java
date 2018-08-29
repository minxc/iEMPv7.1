package org.minxc.emp.bpm.api.engine.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;

/**
 * 线程变量管理类
 *
 * @author jeff
 */
public class BpmContext {

    /**
     * 在activiti监听事件中获取线程数据
     * 此时数据无法传递
     */
    private static ThreadLocal<Stack<ActionCmd>> threadActionModel = new ThreadLocal<>();
    /**
     * 流程定义的线程缓存,减少序列化（往往一个启动任务至少需要几十次获取流程定义的配置情况，故此做线程缓存）
     */
    private static ThreadLocal<Map<String, BpmProcessDef>> threadBpmProcessDef = new ThreadLocal<>();

    public static void setActionModel(ActionCmd actionModel) {
        getStack(threadActionModel).push(actionModel);
    }
    
    /**
     * 获取当前处理中的actionCmd
     * @return
     */
    public static ActionCmd getActionModel() {
        Stack<ActionCmd> stack = getStack(threadActionModel);
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }
    
    /**
     * 获取提交的actionCmd
     * @return
     */
    public static ActionCmd submitActionModel() {
        Stack<ActionCmd> stack = getStack(threadActionModel);
        if (stack.isEmpty()) {
            return null;
        }
        return stack.firstElement();
    }
    
    public static void removeActionModel() {
        Stack stack = getStack(threadActionModel);
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public static BpmProcessDef getProcessDef(String defId) {
        Map<String, BpmProcessDef> map = getThreadMap(threadBpmProcessDef);
        return map.get(defId);
    }

    public static void addProcessDef(String defId, BpmProcessDef processDef) {
        getThreadMap(threadBpmProcessDef).put(defId, processDef);
    }


    /**
     * 清除所有的线程变量。
     * void
     */
    public static void cleanTread() {
        threadActionModel.remove();
        threadBpmProcessDef.remove();
    }

    protected static <T> Stack<T> getStack(ThreadLocal<Stack<T>> threadLocal) {
        Stack<T> stack = threadLocal.get();
        if (stack == null) {
            stack = new Stack<T>();
            threadLocal.set(stack);
        }
        return stack;
    }

    protected static <T> Map<String, T> getThreadMap(ThreadLocal<Map<String, T>> threadMap) {
        Map<String, T> processDefMap = threadMap.get();
        if (processDefMap == null) {
            processDefMap = new HashMap<String, T>();
            threadMap.set(processDefMap);
        }

        return processDefMap;
    }

}
