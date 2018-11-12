package org.minxc.emp.basis.api.jms.model.msg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.idm.api.model.User;

/**
 * 通知消息的DTO
 *
 */
public class NotifyMessage implements Serializable{
	
	private static final long serialVersionUID = -7773676683448714903L;
	
	private String subject;
    private String htmlContent;
    private String textContent;
    private User sender;
    private List<SystemIdentity> receivers;


    public NotifyMessage(String subject, String htmlContent, User sender, List<SystemIdentity> receivers) {
        this.subject = subject;
        this.sender = sender;
        this.receivers = receivers;
        this.htmlContent = htmlContent;
    }

    private Map<String, Object> extendVars = new HashMap<String, Object>();

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<SystemIdentity> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<SystemIdentity> receivers) {
        this.receivers = receivers;
    }

    public Map<String, Object> getExtendVars() {
        return extendVars;
    }

    public void setExtendVars(Map<String, Object> extendVars) {
        this.extendVars = extendVars;
    }
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}


}
