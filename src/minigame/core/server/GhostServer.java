package minigame.core.server;

import minigame.core.Chess;
import minigame.core.net.InitPacket;
import minigame.core.net.Packet;
import minigame.core.players.Player;
import minigame.ui.ChessUI;

import java.io.IOException;
import java.net.Socket;

/**
 * 远程服务器的镜像
 */
public class GhostServer extends RemoteServer{
    private int playerId;
    public GhostServer(String ip,int port) {
        try {
            Socket socket=new Socket(ip,port);
            connection=new Connection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startListen();
    }
    @Override
    public void onJoin(Player player) {
        this.player=player;
    }

    @Override
    public boolean canStepAt(Player player, int x, int y) {
        return false;
    }

    @Override
    public void step(Player player, int x, int y) {

    }

    @Override
    public void packetReceive(Packet packet) {
        if (packet instanceof InitPacket){
            InitPacket initPacket = (InitPacket) packet;
            playerId=initPacket.yourId;
            chess=initPacket.chess;
            ChessUI.instance.setChess(chess);
        }
    }
}
