package com.sii.competition.service;

import java.io.File;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.sii.competition.dto.SoundDTO;

public class musicService {
	public static List<SoundDTO> getMusicForFront(String musicNote,String id){
		List<SoundDTO> musicForFront = new ArrayList<SoundDTO>() ;
		
		String[] oneSounds = musicNote.split(",");
		for(String oneSound: oneSounds){
			if(oneSound.contains(":")){
				String[] splitedSound = oneSound.split(":");
				musicForFront.add(new SoundDTO(id,splitedSound[0],splitedSound[1]));
			}else{
				musicForFront.add(new SoundDTO(id,"takt","takt"));
			}
		}
		
		return musicForFront;
	}
	
	public static void getWav(String musicNote,String id){
		List<SoundDTO> musicForFront = new ArrayList<SoundDTO>() ;
		String[] oneSounds = musicNote.split(",");
		for(String oneSound: oneSounds){
			if(oneSound.contains(":")){
				String[] splitedSound = oneSound.split(":");
				musicForFront.add(new SoundDTO(id,splitedSound[0],splitedSound[1]));
			}else{
				musicForFront.add(new SoundDTO(id,"takt","takt"));
			}
		}
		int i = 0;
		for(SoundDTO node : musicForFront){
			String wavFile1="C:\\nuty\\x1.wav";
		if(i!=0)
			
		    wavFile1 = "C:\\nuty\\wavAll"+String.valueOf(i)+".wav";
		    String wavFile2 = "";
		
		    if(node.getLine().equals("1")){
		    	wavFile2 = "C:\\nuty\\f1.wav";
		    }
		    if(node.getLine().equals("3")){
		    	wavFile2 = "C:\\nuty\\g1.wav";
		    }
		    if(node.getLine().equals("5")){
		    	wavFile2 = "C:\\nuty\\a1.wav";
		    }
		    if(node.getLine().equals("7")){
		    	wavFile2 = "C:\\nuty\\b1.wav";
		    }
		    if(node.getLine().equals("8")){
		    	wavFile2 = "C:\\nuty\\c1.wav";
		    }
		    if(node.getLine().equals("10")){
		    	wavFile2 = "C:\\nuty\\d1.wav";
		    }
		    if(node.getLine().equals("12")){
		    	wavFile2 = "C:\\nuty\\e1.wav";
		    }
		    if(!node.getTime().equals("takt")){
		    	i++;
for(int j=0;j<Integer.valueOf(node.getTime());j++){
    try {
	    AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(wavFile1));
	    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(wavFile2));
	    i++;
	    AudioInputStream appendedFiles = 
                        new AudioInputStream(
                            new SequenceInputStream(clip1, clip2),     
                            clip1.getFormat(), 
                            clip1.getFrameLength() + clip2.getFrameLength());

	    AudioSystem.write(appendedFiles, 
                        AudioFileFormat.Type.WAVE, 
                        new File("C:\\nuty\\wavAll"+String.valueOf(i)+".wav"));
	    wavFile1="C:\\nuty\\wavAll"+String.valueOf(i)+".wav";
	    clip1.close();
	    clip2.close();
	    appendedFiles.close();
    } catch (Exception e) {
	    e.printStackTrace();
    }
}
try {
    AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File("C:\\nuty\\wavAll"+String.valueOf(i)+".wav"));
    AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File("C:\\nuty\\x1.wav"));
	i++;
    AudioInputStream appendedFiles = 
                    new AudioInputStream(
                        new SequenceInputStream(clip1, clip2),     
                        clip1.getFormat(), 
                        clip1.getFrameLength() + clip2.getFrameLength());

    AudioSystem.write(appendedFiles, 
                    AudioFileFormat.Type.WAVE, 
                    new File("C:\\nuty\\wavAll"+String.valueOf(i)+".wav"));
    clip1.close();
    clip2.close();
    appendedFiles.close();
    
} catch (Exception e) {
    e.printStackTrace();
}
		    }
		}
	}
	
	public static String getApproximatedTime(String time){
		Float timeFloat= Float.valueOf(time);
		if(timeFloat<125){
			time = "1";
		}else
		if(timeFloat<250){
			time = "2";
		}else
		if(timeFloat<500){
			time = "4";
		}else
		if(timeFloat<1000){
			time = "8";
		}else{
			time = "16";
		}
	
		return time;
	}
	
	public static String saveWav(String message){

		return null;
	}
}
