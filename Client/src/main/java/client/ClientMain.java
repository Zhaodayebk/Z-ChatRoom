package client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings({"all"})
public class ClientMain {
    public static ChatInfo chatInfo;

    public static void main(String[] args) throws InterruptedException {
        //读取配置文件,获取服务端IP和端口
        Properties properties = new Properties();
        try {
            File configFile = new File("config.properties");
            if (configFile.exists()) {
                properties.load(new FileInputStream(configFile));
            } else {
                System.err.println("找不到配置文件: " + configFile.getAbsolutePath());
                throw new IOException("找不到配置文件: " + configFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加程序退出时的收尾工作
        //添加监听程序退出的线程
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //判断是否为Channel创建者
                if (!chatInfo.isChannelCreator()) {
                    //发送告别消息
                    String body2 = JSONUtil.toJsonStr(new Message(false, "",
                            chatInfo.getChannelName(), ClientMain.chatInfo.getMyUsername(), " 退出了本Channel"));
                    HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort()
                            + "/api/addmessage").body(JSONUtil.toJsonStr(body2)).execute(false);
                    System.out.println("发送消息窗口已关闭!程序结束");
                } else {
                    //发送告别消息
                    String body2 = JSONUtil.toJsonStr(new Message(false, "",
                            chatInfo.getChannelName(), ClientMain.chatInfo.getMyUsername(), " 退出了本Channel"));
                    HttpRequest.post("http://" + chatInfo.getServerIP() + ":"
                            + chatInfo.getServerPort() + "/api/addmessage").body(JSONUtil.toJsonStr(body2)).execute(false);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //删除Channel
                    HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort() +
                            "/api/removechannel" + "?" + "channelName=" + chatInfo.getChannelName())
                            .execute(false);
                    System.out.println("\n\n您是ID为" + chatInfo.getChannelName() + "的Channel的创建者,您的客户端已退出,本Channel被彻底删除!");
                    System.out.println("发送消息窗口已关闭!程序结束");
                }
            }
        });
        //引导用户配置聊天会话相关信息,并储存在chatInfo中
        chatInfo = new ChatInfo();
        chatInfo.setServerIP(properties.getProperty("server.ip"));
        chatInfo.setServerPort(Integer.parseInt(properties.getProperty("server.port")));
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用Z-ChatRoom聊天系统");
        System.out.println("在使用之前,请务必阅读用户使用文档\n");
        System.out.println("如果您要加入他人创建的Channel,请输入[a]");
        System.out.println("如果您要自己创建Channel,请输入[b]");
        String x = scanner.nextLine();
        if ("a".equals(x)) {
            System.out.println("请输入您的用户名");
            chatInfo.setMyUsername(scanner.nextLine());
            //判断用户名长度
            if (chatInfo.getMyUsername().length() > 11) {
                System.out.println("用户名过长!应在11个字符以内");
                Thread.sleep(2000);
                System.exit(11);
            }
            System.out.println("请输入您要加入的Channel的Channel-ID");
            chatInfo.setChannelName(scanner.nextLine());
            chatInfo.setChannelCreator(false);
            System.out.println("配置完成! 现在您可以在这个窗口查看Channel中的消息\n\n");
            //启动接收消息线程
            new Thread(new CommandSender()).start();
        } else if ("b".equals(x)) {
            System.out.println("请输入您的用户名");
            chatInfo.setMyUsername(scanner.nextLine());
            //判断用户名长度
            if (chatInfo.getMyUsername().length() > 11) {
                System.out.println("用户名过长!应在11个字符以内");
                Thread.sleep(2000);
                System.exit(11);
            }
            //创建Channel,Chaanel名使用32位随机字符串
            chatInfo.setChannelName(RandomStrUtil.getRandomString(32));
            String body = JSONUtil.toJsonStr(new Message(true, "chatroom.addchannel",
                    ClientMain.chatInfo.getChannelName(), ClientMain.chatInfo.getMyUsername(), null));
            HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort() +
                    "/api/addchannel").body(body).execute(false);
            body = null;
            System.out.println("新Channel已创建! Channel-ID为 " + chatInfo.getChannelName());
            System.out.println("若要其他人加入Channel,请分享这个Channel-ID");
            chatInfo.setChannelCreator(true);
            System.out.println("您已经成为ID为" + chatInfo.getChannelName() + "的Channel的创建者");
            System.out.println("配置完成! 现在您可以在这个窗口查看Channel中的消息\n\n");
            //启动接收消息线程
            new Thread(new CommandSender()).start();
        }else {
            System.out.println("输入不合规!");
            System.exit(0);
        }
    }
}
