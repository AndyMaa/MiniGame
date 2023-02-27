package minigame.core;

import minigame.ui.ChessUI;

/**
 * 棋盘
 * 这不是一个swing控件，只是一个虚拟的棋盘
 * 渲染模块负责将其可视化
 */
public final class Chess {
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
        data[size/2-1][size/2-1]=data[size/2][size/2]=1;
        data[size/2-1][size/2]=data[size/2][size/2-1]=2;
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
    public void set(int x,int y,int id){
        data[x][y]= (byte) id;
        int num,startX,startY,i;
        for (int dx=-1;dx<2;dx++){
            for (int dy=-1;dy<2;dy++){
                if (dy==0&&dx==0) continue;
                num=find(x,y,dx,dy,id);
                if (num>0){
                    startX=x+dx;startY=y+dy;
                    for (i=0;i<num;i++){
                        data[startX+i*dx][startY+i*dy]= (byte) id;
                    }
                }
            }
        }
        ui.repaint();
    }

    /**
     * 查找指定方向上id可以吃掉的棋子的数量
     * @param deltaX x轴变化量
     * @param deltaY y轴变化量
     * @return 棋子的数量
     */
    public int find(int x,int y,int deltaX,int deltaY,int id){
        int xx=x+deltaX,yy=y+deltaY;
        byte enemy= (byte) (3-id);
        byte buff;
        int count=0;
        for (;;xx+=deltaX,yy+=deltaY){
            if (xx<0||yy<0||xx==size||yy==size) return 0;
            buff=data[xx][yy];
            if (buff==enemy) count++;
            else {
                if (buff==0) return 0;
                if (buff==id) return count;
            }
        }
    }

    /**
     * 这个位置是否能下id的棋
     * 现在只判断是否为空，后期再判断是否符合规则
     */
    public boolean isValid(int x,int y,int id){
        if (data[x][y]!=0) return false;
        return find(x, y, 1, 0, id) > 0
                || find(x, y, -1, 0, id) > 0
                || find(x, y, 0, 1, id) > 0
                || find(x, y, 0, -1, id) > 0
                || find(x, y, 1, 1, id) > 0
                || find(x, y, -1, 1, id) > 0
                || find(x, y, -1, -1, id) > 0
                || find(x, y, 1, -1, id) > 0;
    }
}