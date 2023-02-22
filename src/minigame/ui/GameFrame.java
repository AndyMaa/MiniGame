package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    public static boolean canWork=true;

    public GameFrame(String name){
        super(name);
        setSize(600, 630);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        getContentPane().add(new GamePane(), BorderLayout.CENTER);
        Chess chess=new Chess(10);
        System.out.println(getSize());
        getContentPane().add(new ChessUI(chess), BorderLayout.CENTER);
    }
}
