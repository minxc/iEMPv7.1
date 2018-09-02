package org.minxc.emp.system.impl.simplemq.handler.message;

import org.minxc.emp.basis.api.jms.model.msg.NotifyMessage;
import org.minxc.emp.basis.impl.simplemq.handler.msg.AbsNotifyMessageHandler;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Component;


/**
 * 内部消息处理器。
 */
@Component("innerHandler")
public class InnerHandler extends AbsNotifyMessageHandler<NotifyMessage> {

    @Override
    public String getType() {
        return "inner";
    }


    public String getTitle() {
        return "内部消息";
    }


    public boolean getIsDefault() {
        return true;
    }


    public boolean getSupportHtml() {
        return false;
    }

	public boolean handlerMessage() {
		return false;
	}

	@Override
	public boolean sendMessage(NotifyMessage message) {
		  if (1 == 1) return false;
	        User sender = ContextUtil.getCurrentUser();
	        return false;
	}

}
