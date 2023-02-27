package minigame.core.players;

/**
 * 本机玩家
 */

public class LocalPlayer extends AbstractPlayer{
    @Override
    public void step(int x, int y) {
        if (server.canStepAt(this,x,y)){
            server.step(this,x, y);
        }
    }
}
