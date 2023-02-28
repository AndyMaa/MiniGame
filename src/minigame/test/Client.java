package minigame.test;

import minigame.core.server.GhostServer;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        new GhostServer("127.0.0.1",53242);
        Thread.sleep(10000);
    }
}
