package minigame.core.players;

import minigame.core.Chess;
import minigame.core.server.Server;

/**
 * 远程玩家的本地实例
 * 短期不用考虑
 */
public class RemotePlayer implements Player {
    public static RemotePlayer instance=new RemotePlayer();
    private Server server;

//    @Override
    public void step(int x, int y) {
        if (server.canStepAt(this, x, y)){
            server.step(this,x,y);
        }
    }

    @Override
    public void join(Server server) {
        this.server=server;
        server.onJoin(this);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
