package minigame.core;

import minigame.core.players.LocalPlayer;
import minigame.core.players.Player;
import minigame.core.server.LocalServer;
import minigame.core.server.RemoteServer;
import minigame.core.server.Server;
import minigame.ui.FXChessUI;

public final class Game {
    /**
     * 本地玩家
     */
    public static Player thePlayer=new LocalPlayer();
    /**
     * 正在运行的服务器
     */
    private static Server server;
    public static int size=10;

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        if (Game.server instanceof RemoteServer){
            ((RemoteServer) Game.server).close();
        }
        Game.server = server;
    }
    public static void exit(){
        thePlayer.logout();
        if (Game.server instanceof RemoteServer){
            ((RemoteServer) Game.server).close();
        }
        Game.server=null;
    }
}
