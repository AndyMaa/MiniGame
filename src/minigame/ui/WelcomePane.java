package minigame.ui;

import minigame.core.event.listeners.AIListener;
import minigame.core.event.listeners.RemoteListener;
import minigame.core.event.listeners.StartListener;
import minigame.ui.buttons.More;

import javax.swing.*;
import java.awt.*;

/**
 * 欢迎界面
 */
public class WelcomePane extends JPanel {
    public static Font font=new Font("微软雅黑",Font.ITALIC,15);
    public static JButton AI=new JButton("AI对战");
    public static JButton NATIVE=new JButton("本地联机");
    public static JButton NETWORK=new JButton("局域网联机");

    public WelcomePane(){
        super();
        setVisible(true);
        setSize(600, 200);
        init();
    }

    public void init(){
        AI.setFont(font);
        NATIVE.setFont(font);
        NETWORK.setFont(font);
        add(AI, BorderLayout.CENTER);
        add(NATIVE);
        add(NETWORK);
        add(More.CREATE_SERVER);
        add(More.JOIN_SERVER);

        AI.addActionListener(new AIListener());
        NATIVE.addActionListener(new StartListener());
        NETWORK.addActionListener(new RemoteListener());
    }
}
