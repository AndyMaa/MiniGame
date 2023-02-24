package minigame.core;

import minigame.core.players.AIPlayer;
import minigame.core.players.LocalPlayer;
import minigame.core.players.Player;
import minigame.core.server.LocalServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;

public class Game {
    /**
     * 本地玩家
     */
    public static Player thePlayer=new LocalPlayer();
    public static void start(){
        Server server=new LocalServer(10);
        ChessUI.instance.setChess(server.getChess());
        thePlayer.join(server);
        new LocalPlayer().join(server);
//        new AIPlayer().jo
    }
}
