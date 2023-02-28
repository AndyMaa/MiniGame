package minigame.core.event.listeners;

import minigame.core.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Game.size=SettingListener.size;
    }
}
