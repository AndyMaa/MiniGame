package minigame.ui;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;

public class MusicPlayer {
    private static byte[] music=null;
    private static AudioFormat format;
    public static void playBackground(){
        if (music==null){
            try {
                InputStream is=MusicPlayer.class.getClassLoader().getResource("res/music.wav").openStream();
                BufferedInputStream bis=new BufferedInputStream(is);
                AudioInputStream ais=AudioSystem.getAudioInputStream(bis);
                format=ais.getFormat();
                music=new byte[ais.available()];
                System.out.println(music.length);
                ais.read(music);
                ais.close();
            } catch (IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
        new Thread(()->{
            SourceDataLine sd;
            DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);
            try {
                sd= ((SourceDataLine) AudioSystem.getLine(info));
                sd.open(format, 128000);
//            if (sd.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
//                FloatControl volume = (FloatControl) sd.getControl(FloatControl.Type.MASTER_GAIN);
//                volume.setValue((float) (volume.getMaximum()*0.01));
//            }
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            }
            int buffSize= sd.getBufferSize();
            System.out.println("real buff size is "+buffSize);
            byte[] data=music;
            int pos=0,max= data.length;
            sd.start();
            while (pos<max-buffSize){
                sd.write(data,pos, buffSize);
                pos+=buffSize;
            }
            System.out.println("end");
        }).start();
    }
}
