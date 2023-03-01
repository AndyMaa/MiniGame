package minigame.core.players;

import minigame.core.ai.AI;

import javax.swing.*;

/**
 * 电脑玩家
 */
public class AIPlayer extends AbstractPlayer {
    private final AI ai;


    public AIPlayer(AI ai) {
        this.ai = ai;
    }

    @Override
    public void step(int x, int y) {
//        long t0=System.currentTimeMillis();
        int[] result = ai.nextStep(server.getChess(), id);
        if (result==null){
            JOptionPane.showMessageDialog(null,"AI已经无棋可走了！棋盘上妻子多的人获胜");
            return;
        }
//        long time=System.currentTimeMillis()-t0;
//        System.out.println("cpu cost: "+time);
//        if (time<700){
//            try {
//                Thread.sleep(700);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        server.step(this, result[0], result[1]);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        if (id==1){
            step(0,0);
        }
    }
}