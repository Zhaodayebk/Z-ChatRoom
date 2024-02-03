package com.zdy.chatroom.server.controller;


import com.zdy.chatroom.server.domain.Message;
import com.zdy.chatroom.server.domain.Result;
import com.zdy.chatroom.server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    public MessageService messageService;

    //获取消息
    @PostMapping("/getmessage")
    public Result getMessage(@RequestBody Message message) {
        try {
            return new Result(200,"success",messageService.getMessageList(message));
        } catch (Throwable e) {
            return new Result(500,"Server error",null);
        }
    }
    //添加Channel
    @PostMapping("/addchannel")
    public void addChannel(@RequestBody Message message) {
        messageService.addChannel(message);
    }

    //添加消息
    @PostMapping("/addmessage")
    public void addMessage(@RequestBody Message message) {
        if (message.getCommandBody() == null){
            message.setCommandBody("");
        }
        messageService.addMessage(message);
    }
    //移除Channel
    @PostMapping("/removechannel")
    public void removeChannel(String channelName) {
        messageService.removeChannel(channelName);
    }
}
