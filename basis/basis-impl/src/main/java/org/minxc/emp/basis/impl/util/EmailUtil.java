package org.minxc.emp.basis.impl.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.impl.email.MailUtil;
import org.minxc.emp.basis.impl.email.model.Mail;
import org.minxc.emp.basis.impl.email.model.MailSetting;
import org.minxc.emp.core.util.AppContextUtil;


/**
 * 这个类用于发送邮件。
 * 
 * 
 */
public class EmailUtil {


    /**
     * 发送邮件。
     * 
     * 	调用方法：
     * 	EmailUtil.sendEmail("收信人地址","抄送人地址","秘密抄送","发送人地址","主题","邮件内容");
     * 
     *
     * @param to      收件人
     * @param cc      抄送人
     * @param bcc     密送人
     * @param from    发件人
     * @param subject 标题
     * @param content 内容
     * @throws MessagingException void
     */
    public static void sendEmail(String to, String cc, String bcc, String from, String subject, String content) throws MessagingException {
        MailUtil mailSender = (MailUtil) AppContextUtil.getBean("mailSender");
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setSenderName(from);
        mail.setContent(content);
        mail.setReceiverAddresses(to);
        mail.setSenderAddress(from);
        if (StringUtils.isNotEmpty(cc)) {
            mail.setCopyToAddresses(cc);
        }

        if (StringUtils.isNotEmpty(bcc)) {
            mail.setBcCAddresses(bcc);
        }
        mail.setSendDate(new Date());
        try {
            mailSender.send(mail);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    

    /**
     * 发送邮件。
     * 
     * 	调用方法：
     * 	EmailUtil.sendEmail("收信人地址","抄送人地址","秘密抄送","发送人地址","主题","邮件内容");
     * 
     *
     * @param to      收件人
     * @param from    发件人
     * @param subject 标题
     * @param content 内容
     * @throws MessagingException void
     */
    public static void sendEmail(String to, String subject, String content) throws MessagingException {
        MailUtil mailSender = (MailUtil) AppContextUtil.getBean("mailSender");
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setContent(content);
        mail.setReceiverAddresses(to);
        mail.setSendDate(new Date());
        try {
            mailSender.send(mail);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception{
    	MailSetting setting = new MailSetting();
    	setting.setSendHost("smtp.qq.com");
    	setting.setSendPort("465");
    	setting.setSSL(true);
    	setting.setProtocal("smtp");
    	setting.setValidate(true);
    	setting.setNickName("agileBpm");
    	setting.setMailAddress("agileBpm@qq.com");
    	setting.setPassword("fywouffpohiibdee");
    	
    	MailUtil mailSender = new MailUtil(setting);
    	
    	 Mail mail = new Mail();
         mail.setSubject("subject");
         mail.setSenderName("senderName");
         mail.setContent("content");
         mail.setReceiverAddresses("632266504@qq.com");
         mail.setSenderAddress("SenderAddress");

         mail.setSendDate(new Date());
         mailSender.send(mail);
	}
}
