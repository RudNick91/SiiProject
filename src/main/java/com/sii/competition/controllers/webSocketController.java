package com.sii.competition.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sii.competition.dto.SoundDTO;


@Controller
public class webSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public  SoundDTO webSocket(SoundDTO message) throws Exception {
        return message;
    }

}
