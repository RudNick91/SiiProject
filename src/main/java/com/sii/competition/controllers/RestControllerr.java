package com.sii.competition.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sii.competition.dto.SoundDTO;
import com.sii.competition.entity.TrackEntity;
import com.sii.competition.repositorys.TrackRepository;
import com.sii.competition.service.musicService;

@RestController
	public class RestControllerr {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	TrackRepository trackRepository;
	
	@RequestMapping(value = "/sendSound")
	public String sendSound(@RequestBody SoundDTO message) throws Exception {
		TrackEntity music = trackRepository.getOne(Long.valueOf(message.getId()));
		String musicNote = music.getMusic();
		musicNote = musicNote + ","+message.getLine()+":"+musicService.getApproximatedTime(message.getTime());
		music.setMusic(musicNote);
		List<SoundDTO> musicForFront= musicService.getMusicForFront(musicNote,message.getId());
		TrackEntity x =trackRepository.save(music);
		this.template.convertAndSend("/topic/greetings",musicForFront);
		return new String("OK");
	}
	
	
	@RequestMapping(value = "/startRecord")
	public String sendSound() throws Exception {
		TrackEntity newMusic = new TrackEntity();
		newMusic.setMusic("");
		TrackEntity x =trackRepository.save(newMusic);
	    return String.valueOf(x.getId());
	}
}

