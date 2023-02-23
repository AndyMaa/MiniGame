package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    public static boolean canWork=false;

    public GameFrame(String name){
        super(name);
        setSize(600, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Chess chess=new Chess(10);
        if (canWork) {
            getContentPane().add(new ChessUI(chess), BorderLayout.CENTER);
            repaint();
        }
        else {
            getContentPane().add(new WelcomePane(), BorderLayout.CENTER);
            repaint();
        }
    }
}
