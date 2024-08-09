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

    @GetMapping("/getRoomDetails")
    public Map<String, Integer>  getRoom(){
        System.out.println("********** In /getRoom *************");
        return roomService.getAllRoomDetails();
    }
    
    @GetMapping("/getRoomList")
    public List<Integer>  getRoomList(){
        System.out.println("********** In /getRoomList *************");
        System.out.println(roomService.getAllRoomList());
        return roomService.getAllRoomList();
    }

    
    @GetMapping("/validateRoomNumber/{roomNumber}")
    public ResponseEntity<RoomResponse> validateRoomNumber(@PathVariable Integer roomNumber){
        System.out.println("********** In /validateRoomNumber *************");
        RoomResponse res = new RoomResponse();
        System.out.println(roomService.validateRoomNumber(roomNumber));
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
