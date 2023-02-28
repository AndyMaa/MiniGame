package minigame.core.net;

import minigame.core.Chess;

public class InitPacket extends Packet{
    private static final long serialVersionUID=1887415157L;
    public final Chess chess;
    /**
     * 接收者的id
     */
    public final int yourId;

    public InitPacket(Chess chess, int yourId) {
        this.chess = chess;
        this.yourId = yourId;
    }
}
