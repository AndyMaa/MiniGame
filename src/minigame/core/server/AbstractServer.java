package minigame.core.server;

import minigame.core.Chess;
import minigame.core.ai.AI;
import minigame.core.ai.NoobAI;
import minigame.core.players.Player;

public abstract class AbstractServer implements Server{
    protected Chess chess;
    /**
     * 轮到哪个玩家下，值为id
     */
    protected int turn=1;
    private final AI innerAI=new NoobAI();

    /**
     * 是否无棋可走
     */
    protected boolean isFinished(){
        return innerAI.nextStep(chess,turn)==null;
    }
    protected int getWinner(){
        byte[][] data= chess.getData();
        int[] result={0,0,0};
        for (int i=0,j;i<chess.size;i++){
            for (j=0;j<chess.size;j++){
                result[data[i][j]]++;
            }
        }
        return result[1]>result[2]?1:(result[1]==result[2]?0:2);
    }

    @Override
    public Chess getChess() {
        return chess;
    }

    @Override
    public boolean canStepAt(Player player, int x, int y) {
        return player.getId()==turn&&chess.isValid(x,y,turn);
    }
}
