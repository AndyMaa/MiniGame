package minigame.ui.buttons;

import minigame.core.event.listeners.*;

import javax.swing.*;

/**
 * 更多按钮
 */
public class More extends JButton {
    public static JButton EXIT=new JButton("退出");
    public static JButton REGRET=new JButton("悔棋");
    public static JButton TIP=new JButton("提示");
    public static JButton CREATE_SERVER=new JButton("创建服务器");
    public static JButton JOIN_SERVER=new JButton("加入服务器");

    public static void init(){
        EXIT.addActionListener(new ExitListener());
        REGRET.addActionListener(new RegretListener());
        TIP.addActionListener(new TipListener());
        CREATE_SERVER.addActionListener(new CreateServerListener());
        JOIN_SERVER.addActionListener(new JoinServerListener());
    }
}
