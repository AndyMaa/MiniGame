package minigame;

import minigame.ui.GameFrame;

public class Main {
    public static void main(String[] args) {
        if (GameFrame.canWork) new GameFrame("Game1").setVisible(true);
        else {
//            new WelcomeFrame("MiniGame");
            System.out.println("Welcome!");
        }
    }
}
