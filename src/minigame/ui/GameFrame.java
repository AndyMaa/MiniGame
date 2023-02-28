package minigame.ui;

import minigame.core.Game;
import minigame.core.event.listeners.SettingListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * 游戏里的所有控件，按钮什么的，不要使用Swing！
 * 自己实现
 */
public class GameFrame extends JFrame {
    public static GameFrame instance;
    public static JMenuBar menuBar=new JMenuBar();
    public static JMenu menu=new JMenu("Preferences");
    public static JMenuItem settings=new JMenuItem("settings");

    /**
     * 储存mode和对应的组件
     */
    public static final HashMap<String,JPanel> modes=new HashMap<>();

    public GameFrame(String name){
        super(name);
        instance=this;
        setSize(600, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      Chess chess=new Chess(10);
        modes.put("game",new ChessUI());
        modes.put("welcome",new WelcomePane());
        setMode("welcome");
        Game.isRunning=false;
        init();
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
            GameFrame.instance.repaint();
        }else {
            System.out.println("未知的mode?  "+mode);
        }
    }

    /**
     * 已测试
     * @return 游戏是否在运行
     */
    public static boolean CheckMode(){
        if (modes.get("welcome").isShowing()){
            Game.isRunning=false;
        }else if (modes.get("game").isShowing()){
            Game.isRunning=true;
        }
        return Game.isRunning;
    }

    /**
     * 设置
     */
    public void init(){
        menu.add(settings);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        settings.addActionListener(new SettingListener());
    }
}
