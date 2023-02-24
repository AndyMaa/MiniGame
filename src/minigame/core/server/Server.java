package minigame.core.server;

import minigame.core.Chess;
import minigame.core.players.Player;

public interface Server {
//    void start();
    void onJoin(Player player);
    boolean canStepAt(Player player,int x,int y);
    void step(Player player,int x,int y);
    Chess getChess();
}
