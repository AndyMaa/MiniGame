package minigame.ui;

import javafx.embed.swing.SwingNode;

/**
 * ChessUI的包装
 */
public class ChessUIAdapter extends SwingNode {
    private final ChessUI chessUI=new ChessUI(this);
    public ChessUIAdapter(){
        setContent(chessUI);
    }
}
