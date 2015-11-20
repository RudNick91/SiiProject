package com.sii.competition.controllers;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
	public void sendSound(@RequestBody SoundDTO message) throws Exception {
		trackRepository.getOne(Long.valueOf(message.getId()));
		this.template.convertAndSend("/topic/greetings",message);
	}
	@RequestMapping(value = "/startRecord")
	public String sendSound() throws Exception {
		TrackEntity newMusic = new TrackEntity();
		trackRepository.save(newMusic);
	    return String.valueOf(newMusic.getId());
	}
}

