package minigame;

import minigame.ui.GameFrame;
import minigame.ui.MusicPlayer;

public class MiniGame {
    public static void main(String[] args) {
        new GameFrame("MiniGame").setVisible(true);
        MusicPlayer.playBackground();
    }
}
