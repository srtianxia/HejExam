package com.srtianxia.hejexam.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class MessageHolder {
    private List<Message> Messages = new ArrayList<>();

    public List<Message> getMessages() {
        return Messages;
    }

    public void setMessages(List<Message> messages) {
        this.Messages = messages;
    }
}
