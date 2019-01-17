package com.minxc.emp.ui.idm.model;

import java.util.Date;

import org.minxc.emp.idm.api.model.Token;
import com.minxc.emp.ui.common.model.AbstractRepresentation;

public class TokenRepresentation extends AbstractRepresentation {

    protected String id;
    protected String value;
    protected Date date;
    protected String userId;
    protected String data;

    public TokenRepresentation() {

    }

    public TokenRepresentation(Token token) {
        setId(token.getId());
        setValue(token.getTokenValue());
        setDate(token.getTokenDate());
        setUserId(token.getUserId());
        setData(token.getTokenData());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
