package LoopPool;

import java.io.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;

public class AudioFile extends Thread {

    private String fileLoc;
    private AudioClip audioFile;
    private String name;
    private String type;
    private Double length;

    AudioFile() {
    }

    AudioFile(String s) {
        this.fileLoc = s;
        try {
            URL u = new URL("file:///" + s);
            this.audioFile = Applet.newAudioClip(u);
            // audioFile.play();
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong in the audio inport" + ex.toString());
        }
    }

    public double getLegth() {
        File file = new File(this.fileLoc);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            this.length = (frames + 0.0) / format.getFrameRate();
            return (frames + 0.0) / format.getFrameRate();
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong with getting file length" + ex.toString());
            return this.length;
        }
    }

    public String sLength() {
        return String.valueOf(getLegth());
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

}
