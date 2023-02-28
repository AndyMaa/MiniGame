package minigame.core.server;

import minigame.core.Chess;
import minigame.core.net.Packet;
import minigame.core.players.Player;

import java.io.IOException;
import java.net.Socket;

public abstract class RemoteServer implements Runnable, Server {
    protected Socket socket;
    protected Connection connection;
    protected Chess chess;
    /**
     * 网络服务器都只需要储存一个player
     */
    protected Player player;
    public abstract void packetReceive(Packet packet);
    /**
     * 监听对方发送的数据包
     */
    @Override
    public void run() {
        Packet packet;
        while (!socket.isClosed()){
            try {
                packet=connection.readPacket();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            if (packet!=null){
                packetReceive(packet);
            }
        }
        System.out.println("Thread stop");
    }

    public void close(){
        if (connection==null){
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            connection.close();
        }
    }

    /**
     * 开始监听
     * 不调用的话无法接受数据！！！
     */
    protected void startListen(){
        new Thread(this).start();
    }

    @Override
    public Chess getChess() {
        return chess;
    }
}
