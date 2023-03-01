package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.players.RemotePlayer;
import minigame.core.server.GhostServer;
import minigame.ui.ChessUI;
import minigame.ui.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinServerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String get= JOptionPane.showInputDialog("请输入ip和端口:");
        String[] result=get.split(":");
        String ip=result[0];
        String p=result[1];
        int port=Integer.parseInt(p);
        GhostServer ghost=new GhostServer(ip, port);
        Game.thePlayer.join(ghost);
        GameFrame.instance.setMode("game");
    }
}
