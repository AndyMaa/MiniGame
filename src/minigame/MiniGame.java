package minigame;

import minigame.ui.GameFrame;
import minigame.ui.MusicPlayer;

import javax.swing.*;

public class MiniGame {
    public static void main(String[] args) {
        //跟随系统ui
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new GameFrame("MiniGame").setVisible(true);
        MusicPlayer.playBackground();
    }
}
