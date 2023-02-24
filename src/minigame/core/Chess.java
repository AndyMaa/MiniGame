package minigame.core;

import minigame.ui.ChessUI;

/**
 * 棋盘
 * 这不是一个swing控件，只是一个虚拟的棋盘
 * 渲染模块负责将其可视化
 */
public class Chess {
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
    public void set(int x,int y,int state){
        data[x][y]= (byte) state;
        ui.repaint();
    }

    /**
     * 这个位置是否能下id的棋
     * 现在只判断是否为空，后期再判断是否符合规则
     */
    public boolean isValid(int x,int y,int id){
        //查找横竖上是否存在自己的棋子
//        int i,j;
//        byte[] c;
//        boolean valid=false;
//        //检测左边
//        if (x>1){
//            for (i=x-1;i>=0;i--){
////                c=;
//                if (data[i][y]==id){//最近的自己的棋子
//                    valid=true;
//                    for (j=i;j<x;j++){
//                        //如果有空
//                        if (data[j][y]==0){
//                            valid=false;
//                            break;
//                        }
//                    }
//                    if (valid) return data[x][y]==0;
//                    break;
//                }
//            }
//        }
//        //检测右边
//        if (x<size-2){
//            for (i=x+1;i<size;i++){
//                if (data[i][y]==id){
//                    valid=true;
//                    for (j=x+1;j<i+1;j++){
//                        if (data[j][y]==0){
//                            valid=false;
//                            break;
//                        }
//                    }
//                    if (valid) return data[x][y]==0;
//                    break;
//                }
//            }
//        }
//        //检测上边
//        if (y>1){
//            for (i=y-1;i>=0;i++){
//                c=data[x];
//                if (c[i]==id){
//                    valid=true;
//                    for (j=i;j<y;j++){
//                        if (data[x][j]==0){
//                            valid=false;
//                            break;
//                        }
//                    }
//                    if (valid) return data[x][y]==0;
//                    break;
//                }
//            }
//        }
//        //检测下边
//        if (y<size-2){
//            for (i=y+1;i<size;i++){
//                if (data[x][i]==id){
//                    valid=true;
//                    for (j=y+1;j<i+1;j++){
//                        if (data[x][j]==0){
//                            valid=false;
//                            break;
//                        }
//                    }
//                    if (valid) return data[x][y]==0;
//                    break;
//                }
//            }
//        }
//
        return data[x][y]==0;
    }
}