package minigame.core;

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
     * 储存期盼数据
     */
    public final byte[][] data;
    public final int width;
    public final int height;

    public Chess(int width,int height){
        data=new byte[width][height];
        this.width=width;
        this.height=height;
    }

    /**
     * 获取某个格子的状态
     * 如果需要频繁调用的话，不要使用这个方法
     * 应该直接获取data进行访问
     */
    public int get(int x,int y){
        return data[x][y];
    }
}