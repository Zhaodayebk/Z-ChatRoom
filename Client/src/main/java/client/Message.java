package client;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings({"all"})
public class Message implements Serializable {
    //是否是命令
    public boolean isCommand;
    //命令内容
    private String commandBody;
    //目标Channel名
    private String channelName;
    //发送者
    private String username;
    //消息内容
    private String body;


    public boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean command) {
        this.isCommand = command;
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
