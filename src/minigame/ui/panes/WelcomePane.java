package minigame.ui.panes;

import minigame.core.event.listeners.AIListener;
import minigame.core.event.listeners.StartListener;
import minigame.ui.buttons.More;

import javax.swing.*;
import java.awt.*;

/**
 * 欢迎界面
 */
public class WelcomePane extends JPanel {
    public static Font font=new Font("微软雅黑",Font.ITALIC,15);
    public static JButton AI=new JButton("AI对战");
    public static JButton NATIVE=new JButton("本地联机");

    public WelcomePane(){
        super();
        AI.setFont(font);
        NATIVE.setFont(font);
        add(AI, BorderLayout.CENTER);
        add(NATIVE);
        add(More.CREATE_SERVER);
        add(More.JOIN_SERVER);
        setBackground(Color.lightGray);
        AI.addActionListener(new AIListener());
        NATIVE.addActionListener(new StartListener());
    }

    @Override
    public void paint(Graphics g){
        String s="Welcome to MiniGame!";
        super.paint(g);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        g.setColor(Color.RED);
        g.drawString(s, 100, 50);
    }
}
