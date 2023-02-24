package minigame.ui.buttons;

import minigame.core.event.listeners.ExitListener;
import minigame.core.event.listeners.RegretListener;

import javax.swing.*;

public class More extends JButton {
    public static JButton EXIT=new JButton("退出");
    public static JButton REGRET=new JButton("悔棋");

    public More(){
        EXIT.addActionListener(new ExitListener());
        REGRET.addActionListener(new RegretListener());
    }
}
