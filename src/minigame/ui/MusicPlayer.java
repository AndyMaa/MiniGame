package minigame.ui;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static byte[] music;
    private static AudioFormat format;
    public static void playBackground(){
        AudioClip clip=new AudioClip(MusicPlayer.class.getClassLoader().getResource("res/music.ogg").toString());
        clip.play();
    }
}
