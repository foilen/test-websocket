package com.foilen.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.foilen.smalltools.tools.AbstractBasics;
import com.foilen.websocket.model.Message;

@Controller
public class MessageController extends AbstractBasics {

    @MessageMapping("message")
    @SendTo("/topic/messages")
    public Message addMessage(Message message) {
        logger.info("Got message: {}", message);
        return message;
    }

}
