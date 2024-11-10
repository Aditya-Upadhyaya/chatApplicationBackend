package com.chatapplication.chatapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    
    private String sendername;
    private String receivername;
    private String message;
    private String timestamp;
    private Status Status;

    public void printallData(){
        System.out.println("Send name : "+getSendername());
        System.out.println("Receiver name :"+getReceivername());
        System.out.println("Message : "+getMessage());
    }
}
