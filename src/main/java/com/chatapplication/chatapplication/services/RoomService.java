package com.chatapplication.chatapplication.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.chatapplication.chatapplication.model.Room;

@Service
public class RoomService {
    
    Map<String, Integer> map = new HashMap<>();

    public String addRoom(Room room){
        map.put(room.getCreatorName(), room.getRoomNumber());
        System.out.println("room added" +  map);
        String myJSON ="{\"code\":\"0000\",\"msg\":\"room number added to list\"}";
        return myJSON;
    }

    public Map<String, Integer> getAllRoomDetails() {
        return map; 
    }

    public List<Integer> getAllRoomList() {
        List<Integer> values = new ArrayList<>(map.values());
        return values; 
    }

    public boolean validateRoomNumber(int roomNumber){
        List<Integer> values = new ArrayList<>(map.values());
        if (values.contains(roomNumber)) {
            return true;
        } else {
            return false;
        }
    }
}
