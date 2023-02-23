package minigame.core.server;

import minigame.core.Chess;
import minigame.players.Player;

public class LocalServer implements Server{
    private final Chess chess;
    private Player p1;
    private Player p2;
    public LocalServer(int size){
        chess=new Chess(size);
    }

    public Chess getChess() {
        return chess;
    }

    @Override
    public void onJoin(Player player) {
        if (p1==null){
            p1=player;
            if (player.getId()==0){
                //随机生成一个id
                //id决定棋子颜色和先后
                int id=1+(int) Math.round(Math.random());
                player.setId(id);
            }
        }else if (p2==null){
            p2=player;
            //第二名玩家强制分配id
            player.setId(3-p1.getId());
        }
    }
    //    @Override
//    public void start() {
//
//    }
}
