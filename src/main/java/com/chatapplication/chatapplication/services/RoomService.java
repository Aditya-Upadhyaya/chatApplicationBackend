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
    Map<Integer, List<String>> usernameListMap = new HashMap<>();

    public String addRoom(Room room){
        map.put(room.getCreatorName(), room.getRoomNumber());
        List<String> userList = new ArrayList<>();
        userList.add(room.getCreatorName());
        usernameListMap.put(room.getRoomNumber(), userList);
        System.out.println("room added" +  map);
        String myJSON ="{\"code\":\"0000\",\"msg\":\"room number added to list\"}";
        return myJSON;
    }

    public String joinRoom(Room room){
        List<String> userList = usernameListMap.get(room.getRoomNumber());
        userList.add(room.getCreatorName());
        System.out.println("userList : " +  userList);
        usernameListMap.replace(room.getRoomNumber(), userList);
        System.out.println("userListMap : " +  usernameListMap);
        String myJSON ="{\"code\":\"0000\",\"msg\":\"Username added to UserName list\"}";
        return myJSON;
    }

    public Map<String, Integer> getAllRoomDetails() {
        return map; 
    }

    public  Map<Integer, List<String>> getAllRoomMap() {
        return usernameListMap; 
    }

    public List<String> getRoomUsername(Integer roomNumber) {
        List<String> values = new ArrayList<>();
        if (usernameListMap.get(roomNumber)==null) {
            values = new ArrayList<>();
        }
        else{
            values = new ArrayList<>(usernameListMap.get(roomNumber));
        }
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
