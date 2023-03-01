package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.server.MainServer;
import minigame.ui.ChessUI;
import minigame.ui.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateServerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainServer server=new MainServer(Game.size);
        Game.thePlayer.join(server);
        ChessUI.instance.setChess(server.getChess());
        GameFrame.instance.setMode("game");
    }
}
