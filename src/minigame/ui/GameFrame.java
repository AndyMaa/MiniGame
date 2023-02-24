package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    public static GameFrame instance;
    /**
     * 储存mode和对应的组件
     */
    public final HashMap<String,JPanel> modes=new HashMap<>();

    public GameFrame(String name){
        super(name);
        instance=this;
        setSize(600, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Chess chess=new Chess(10);
        modes.put("game",new ChessUI(chess));
        modes.put("welcome",new WelcomePane());
        setMode("welcome");
    }

    /**
     * 测试过了，能用了
     * 眼保健操改的，快叫我大聪明
     */
    public void setMode(String mode){
        if (modes.containsKey(mode)){
            getContentPane().removeAll();
            getContentPane().add(modes.get(mode),BorderLayout.CENTER);
            validate();
        }else {
            System.out.println("未知的mode?  "+mode);
        }
    }
}
