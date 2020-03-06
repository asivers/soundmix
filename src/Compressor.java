public class Compressor {

    public static byte[] compress(byte[] audioData, int compressorLvl) {
        int size = audioData.length;
        byte[] compressedAudioData = new byte[size];
        for (int i = 0; i < size; i++) {
            compressedAudioData[i] = audioData[i];
        }
        byte lvl = (byte)(128 * compressorLvl / 100);
        for (int i = 0; i < size; i++) {
            if (compressedAudioData[i] > lvl) {
                compressedAudioData[i] = lvl;
            }
            if (compressedAudioData[i] < (byte)-lvl) {
                compressedAudioData[i] = (byte)-lvl;
            }
        }
        return compressedAudioData;
    }

}
