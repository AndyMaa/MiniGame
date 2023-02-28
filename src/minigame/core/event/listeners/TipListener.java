package minigame.core.event.listeners;

import minigame.core.Game;
import minigame.core.ai.NormalAI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TipListener implements ActionListener {
    private int count=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        NormalAI normalAI=new NormalAI();
        if (count<5) {
            int[] pos=normalAI.nextStep(Game.getServer().getChess(), Game.thePlayer.getId());
            if (pos!=null){
                JOptionPane.showMessageDialog(null,"AI建议你下"+pos[0]+", "+pos[1]);
            }
        }
        else JOptionPane.showInputDialog("您的试用次数已结束!请充值");
        count=count+1;
    }
}
