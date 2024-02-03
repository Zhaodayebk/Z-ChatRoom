package com.zdy.chatroom.server.domain;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings({"all"})
public class Message implements Serializable {
    private boolean isCommand;
    private String commandBody;
    private String channelName;
    private String username;
    private String body;


    public boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean command) {
        isCommand = command;
    }

    public String getCommandBody() {
        return commandBody;
    }

    public void setCommandBody(String commandBody) {
        this.commandBody = commandBody;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message(boolean isCommand, String commandBody, String channelName, String username, String body) {
        this.isCommand = isCommand;
        this.commandBody = commandBody;
        this.channelName = channelName;
        this.username = username;
        this.body = body;
    }
    public Message(){}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return isCommand == message.isCommand &&
                Objects.equals(commandBody, message.commandBody) &&
                Objects.equals(channelName, message.channelName) &&
                Objects.equals(username, message.username) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCommand, commandBody, channelName, username, body);
    }
}
