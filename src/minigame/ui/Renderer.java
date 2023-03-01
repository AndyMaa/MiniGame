package minigame.ui;

import java.awt.*;

/**
 * 渲染器
 */
public class Renderer {
    private Graphics g;
    public Renderer(){
    }

    public void setGraphic(Graphics g) {
        this.g = g;
    }

    //绘制矩形
    public void drawRect(int x,int y,int width,int height,boolean fill,Color color, String text){
        char[] chars = new char[text.length()];
        g.setColor(color);
        g.drawChars(chars, 0, chars.length, x, y);
        if (fill) g.fillRect(x, y, width, height);
        else g.drawRect(x, y, width, height);
    }

    public void drawRect(int x,int y,int width,int height,boolean fill,Image img, String text){
        char[] chars = new char[text.length()];
        drawImage(img, x, y);
        g.drawChars(chars, 0, chars.length, x, y);
        if (fill) g.fillRect(x, y, width, height);
        else g.drawRect(x, y, width, height);
    }

    public void text(String s,int x,int y){
        g.drawString(s,x,y);
    }
    public void text(String s, int x, int y, Font font, Color color){
        g.setColor(color);
        g.drawString(s,x,y);
        g.setFont(font);
    }

    public void text(String s,int x,int y, Font font){
        g.drawString(s,x,y);
        g.setFont(font);
    }
    public void drawImage(Image img,int x,int y){
        if (img==null) return;
        g.drawImage(img,x,y,null);
    }
}
