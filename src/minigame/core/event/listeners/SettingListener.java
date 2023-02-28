package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.ui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingListener implements ActionListener{
    public static JButton claim=new JButton("确定");
    public static JFrame setting=new JFrame("Settings");
    public static JLabel sz=new JLabel("(边长)设置棋盘大小:");
    public static JTextField field=new JTextField(7);
    public static JPanel panel=new JPanel();
    public static int size;

    @Override
    public void actionPerformed(ActionEvent e) {
        setting.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setting.setSize(300, 100);
        field.setText(String.valueOf(Game.size));
        field.setEnabled(!GameFrame.CheckMode());
        field.setEditable(!GameFrame.CheckMode());
        panel.add(sz);
        panel.add(field);
        panel.add(claim);
        claim.addActionListener(new ConfirmListener());
        setting.getContentPane().add(panel, BorderLayout.CENTER);

        String get=field.getText();
        if (get!=null){
            try {
                size=Integer.parseInt(get);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showInputDialog(GameFrame.instance, "请输入有效数字");
            }
        }else {


            JOptionPane.showInputDialog(GameFrame.instance, "请输入");
        }
        setting.setVisible(true);
    }
}
