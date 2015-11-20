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
			}
		}
		
		return musicForFront;
	}
	
	public static String getApproximatedTime(String time){
		Float timeFloat= Float.valueOf(time);
		if(timeFloat<2500){
			time = "0";
		}else
		if(timeFloat<5000){
			time = "1";
		}else
		if(timeFloat<10000){
			time = "2";
		}else
		if(timeFloat<15000){
			time = "3";
		}
		return time;
	}
}
