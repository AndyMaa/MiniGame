package minigame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import minigame.ui.GameFrame;
import minigame.ui.MusicPlayer;

import javax.swing.*;
import java.io.IOException;

public class MiniGame {
    public static void main(String[] args) {
        //跟随系统ui
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new GameFrame("MiniGame").setVisible(true);
//        MusicPlayer.playBackground();
    }
}
