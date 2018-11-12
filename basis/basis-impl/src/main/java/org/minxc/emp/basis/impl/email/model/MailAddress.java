package org.minxc.emp.basis.impl.email.model;

/**
 * 
 * 邮件自定义显示名及邮件地址辅助类
 * 
 */
public class MailAddress {

    protected String address = "";

    protected String name = "";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MailAddress() {

    }

    public MailAddress(String address, String name) {
        this.address = address;
        this.name = name;
    }
}
