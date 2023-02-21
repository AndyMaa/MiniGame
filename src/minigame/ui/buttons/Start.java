package minigame.ui.buttons;

import minigame.core.event.listeners.StartListener;

import javax.swing.*;

/**
 * 开始按钮
 */
public class Start extends JButton {
    public Start(String text){
        super(text);
        addActionListener(new StartListener());
    }
}
