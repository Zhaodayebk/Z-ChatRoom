package com.zdy.chatroom.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@Slf4j
public class ServerApplication {

    public static void main(String[] args) {
        log.info("Z-ChatRoom服务端已启动,监听端口14098");
        SpringApplication.run(ServerApplication.class, args);
    }

}
