package com.chatapplication.chatapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.chatapplication.chatapplication.model.Message;

@Controller
public class ChatController {
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    
     // Handles messages from /app/message. (Note the Spring adds the /app prefix for us).
    @MessageMapping("/message/{id}")
     // Sends the return value of this method to /chatroom/public
    // @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message msg , @DestinationVariable String id){
        simpMessagingTemplate.convertAndSend("/chatroom/public/"+id,msg);
        msg.printallData();
        System.out.println("****In public message** Id = "+id);
        return msg;
    }

    @MessageMapping("/privateMessage")
    public Message sendPrivateMessage(@Payload Message msg){
        simpMessagingTemplate.convertAndSendToUser(msg.getReceivername(),"/private",msg);
        System.out.println("In private message");
        // System.out.println(msg);
        return msg;
    }

}
