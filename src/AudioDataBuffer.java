import java.io.*;
import java.nio.file.Files;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioDataBuffer {
    private static final int BUFFER_LENGTH = 1024;

    public static AudioFileFormat fileFormat;
    public static AudioFormat audioFormat;

    public static byte[] fileToByteArray(File sourceFile) throws Exception {
        fileFormat = AudioSystem.getAudioFileFormat(sourceFile);
        audioFormat = fileFormat.getFormat();
        AudioInputStream inputAIS = AudioSystem.getAudioInputStream(sourceFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nBufferSize = BUFFER_LENGTH * audioFormat.getFrameSize();
        byte[] abBuffer = new byte[nBufferSize];
        while (true) {
            int nBytesRead = inputAIS.read(abBuffer);
            if (nBytesRead == -1)
                break;
            baos.write(abBuffer, 0, nBytesRead);
        }
        byte[] abAudioData = baos.toByteArray();
        return abAudioData;
    }

    public static File byteArrayToFile(byte[] abAudioData, String path) throws Exception {
        File targetFile = new File(path + ".wav");
        Files.deleteIfExists(targetFile.toPath());
        AudioFileFormat.Type targetFileType = fileFormat.getType();
        ByteArrayInputStream bais = new ByteArrayInputStream(abAudioData);
        AudioInputStream outputAIS = new AudioInputStream(bais, audioFormat, abAudioData.length / audioFormat.getFrameSize());
        AudioSystem.write(outputAIS, targetFileType, targetFile);
        return targetFile;
    }

}


