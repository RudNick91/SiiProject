package com.sii.competition.controllers;

import java.util.ArrayList;
import java.util.Date;
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
	public String sendSound(SoundDTO message) throws Exception {
		TrackEntity music = trackRepository.getOne(Long.valueOf(message.getId()));
		String musicNote = music.getMusic();
		String[] tact = musicNote.split("\\|");
		String lastTact = "";
		if(tact.length>0){
			lastTact = tact[tact.length-1];
			if(lastTact.indexOf(",")==0){
				lastTact=lastTact.substring(1, lastTact.length());
			}
			
		}

		if(musicNote.equals("")){
			musicNote = message.getLine()+":"+musicService.getApproximatedTime(message.getTime());
		}else{
			int ile=0;
			if(tact.length>0)
			for(String x : lastTact.split(",")){
				ile = ile + Integer.valueOf(x.split(":")[1]);
			}
			String time = musicService.getApproximatedTime(message.getTime());
			if(ile+Integer.valueOf(musicService.getApproximatedTime(message.getTime()))>16){
				if(16-ile!=0){
					musicNote = musicNote + ","+message.getLine()+":"+String.valueOf(16-ile);
					time=String.valueOf(Integer.valueOf(musicService.getApproximatedTime(message.getTime()))-(16-ile));
				}
				musicNote = musicNote + ",|";
			}
			
			musicNote = musicNote + ","+message.getLine()+":"+time;
		}
		
		music.setMusic(musicNote);
		List<SoundDTO> musicForFront= musicService.getMusicForFront(musicNote,message.getId());
		TrackEntity savedMusic =trackRepository.save(music);
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
	
	@RequestMapping(value = "/getAllMusics")
	public List<TrackEntity> getAllMusics() throws Exception {
	    return trackRepository.findAll();
	}
	
	@RequestMapping(value = "/saveMusic")
	public String saveMusic(@RequestBody TrackEntity json) throws Exception {
		TrackEntity music = trackRepository.getOne(json.getId());
		music.setTitle(json.getTitle());
		music.setDate(new Date());
		trackRepository.save(music);
	    return new String("OK");
	}
	@RequestMapping(value = "/getWav")
	public String getWav() throws Exception {
		TrackEntity music = trackRepository.getOne((long) 39);
		String musicNote = music.getMusic();
		musicService.getWav(musicNote, "");
	    return new String("OK");
	}
	
}

