package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * 棋盘面板
 */

public class GamePane extends JPanel {
    public GamePane(){
        super();
        Chess chess=new Chess(10);
        System.out.println(getSize());
        add(new ChessUI(chess), BorderLayout.CENTER);
    }
}
