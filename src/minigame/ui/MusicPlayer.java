package minigame.ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static byte[] music;
    private static AudioFormat format;
    public static void playBackground(){
        if (music==null){
            try {
                AudioInputStream stream= AudioSystem.getAudioInputStream(new File("src/res/music.mp3"));
                music=new byte[stream.available()];
                stream.read(music);
                format=stream.getFormat();
                stream.close();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SourceDataLine sd;
        DataLine.Info di=new DataLine.Info(SourceDataLine.class,format);
        try {
            sd= ((SourceDataLine) AudioSystem.getLine(di));
            sd.open();
            sd.start();
            sd.write(music,0,music.length);
            sd.drain();
            sd.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
