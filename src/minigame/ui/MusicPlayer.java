package minigame.ui;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {
    private static byte[] music;
    private static AudioFormat format;
    public static void playBackground(){
        try {
            InputStream is=MusicPlayer.class.getClassLoader().getResource("res/music.ogg").openStream();
            BufferedInputStream bis=new BufferedInputStream(is);
            bis.mark(1024);
            AudioInputStream ais=AudioSystem.getAudioInputStream(bis);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        AudioClip clip= Applet.newAudioClip(MusicPlayer.class.getClassLoader().getResource("res/music.wav"));
//        clip.
        clip.play();
        System.out.println("wait");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
