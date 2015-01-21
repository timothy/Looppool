package LoopPool;

import java.io.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;

public class AudioFile extends Thread {

    AudioFile() {
    }

    public void getAudio() {
        try {
            URL u = new URL("test.wav");
            AudioClip wav = Applet.newAudioClip(u);
            wav.play();
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong in the audio inport");
        }
    }
}
