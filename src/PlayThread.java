public class PlayThread implements Runnable{
    private volatile boolean exit = false;

    public void run(){
        try {
            int cnt;
            while((cnt = PlayAudio.audioInputStream.read(PlayAudio.tempBuffer, 0, PlayAudio.tempBuffer.length)) != -1)
                if ((cnt > 0) && (!exit))
                    PlayAudio.sourceDataLine.write(PlayAudio.tempBuffer, 0, cnt);
            PlayAudio.sourceDataLine.drain();
            PlayAudio.sourceDataLine.close();
        }
        catch (Exception e) { }
    }

    public void stop(){
        exit = true;
    }
}