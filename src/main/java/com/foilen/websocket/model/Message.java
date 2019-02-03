package com.foilen.websocket.model;

import com.foilen.smalltools.tools.AbstractBasics;

public class Message extends AbstractBasics {

    private String user;
    private String text;

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
