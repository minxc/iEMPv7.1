package org.minxc.emp.bpm.api.engine.action.button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.engine.action.handler.ActionHandler;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.Button;

/**
 * 通过actionhandle生成默认可配置的 button
 *
 * @author min.xianchang
 */
public class ButtonFactory {
    /**
     * 获取按钮，并按照当前节点和任务状态过滤按钮
     *
     * @param bpmNodeDef 流程节点
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Button> generateButtons(BpmNodeDef nodeDef, boolean isDefault) throws ClassNotFoundException {
        NodeProperties prop = nodeDef.getNodeProperties();

        List<Button> btns = new ArrayList<Button>();

        Map<String, ActionHandler> actionMap = AppContextUtil.getImplInstance(ActionHandler.class);

        List<ActionHandler> list = new ArrayList<ActionHandler>(actionMap.values());
        sortActionList(list);

        for (ActionHandler actionHandler : list) {

            if (isDefault && !actionHandler.isDefault()) {
                continue;
            }

            if (!actionHandler.isSupport(nodeDef)) {
                continue;
            }

            ActionType actionType = actionHandler.getActionType();
            Button button = new Button(actionType.getName(), actionType.getKey(), actionHandler.getDefaultGroovyScript(), actionHandler.getConfigPage());
            button.setBeforeScript(actionHandler.getDefaultBeforeScript());

            btns.add(button);
        }

        return btns;

    }

    private static void sortActionList(List<ActionHandler> list) {
        Collections.sort(list, new Comparator<ActionHandler>() {
            @Override
            public int compare(ActionHandler o1, ActionHandler o2) {
                return o1.getSn() - o2.getSn();
            }
        });
    }

    public static List<Button> getInstanceButtons() {
        List<Button> btns = new ArrayList<Button>();

        Map<String, ActionHandler> actionMap = AppContextUtil.getImplInstance(ActionHandler.class);
        List<ActionHandler> list = new ArrayList<ActionHandler>(actionMap.values());

        for (ActionHandler actionHandler : list) {
            ActionType actionType = actionHandler.getActionType();
            ActionType[] actions = {ActionType.FLOWIMAGE, ActionType.PRINT, ActionType.TASKOPINION};
            List<ActionType> bbbb = Arrays.asList(actions);
            if (!bbbb.contains(actionType)) {
                continue;
            }

            Button button = new Button(actionType.getName(), actionType.getKey(), actionHandler.getDefaultGroovyScript(), actionHandler.getConfigPage());
            button.setBeforeScript(actionHandler.getDefaultBeforeScript());

            btns.add(button);
        }

        return btns;
    }

}
