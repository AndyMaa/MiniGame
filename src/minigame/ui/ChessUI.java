package minigame.ui;

import minigame.core.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * 渲染一个Chess
 */
public class ChessUI extends JPanel implements ComponentListener {
    private Chess chess;
    public ChessUI(Chess chess){
        super();
        this.chess=chess;
        chess.setUI(this);
        addComponentListener(this);
    }
    public void setChess(Chess chess){
        this.chess=chess;
        chess.setUI(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return getParent().getSize();
    }

    /**
     * 渲染相关数据
     */
    private int rX;
    private int rY;
    private int rSize;//边长
    private int blockSize;//一格的边长
    /**
     * 在大小改变时重新计算位置
     */
    @Override
    public void componentResized(ComponentEvent e) {
        int min= Math.min(getWidth(), getHeight());
        rSize=min-100;
        rX=(getWidth()-rSize)/2;
        rY=(getHeight()-rSize)/2;
        blockSize=rSize/chess.size;
        System.out.println(rX+" "+rY+" "+rSize+" "+blockSize);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(rX,rY,rSize,rSize);
        for (int i=1;i<chess.size;i++){
            g.drawLine(rX+i*blockSize,rY,rX+i*blockSize,rY+rSize);
            g.drawLine(rX,rY+i*blockSize,rX+rSize,rY+i*blockSize);
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
