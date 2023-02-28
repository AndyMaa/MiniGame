package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.ui.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingListener implements ActionListener {
    public static JButton claim=new JButton("确定");
    public static JFrame setting=new JFrame("Settings");
    public static JLabel sz=new JLabel("设置棋盘大小:");
    public static JTextField field=new JTextField(20);
    public static int size;

    @Override
    public void actionPerformed(ActionEvent e) {
        setting.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setting.setSize(200, 100);
        field.setText(String.valueOf(Game.size));
        field.setEnabled(GameFrame.CheckMode());
        field.setEditable(GameFrame.CheckMode());
        setting.getContentPane().add(sz);//, FlowLayout.LEFT);
        setting.getContentPane().add(field);//, FlowLayout.RIGHT);
        setting.getContentPane().add(claim);
        claim.addActionListener(new ConfirmListener());

        String get=field.getText();
        if (get!=null){
            try {
                size= Integer.parseInt(get);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            JOptionPane.showInputDialog(GameFrame.instance, "请输入有效数字");
        }
        setting.setVisible(true);
    }
}
