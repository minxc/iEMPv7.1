package org.minxc.emp.basis.impl.simplemq.handler.msg;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.api.jms.model.msg.NotifyMessage;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 短消息发送处理器。
 */
@Slf4j
@Component
public class SmsHandler extends AbsNotifyMessageHandler<NotifyMessage> {

    @Override
    public String getType() {
        return "sms";
    }

    @Override
    public boolean sendMessage(NotifyMessage message) {
    	
        // 调用阿里大于
        List<User> recievers =null; message.getReceivers();
        String content = message.getTextContent();
        String templateCode = null;//message.getSmsTemplateNo();

        if (StringUtils.isEmpty(content) || BeanUtils.isEmpty(recievers)) return false;


        for (User user : recievers) {
            if (StringUtils.isEmpty(user.getMobile())) continue;
			
	/*		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend(alidayuSetting.getExtend());
			req.setSmsType("normal");
			req.setSmsFreeSignName(alidayuSetting.getFreeSignName());
			
			String parmString =TaoBaoUtil.buildParams(vo);
			req.setSmsParamString(parmString);
			req.setRecNum(user.getMobile());
			req.setSmsTemplateCode(templateCode);
			try {
				AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			} catch (ApiException e) {
				e.printStackTrace();
			}*/
        }
        return true;
    }

    @Override
    public String getTitle() {
        return "短信";
    }

    @Override
    public boolean getIsDefault() {
        return false;
    }

    @Override
    public boolean getSupportHtml() {
        return true;
    }

}
