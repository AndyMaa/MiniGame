package minigame.core.players;

import minigame.core.ai.AI;
import minigame.core.server.Server;

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
        int[] result = ai.nextStep(server.getChess(), id);
        if (result==null){
            JOptionPane.showMessageDialog(null,"AI已经无棋可走了！棋盘上妻子多的人获胜");
            return;
        }
        server.step(this, result[0], result[1]);
    }
}