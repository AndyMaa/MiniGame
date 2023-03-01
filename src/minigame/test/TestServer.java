package minigame.test;

import minigame.core.Game;
import minigame.core.server.MainServer;

public class TestServer {
    public static void main(String[] args) throws Exception {
        MainServer server=new MainServer(Game.size);
        Thread.sleep(10000);
        server.close();
    }
}

