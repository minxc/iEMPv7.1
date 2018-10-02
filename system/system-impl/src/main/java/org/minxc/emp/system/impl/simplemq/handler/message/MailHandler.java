package org.minxc.emp.system.impl.simplemq.handler.message;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.api.jms.model.msg.NotifyMessage;
import org.minxc.emp.basis.impl.simplemq.handler.msg.AbsNotifyMessageHandler;
import org.minxc.emp.basis.impl.util.EmailUtil;
import org.minxc.emp.core.util.JacksonUtil;
import org.minxc.emp.core.util.PropertiesUtil;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.api.service.SysIdentityConvert;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件消息处理器。
 */
@Slf4j
@Component
public class MailHandler extends AbsNotifyMessageHandler<NotifyMessage> {
	@Resource
	SysIdentityConvert sysIdentityConvert;
	
    @Override
    public String getType() {
        return "email";
    }


    @Override
    public String getTitle() {
        return "邮件";
    }


    @Override
    public boolean getSupportHtml() {
        return true;
    }

	@Override
	public boolean sendMessage(NotifyMessage notifMessage) {
		String fromEmail = PropertiesUtil.getProperty("mail.address");
	  
        List<User> recievers = sysIdentityConvert.convert2Users(notifMessage.getReceivers());
        
        for (User reciver : recievers) {
            String email = reciver.getEmail();
            if (StringUtils.isEmpty(email)) continue;
            try {
                EmailUtil.sendEmail(email, "", "", fromEmail, notifMessage.getSubject(), notifMessage.getHtmlContent());
            } catch (MessagingException e) {
            	log.error(JSON.toJSONString(notifMessage));
            	log.error("发送邮件失败！",e);
            }
        }
        log.debug("发送邮件成功 ：{}",JSON.toJSONString(notifMessage));
        return true;
	}

}
