package minigame.core.players;

import minigame.core.server.Server;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 本机玩家
 */

public class LocalPlayer implements Player{
    private Server server;
    private int id=0;
    @Override
    public void step(int x, int y) {
        if (server.canStepAt(this,x,y)){
            server.step(this,x, y);
        }
    }

    @Override
    public void join(Server server) {
        this.server=server;
        server.onJoin(this);
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }
}
