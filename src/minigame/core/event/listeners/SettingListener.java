package minigame.core.event.listeners;

import minigame.core.Chess;
import minigame.core.Game;
import minigame.ui.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingListener implements ActionListener {
    public static JLabel sz=new JLabel("设置棋盘大小:");
    public static JTextField field=new JTextField(20);

    public int size;

    @Override
    public void actionPerformed(ActionEvent e) {
        String get=field.getText();
        if (get!=null){
            int s=Integer.parseInt(get);
            Game.size=s;
        }else {
            JOptionPane.showInputDialog(GameFrame.instance, "请输入有效数字");
        }
    }
}
