package LoopPool;

import java.io.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;

public class AudioFile extends InputStream {

    private String fileLoc;
    private AudioClip audioFile;
    private String name;
    private String type;
    private Double length;
    private String location;
    private long frames;

    AudioFile() {
    }

    AudioFile(String s) {
        this.fileLoc = s;
        try {
            URL u = new URL("file:///" + s);
            this.audioFile = Applet.newAudioClip(u);
            this.location = s;
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong in the audio inport" + ex.toString());
        }
    }

    public long getFrameLength() {
        File file = new File(this.fileLoc);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            this.length = (frames + 0.0) / format.getFrameRate();
            frames = (new Double(length).longValue());
            return frames;
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong with getting file length" + ex.toString());
            return this.frames;
        }
    }

    public String sLength() {
        return String.valueOf(getFrameLength());
    }

    public void AudioPlay() {
        this.audioFile.play();
    }

    public void AudioStop() {
        this.audioFile.stop();
    }

    public void setType(String t) {
        this.type = t;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
