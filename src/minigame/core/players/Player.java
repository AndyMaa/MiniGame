package minigame.core.players;

import minigame.core.server.Server;

/**
 * 为什么要使用接口？
 * 后期如果多人对战的话，接口方便维护
 */
public interface Player {
    /**
     * 下一步棋
     */
    void step(int x, int y);
    void join(Server server);
    void logout();
    /**
     * 设置玩家棋子的id
     */
    void setId(int id);
    int getId();
}
