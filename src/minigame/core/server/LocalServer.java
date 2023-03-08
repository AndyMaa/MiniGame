package minigame.core.server;

import minigame.core.Chess;
import minigame.core.Game;
import minigame.core.players.LocalPlayer;
import minigame.core.players.Player;

public final class LocalServer implements Server{
    private final Chess chess;
    private Player p1;
    private Player p2;

    /**
     * 是否两个都是本地玩家
     */
    private boolean playerOnly=false;
    /**
     * 轮到哪个玩家下，值为id
     */
    private int turn=1;
    public LocalServer(int size){
        chess=new Chess(size);
    }

    @Override
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
            playerOnly=p1 instanceof LocalPlayer&&p2 instanceof LocalPlayer;
        }
        if (playerOnly){
            Game.thePlayer=p1.getId()==1?p1:p2;
        }
    }

    @Override
    public boolean canStepAt(Player player,int x, int y) {
        return player.getId()==turn&&chess.isValid(x,y,turn);
    }

    @Override
    public void step(Player player,int x, int y) {
        chess.set(x,y,player.getId());
        if (++turn==3) turn=1;
        if (playerOnly){
            Game.thePlayer=player==p1?p2:p1;
        }else {
            if (Game.thePlayer==player){
                if (player==p1){
                    p2.step(0,0);
                }else {
                    p1.step(0,0);
                }
            }
        }
    }
}
