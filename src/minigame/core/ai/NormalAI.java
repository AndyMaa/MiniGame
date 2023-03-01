package minigame.core.ai;

import minigame.core.Chess;

public class NormalAI implements AI{
    @Override
    public int[] nextStep(Chess chess, int id) {
        int[] pos=new int[2];
        int score=0,temp;
        for (int x=0,y;x<chess.size;x++){
            for (y=0;y<chess.size;y++){
                if (chess.isValid(x,y,id)){
                    temp=getScore(chess,x,y,id);
                    if (temp>score){
                        score=temp;
                        pos[0]=x;
                        pos[1]=y;
                    }
                }
            }
        }
        if (score==0) return null;
        return pos;
    }
    private int getScore(Chess chess,int x,int y,int id){
        int num=0,i,dy;
        for (int dx=-1;dx<2;dx++){
            for (dy = -1; dy<2; dy++){
                if (dy==0&&dx==0) continue;
                num+=chess.find(x,y,dx,dy,id);
            }
        }
        return num;
    }
}
