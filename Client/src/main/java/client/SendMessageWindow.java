package client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static client.ClientMain.chatInfo;

@SuppressWarnings({"all"})
public class SendMessageWindow extends JFrame implements Runnable {

    private JTextField inputField;
    private JButton button;
    private JLabel label;

    public SendMessageWindow() {
        //创建消息发送器的窗口
        setTitle("Z-ChatRoom消息发送器");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        label = new JLabel("您的用户名是:" + chatInfo.getMyUsername() + "\n   " + "请在输入框中输入需发送消息");
        inputField = new JTextField(20);
        button = new JButton("发送");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送消息
                String input = inputField.getText();
                Message message = new Message();
                message.setCommandBody("");
                message.setBody(input);
                message.setUsername(chatInfo.getMyUsername());
                message.setChannelName(chatInfo.getChannelName());
                message.setCommand(false);
                HttpRequest.post("http://" + chatInfo.getServerIP() + ":" + chatInfo.getServerPort() + "/api/addmessage").body(JSONUtil.toJsonStr(message))
                        .execute(false);
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(inputField);
        panel.add(button);
        add(panel);
    }


    @Override
    public void run() {
        //显示窗口
        SendMessageWindow window = new SendMessageWindow();
        window.setVisible(true);

    }
}