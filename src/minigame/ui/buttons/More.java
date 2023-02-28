package minigame.ui.buttons;


import minigame.core.event.listeners.ExitListener;
import minigame.core.event.listeners.RegretListener;
import minigame.core.event.listeners.TipListener;

import javax.swing.*;


/**
 * 更多按钮
 */
public class More extends JButton {
    public static JButton EXIT=new JButton("退出");
    public static JButton REGRET=new JButton("悔棋");
    public static JButton TIP=new JButton("提示");

    public static void init(){
        EXIT.addActionListener(new ExitListener());
        REGRET.addActionListener(new RegretListener());
        TIP.addActionListener(new TipListener());
    }
}
