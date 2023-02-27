package minigame.core.players;

import minigame.core.server.Server;

public abstract class AbstractPlayer implements Player{
    protected Server server;
    protected int id = 0;
    @Override
    public void join(Server server) {
        this.server = server;
        server.onJoin(this);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
