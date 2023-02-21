package minigame.ui;

import javax.swing.*;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    public static boolean canWork=false;

    public GameFrame(String name){
        super(name);
        setSize(900, 400);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        getContentPane().add(new GamePane());
    }
}
