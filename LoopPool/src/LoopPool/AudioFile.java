package LoopPool;

import java.io.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;

public class AudioFile extends Thread {

    AudioFile() {
    }
    
    AudioFile(String fileLoc) {

    }
//file:///C:/Users/kamonson17/Documents/GitHub/Looppool/JavaApplication4/src/LoopPool/test.wav

    public void getAudio(String fileLoc) {
       
        try {
            URL u = new URL("file:///" + fileLoc);
            AudioClip wav = Applet.newAudioClip(u);
            wav.play();
        } catch (Exception ex) {
            System.out.println("Ah crap...something went wrong in the audio inport");
        }
    }
        
}
