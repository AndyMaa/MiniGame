package minigame.core;

import minigame.ui.ChessUI;

/**
 * 棋盘
 * 这不是一个swing控件，只是一个虚拟的棋盘
 * 渲染模块负责将其可视化
 */
public class Chess {
    public static final int NONE=0;
    public static final int MY=1;
    public static final int YOU=2;

    /**
     * 储存期棋盘数据
     */
    private final byte[][] data;
    /**
     * 棋盘大小，以格为单位
     */
    public final int size;
    /**
     * 一个ChessUI的指针，当数据变化时调用repaint
     * 在ChessUI.setChess中会自动绑定
     */
    private ChessUI ui;
    public void setUI(ChessUI ui){
        this.ui=ui;
    }

    public Chess(int size){
        data=new byte[size][size];
        this.size =size;
    }

    /**
     * 获取某个格子的状态
     * 如果需要频繁调用的话，不要使用这个方法
     * 应该直接获取data进行访问
     */
    public int get(int x,int y){
        return data[x][y];
    }
    public byte[][] getData(){
        return data;
    }

    /**
     * 设置某个点的状态
     */
    public void set(int x,int y,int state){
        data[x][y]= (byte) state;
        ui.repaint();
    }
}