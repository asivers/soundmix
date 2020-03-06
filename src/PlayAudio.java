import java.io.*;
import javax.sound.sampled.*;

public class PlayAudio {

    public static AudioInputStream audioInputStream;
    public static SourceDataLine sourceDataLine;
    public static byte tempBuffer[] = new byte[10000];
    public static PlayThread playThread;

    public static void play(byte[] audioData, AudioFormat audioFormat) {
        try{
            InputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat, audioData.length/audioFormat.getFrameSize());
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            playThread = new PlayThread();
            Thread startThread = new Thread(playThread);
            startThread.start();
        }
        catch (Exception e) { }
    }

    public static void stop() {
        playThread.stop();
    }

}