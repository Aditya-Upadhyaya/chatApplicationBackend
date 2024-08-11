package com.chatapplication.chatapplication.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapplication.chatapplication.model.RoomResponse;
import com.chatapplication.chatapplication.model.Room;
import com.chatapplication.chatapplication.services.RoomService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class ValidateRoomNumber {

    @Autowired
    private RoomService roomService;
    
    @PostMapping("/addRoom")
    public ResponseEntity<String> addRoomNumber(@RequestBody Room room){
        HttpHeaders headers = new HttpHeaders();
        String str = roomService.addRoom(room);
        ResponseEntity<String> entity = new ResponseEntity<>(str,headers,HttpStatus.CREATED);
        return entity;

    }

    @PostMapping("/joinRoom")
    public ResponseEntity<String> joinRoom(@RequestBody Room room){
        System.out.println("In joinroom");
        HttpHeaders headers = new HttpHeaders();
        String str = roomService.joinRoom(room);
        ResponseEntity<String> entity = new ResponseEntity<>(str,headers,HttpStatus.CREATED);
        return entity;

    }

    @GetMapping("/getRoomDetails")
    public Map<String, Integer>  getRoom(){
        return roomService.getAllRoomDetails();
    }
    
    @GetMapping("/getAllRoomMap")
    public Map<Integer, List<String>> getAllRoomMap(){
        return roomService.getAllRoomMap();
    }

    @GetMapping("/getRoomUsername/{roomNumber}")
    public ResponseEntity<List<String>> getRoomUsername(@PathVariable Integer roomNumber){
        System.out.println("In /getRoomUsername");
        HttpHeaders headers = new HttpHeaders();
        List<String> str = roomService.getRoomUsername(roomNumber);
        ResponseEntity<List<String>> entity = new ResponseEntity<>(str,headers,HttpStatus.CREATED);
        return entity;
    }

    
    @GetMapping("/validateRoomNumber/{roomNumber}")
    public ResponseEntity<RoomResponse> validateRoomNumber(@PathVariable Integer roomNumber){
        RoomResponse res = new RoomResponse();
        if (roomService.validateRoomNumber(roomNumber)) {
            res.setCode("0000");
            res.setMsg("Room find");
        } else {
            res.setCode("9999");
            res.setMsg("Room not find");
        }
        return new ResponseEntity<RoomResponse>(res, HttpStatus.OK);
    }

}
