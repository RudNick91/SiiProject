package com.sii.competition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sii.competition.dto.SoundDTO;
import com.sii.competition.entity.TrackEntity;
import com.sii.competition.repositorys.TrackRepository;

@RestController
	public class RestControllerr {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	TrackRepository trackRepository;
	
	@RequestMapping(value = "/sendSound")
	public String sendSound(SoundDTO message) throws Exception {
		TrackEntity music = trackRepository.getOne(Long.valueOf(message.getId()));
		String musicNote = music.getMusic();
		musicNote = musicNote + ","+message.getLine()+":"+getApproximatedTime(message.getTime());
		this.template.convertAndSend("/topic/greetings",message);
		return new String("OK");
	}
	
	public String getApproximatedTime(String time){
		Float timeFloat= Float.valueOf(time);
		if(timeFloat<2500){
			time = "0";
		}
		if(timeFloat<5000){
			time = "1";
		}
		if(timeFloat<10000){
			time = "2";
		}
		if(timeFloat<15000){
			time = "3";
		}
		return time;
	}
	
	@RequestMapping(value = "/startRecord")
	public String sendSound() throws Exception {
		TrackEntity newMusic = new TrackEntity();
		newMusic.setMusic("");
		TrackEntity x =trackRepository.save(newMusic);
	    return String.valueOf(x.getId());
	}
}

