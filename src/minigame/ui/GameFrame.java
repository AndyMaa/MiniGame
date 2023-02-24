package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    //public static boolean canWork=false;
    public static GameFrame instance;
    public static JPanel gameC;
    public static JPanel welcomeC;

    public GameFrame(String name){
        super(name);
        instance=this;
        setSize(600, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Chess chess=new Chess(10);
        gameC=new ChessUI(chess);
        welcomeC=new WelcomePane();
//        getContentPane().add(welcomeC,BorderLayout.CENTER);
//        getContentPane().add(welcomeC,BorderLayout.CENTER);
        setMode("welcome");
    }

    /**
     * 有bug
     * @param mode
     */
    public void setMode(String mode){
        switch (mode){
            case "welcome":
                System.out.println("we");
                getContentPane().removeAll();
//                removeAll();
                getContentPane().add(welcomeC,BorderLayout.CENTER);
                break;
            case "game":
                System.out.println("game");
                getContentPane().removeAll();
//                removeAll();
                getContentPane().add(gameC,BorderLayout.CENTER);
                break;
        }
        repaint();
//        System.out.println(gameC.getBounds());
    }
}
