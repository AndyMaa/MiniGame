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
            int p1=pos[0]+1;
            int p2=-pos[1]-1;
            //真实坐标(左上角0,0)
            if (pos!=null){
                JOptionPane.showMessageDialog(null,"AI建议你下"+p1+", "+ p2);
            }
        }
        else JOptionPane.showInputDialog("您的试用次数已结束!请充值");
        count=count+1;
    }
}
