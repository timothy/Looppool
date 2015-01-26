/*
 @author Kamonson And Timothy 
 */
package LoopPool;

import java.io.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;
/*
 Custom class AudioFile is a subclass of InputStream to handle custom audio
 */

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

    /**
     *
     * @param s Takes the file location and imports it into an audio file to be
     * used throughout this class
     */
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

    /**
     *
     * @return Returns the frame length of the audio file in the form of a long
     */
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

    /**
     *
     * @return Returns the frame length of the audio file in the form of a
     * String
     */
    public String sLength() {
        return String.valueOf(getFrameLength());
    }

    /**
     * Plays the audio file
     */
    public void AudioPlay() {
        this.audioFile.play();
    }

    /**
     * Stops the Audio file
     */
    public void AudioStop() {
        this.audioFile.stop();
    }

    /**
     *
     * @param t sets the type of audio file to be used
     */
    public void setType(String t) {
        this.type = t;
    }

    /**
     *
     * @return Returns a string representation of the file path
     */
    public String getLocation() {
        return this.location;
    }

    /**
     *
     * @return Required exception for Audio files
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
