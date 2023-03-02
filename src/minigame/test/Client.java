package minigame.test;

import minigame.core.server.GhostServer;
import minigame.ui.MusicPlayer;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        StringBuilder s= new StringBuilder();
        for (int i=0;i<26;i++){
            s.append("\'").append((char) ('a'+i)).append("\',");
        }
        for (int i=0;i<26;i++){
            s.append("\'").append((char) ('A'+i)).append("\',");
        }
        System.out.print(s.toString());
//        MusicPlayer.playBackground();
    }
}
