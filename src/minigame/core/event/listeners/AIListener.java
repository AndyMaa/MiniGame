package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.ai.NoobAI;
import minigame.core.players.AIPlayer;
import minigame.core.server.LocalServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;
import minigame.ui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AIListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Server server=new LocalServer(10);
        ChessUI.instance.setChess(server.getChess());
        Game.thePlayer.join(server);
        AIPlayer ai=new AIPlayer(new NoobAI());
        ai.join(server);
        GameFrame.instance.setMode("game");
        System.out.println("ai mode");
    }
}
