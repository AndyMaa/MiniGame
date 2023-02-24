package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.ui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 监听开始按钮
 */

public class StartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameFrame.instance.setMode("game");
    }
}
