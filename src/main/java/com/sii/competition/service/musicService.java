package com.sii.competition.service;

import java.util.ArrayList;
import java.util.List;

import com.sii.competition.dto.SoundDTO;

public class musicService {
	public static List<SoundDTO> getMusicForFront(String musicNote,String id){
		List<SoundDTO> musicForFront = new ArrayList<SoundDTO>() ;
		
		String[] oneSounds = musicNote.split(",");
		for(String oneSound: oneSounds){
			if(oneSound.contains(":")){
				String[] splitedSound = musicNote.split(":");
				musicForFront.add(new SoundDTO(id,splitedSound[0],splitedSound[1]));
			}else{
				musicForFront.add(new SoundDTO(id,"takt","takt"));
			}
		}
		
		return musicForFront;
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
