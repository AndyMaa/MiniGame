package minigame.core.ai;

import minigame.core.Chess;

import java.util.LinkedList;

public class NoobAI implements AI{
    @Override
    public int[] nextStep(Chess chess, int id) {
        int[] pos;
        LinkedList<int[]> list=new LinkedList<>();
        for (int x=0,y;x<chess.size;x++){
            for (y=0;y<chess.size;y++){
                if (chess.isValid(x,y,id)){
                    pos=new int[2];
                    pos[0]=x;
                    pos[1]=y;
                    list.add(pos);
                }
            }
        }
        Object[] poses=list.toArray();
        if (poses.length==0) return null;
        return (int[]) poses[((int) (Math.random() * poses.length))];
    }
}
