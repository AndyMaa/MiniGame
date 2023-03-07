package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.server.MainServer;
import minigame.ui.ChessUI;
import minigame.ui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class CreateServerListener implements ActionListener {
    public static String ip;
    public static int port;
    @Override
    public void actionPerformed(ActionEvent e) {
        MainServer server=new MainServer(Game.size);
        Game.setServer(server);
        Game.thePlayer.join(server);
        ChessUI.instance.setChess(server.getChess());
        ip=MainServer.ip;
        port=MainServer.port;
        GameFrame.instance.setMode("game");

    }
}
