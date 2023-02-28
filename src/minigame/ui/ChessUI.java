package minigame.ui;

import minigame.core.Chess;
import minigame.core.Game;
import minigame.ui.buttons.More;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 渲染一个Chess
 */
public class ChessUI extends JPanel implements ComponentListener, MouseMotionListener, MouseListener {
    public static ChessUI instance;
    private Chess chess;
    public ChessUI(){
        super();
        instance=this;
        addComponentListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setButton();
        More.init();
    }

    public void setButton(){
        //添加按钮
        add(More.EXIT) ;
        add(More.REGRET);
        add(More.TIP);
    }

    public void setChess(Chess chess){
        this.chess=chess;
        chess.setUI(this);
    }

    /**
     * 像素点到格点坐标的转换
     * 已测试
     */
    public int toBlockX(int x){
        return (x-rX)/blockSize;
    }
    public int toBlockY(int y){
        return (y-rY)/blockSize;
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
    }

    private static final Color highLight=new Color(54, 227, 241, 120);
    private static final Color click=new Color(15, 21, 21, 205);
    private static final Color purple=new Color(133, 56, 220);
    private static final Color blue=new Color(41, 180, 241);
    private static final Color lastStep=new Color(238, 226, 46,0);
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //缓存,因为要频繁调用
        int blockSize=this.blockSize;
        Chess chess=this.chess;
        //TODO 在最后会多画一点
        for (int i=0;i<chess.size+1;i++){
            g.drawLine(rX+i*blockSize,rY,rX+i*blockSize,rY+rSize);
            g.drawLine(rX,rY+i*blockSize,rX+rSize,rY+i*blockSize);
        }
        byte[][] data=chess.getData();
        //高亮最后一步
        if (chess.lastX!=-1){
            g.setColor(lastStep);
            g.fillRect(rX+chess.lastX*blockSize,rY+chess.lastY*blockSize,blockSize,blockSize);
            g.setColor(Color.black);
        }
        //渲染棋子
        int current,j;
        for (int i=0;i<chess.size;i++){
            for (j=0;j<chess.size;j++){
                current=data[i][j];
                if (current!=0){
                    if (current==1) g.setColor(purple);
                    else if (current==2) g.setColor(blue);
                    g.fillOval(rX+i*blockSize+5,rY+j*blockSize+5,blockSize-10,blockSize-10);
                    g.setColor(Color.black);
                }
            }
        }

        if (lastX==-1||lastY==-1||!chess.isValid(lastX,lastY,Game.thePlayer.getId())) return;
        g.setColor(highLight);
        g.fillRect(rX+lastX*blockSize,rY+lastY*blockSize,blockSize,blockSize);
        if (pressX==-1||pressY==-1) return;
        g.setColor(click);
        g.fillRect(rX+pressX*blockSize,rY+pressY*blockSize,blockSize,blockSize);
    }

    /**
     * 上一次鼠标的格点位置
     */
    private int lastX=-1;
    private int lastY=-1;
    /**
     * 在鼠标移动时高亮格子
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int newX=toBlockX(e.getX());
        int newY=toBlockY(e.getY());
        if (newX==lastX&&newY==lastY) return;//没有变化就return
        lastX=newX>=chess.size||newX<0?-1:newX;
        lastY=newY>=chess.size||newY<0?-1:newY;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    private int pressX=-1;
    private int pressY=-1;
    @Override
    public void mousePressed(MouseEvent e) {
        int newX=toBlockX(e.getX());
        int newY=toBlockY(e.getY());
        if (newX==pressX&&newY==pressY) return;//没有变化就return
        pressX=newX>=chess.size||newX<0?-1:newX;
        pressY=newY>=chess.size||newY<0?-1:newY;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toBlockX(e.getX())==pressX&&toBlockY(e.getY())==pressY&&pressX!=-1&&pressY!=-1){
            Game.thePlayer.step(pressX,pressY);
        }
        pressX=-1;
        pressY=-1;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
