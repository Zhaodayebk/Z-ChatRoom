package com.zdy.chatroom.server.service;

import com.zdy.chatroom.server.domain.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Service
@SuppressWarnings({"all"})
public class MessageService {
    //存储所有Channel的Map
    public static ConcurrentHashMap<String, ArrayList<Message>> channelList;
    static {
        channelList = new ConcurrentHashMap<String, ArrayList<Message>>();
    }
    public ArrayList<Message> getMessageList(Message command){
        //获取消息
        if(("chatroom.getmessage".equals(command.getCommandBody()))){
            return channelList.get(command.getChannelName());
        }
        return null;
    }


    public void addChannel(Message command){
        //添加Channel
        if("chatroom.addchannel".equals(command.getCommandBody())){
            channelList.put(command.getChannelName(), new ArrayList<>());
        }
    }
    public void addMessage(Message message){
        //添加消息
        if ("".equals(message.getCommandBody())){
            ArrayList<Message> chat = channelList.get(message.getChannelName());
            if (chat == null) {
                ArrayList<Message> a = new ArrayList<>();
                a.add(message);
                channelList.put(message.getChannelName(), a);
            } else {
                ArrayList<Message> b = new ArrayList<>();
                b = channelList.get(message.getChannelName());
                b.add(message);
                channelList.put(message.getChannelName(), b);

            }
        }
    }

@PostMapping("/removechannel")
    public void removeChannel(String channelName) {
        channelList.remove(channelName);
    }
}
