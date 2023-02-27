package minigame.core.ai;

import minigame.core.Chess;

public interface AI {
    int[] nextStep(Chess chess,int id);
}
