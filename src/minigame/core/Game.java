package minigame.core;

import minigame.core.players.LocalPlayer;
import minigame.core.players.Player;
import minigame.core.server.LocalServer;
import minigame.core.server.MainServer;
import minigame.core.server.Server;
import minigame.ui.ChessUI;

public class Game {
    /**
     * 本地玩家
     */
    public static Player thePlayer=new LocalPlayer();
    /**
     * 正在运行的服务器
     */
    private static Server server;
    public static int size;

    //检测游戏是否在运行
    public static boolean isRunning=false;

    public static void start(){
        Server server=new LocalServer(size);
        Game.setServer(server);
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

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        if (Game.server instanceof MainServer){
            ((MainServer) Game.server).close();
        }
        Game.server = server;
    }
}
