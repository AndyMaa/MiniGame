package minigame.core.event.listeners;

import minigame.ui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameFrame.instance.setMode("welcome");
        //GameFrame.instance.setVisible(false);
    }
}
