package com.zdy.chatroom.server.domain;

import java.util.ArrayList;

//一个空壳封装类,用于解决JSON序列化成对象时的格式错误问题
@SuppressWarnings({"all"})
public class Result {
    public int code;
    public String message;
    public ArrayList<Message> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Message> getData() {
        return data;
    }

    public void setData(ArrayList<Message> data) {
        this.data = data;
    }

    public Result(int code, String message, ArrayList<Message> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
