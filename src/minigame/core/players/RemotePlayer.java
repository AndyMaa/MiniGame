package minigame.core.players;

import minigame.core.Chess;
import minigame.core.server.Server;

/**
 * 远程玩家的本地实例
 * 短期不用考虑
 */
public class RemotePlayer{
    public static RemotePlayer instance=new RemotePlayer();
    private Server server;

//    @Override
    public void step(int x, int y) {
    }
}
