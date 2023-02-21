package minigame.ui;

import minigame.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

/**
 * 欢迎界面
 */

public class WelcomeFrame extends JFrame {
    public static Renderer r = new Renderer();
    //public static Image image=new BufferedImage(200, 100, ColorSpace.TYPE_2CLR);

    public WelcomeFrame(String name){
        super(name);
        setSize(1280, 720);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        init();
    }

    public void init(){
        //r.drawRect(Settings.WIDTH/2, Settings.LENGTH/3, 200, 100, false, image, "开始游戏");
    }
}
