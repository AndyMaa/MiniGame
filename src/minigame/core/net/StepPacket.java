package minigame.core.net;

public final class StepPacket extends Packet{
    private static final long serialVersionUID=18834257L;
    public final int x;
    public final int y;

    public StepPacket(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
