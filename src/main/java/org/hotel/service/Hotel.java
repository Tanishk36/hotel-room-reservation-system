package org.hotel.service;
import java.util.Random;


import org.hotel.model.Room;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
        generateRooms();
    }

    private void generateRooms() {

        // Floors 1 to 9 (10 rooms each)
        for (int floor = 1; floor <= 9; floor++) {
            for (int pos = 1; pos <= 10; pos++) {
                int roomNumber = floor * 100 + pos;
                rooms.add(new Room(roomNumber, floor, pos));
            }
        }

        // Floor 10 (7 rooms)
        for (int pos = 1; pos <= 7; pos++) {
            int roomNumber = 1000 + pos;
            rooms.add(new Room(roomNumber, 10, pos));
        }
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isOccupied()) {
                available.add(room);
            }
        }
        return available;
    }

    public void resetAllRooms() {
        for (Room room : rooms) {
            room.vacate();
        }
    }
    public void randomOccupancy() {
        Random random = new Random();

        for (Room room : rooms) {
            // 30% chance room is occupied
            if (random.nextInt(100) < 30) {
                room.occupy();
            } else {
                room.vacate();
            }
        }
    }


}
