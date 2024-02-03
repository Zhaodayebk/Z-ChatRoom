package client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Vector;
import static client.ClientMain.chatInfo;

@SuppressWarnings({"all"})
public class CommandSender extends Thread {
    public static ArrayList<Message> newMessageList;
    public static ArrayList<Message> oldMessageList;

    static {
        //初始化对象
        newMessageList = new ArrayList<Message>();
        oldMessageList = new ArrayList<Message>();
    }

    @Override
    public void run() {
        //发送打招呼消息
        String body2 = JSONUtil.toJsonStr(new Message(false, "",
                ClientMain.chatInfo.getChannelName(), ClientMain.chatInfo.getMyUsername(),
                " 加入了本Channel"));
        HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort() + "/api/addmessage").body(JSONUtil.toJsonStr(body2))
                .execute(false);
        //打开发送消息窗口
        new Thread(new SendMessageWindow()).start();
        //不断请求服务器以获得最新消息
        while (true) {
            try {
                //发送请求,服务端返回Chaanel中所有消息
                String msg = (JSONUtil.toJsonStr(new Message(true, "chatroom.getmessage",
                        chatInfo.getChannelName(), chatInfo.getMyUsername(),
                        null)));
                String json = (HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort() + "/api/getmessage")
                        .body(msg).execute(false).body() + "");
                newMessageList= JSONUtil.toBean(json,Result.class).getData();
                //对比第n次请求和第n+1次请求数据的差异,以获取其中的新消息
                ArrayList<Message> nmsg = findUniqueInFirstVector(newMessageList, oldMessageList);
                //打印新消息
                for (Message m :nmsg) {
                    System.out.println(m.getUsername() + ": " + m.getBody());
                }
                //更新旧消息列表
                oldMessageList = newMessageList;
                try {
                    //休眠500毫秒,减缓服务器的访问频率
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("消息获取出现异常!可能是channel创建者已关闭客户端");
                System.out.println("本客户端关闭!");
                System.exit(0);
            }


        }


    }
    //比较两个list中的差异
    public ArrayList<Message> findUniqueInFirstVector(ArrayList<Message> list1, ArrayList<Message> list2) {

        ArrayList<Message> copyOfList1 = null;
        try {
            copyOfList1 = new ArrayList<>(list1);
            copyOfList1.removeAll(list2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("消息获取出现异常!可能是channel创建者已关闭客户端");
            System.out.println("本客户端关闭!");
            System.exit(0);
        }
        return copyOfList1;
    }
}
