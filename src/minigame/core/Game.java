package minigame.core;

import minigame.core.ai.NoobAI;
import minigame.core.players.AIPlayer;
import minigame.core.players.LocalPlayer;
import minigame.core.players.Player;
import minigame.core.players.RemotePlayer;
import minigame.core.server.LocalServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;
import minigame.ui.WelcomePane;

public class Game {
    /**
     * 本地玩家
     */
    public static Player thePlayer=new LocalPlayer();
    public static int size;

    public static void start(){
        Server server=new LocalServer(size);
        ChessUI.instance.setChess(server.getChess());
        thePlayer.join(server);
//        if (WelcomePane.AI.isSelected()){
//            new AIPlayer(new NoobAI()).join(server);
//            System.out.println("ai mode");
//        }else if (WelcomePane.NATIVE.isSelected()){
//            new LocalPlayer().join(server);
//        }else if (WelcomePane.NETWORK.isSelected()){
//            new RemotePlayer().join(server);
//        }
//        new AIPlayer().jo
    }
}
