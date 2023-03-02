package minigame.test;

import minigame.core.Game;
import minigame.core.Util;
import minigame.core.server.MainServer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestServer {
    public static void main(String[] args) throws Exception {
        String ip="123.232.23.12";
        int port=34231;
        String zip= Util.zipAddress(ip,port);
        System.out.println(zip);
        System.out.println(Arrays.toString(Util.unZipAddress(zip)));
    }
}

