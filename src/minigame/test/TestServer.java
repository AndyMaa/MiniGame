package minigame.test;

import minigame.core.Util;

import java.net.InetAddress;
import java.util.Arrays;

public class TestServer {
    public static void main(String[] args) throws Exception {
        String ip= InetAddress.getLocalHost().getHostAddress();
        int port=55231;
        String zip= Util.zipAddress(ip,port);
        System.out.println(zip);
        System.out.println(Arrays.toString(Util.unZipAddress(zip)));
    }
}

