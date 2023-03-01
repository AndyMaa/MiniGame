package minigame.core.server;

import minigame.core.Chess;
import minigame.core.net.InitPacket;
import minigame.core.net.Packet;
import minigame.core.net.StepPacket;
import minigame.core.players.Player;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 真正的服务器
 */
public final class MainServer extends RemoteServer{
    public int port;
    public String ip;
    ServerSocket serverS;
    public MainServer(int size) {
        chess=new Chess(size);
        new Thread(new Runnable() {
            /**
             * 监听玩家加入
             */
            @Override
            public void run() {
                try {
                    serverS = new ServerSocket(53242);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                port = serverS.getLocalPort();
                ip = serverS.getInetAddress().getHostAddress();
                System.out.println("socket start at " + serverS.getLocalPort());
                while (socket == null) {
                    try {
                        socket = serverS.accept();
                    } catch (IOException e) {
                        socket = null;
                        e.printStackTrace();
                    }
                }
                try {
                    connection = new Connection(socket);
                    online=true;
                    connection.writePacket(new InitPacket(chess,3-player.getId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("get Connection");
                try {
                    serverS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverS = null;
                startListen();
            }
        }).start();
    }

    @Override
    public void onJoin(Player player) {
        this.player=player;
        if (player.getId()==0){
            //随机生成一个id
            //id决定棋子颜色和先后
            int id=1+(int) Math.round(Math.random());
            player.setId(id);
        }
    }

    @Override
    public void close() {
        super.close();
        if (serverS!=null){
            try {
                serverS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serverS=null;
        }
    }

    @Override
    public void packetReceive(Packet packet) {
        if (packet instanceof StepPacket){
            StepPacket stepPacket = (StepPacket) packet;
            chess.set(stepPacket.x, stepPacket.y, turn);
            turn=player.getId();
        }
    }
}
