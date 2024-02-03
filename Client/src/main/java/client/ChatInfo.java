package client;

import lombok.Data;


@Data
public class ChatInfo {
    //用户名
    private String myUsername;
    //加入的Channel
    private String channelName;
    //服务器IP和端口
    private String serverIP;
    private int serverPort;
    //是否是Channel创建者
    private boolean isChannelCreator;



}
