package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.server.GhostServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class JoinServerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String get= JOptionPane.showInputDialog("请输入ip和端口:");
        String[] result=get.split(":");
        String ip=result[0];
        String p=result[1];
        int port=Integer.parseInt(p);
        GhostServer ghost=new GhostServer(ip, port);
        Game.setServer(ghost);
        Game.thePlayer.join(ghost);

    }
}
