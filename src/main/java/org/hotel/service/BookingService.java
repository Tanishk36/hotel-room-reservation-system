package org.hotel.service;

import org.hotel.model.Room;
import org.hotel.util.TravelTimeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookingService {

    private Hotel hotel;

    public BookingService(Hotel hotel) {
        this.hotel = hotel;
    }

    // MAIN METHOD CALLED FOR BOOKING
    public List<Room> bookRooms(int numberOfRooms) {

        if (numberOfRooms < 1 || numberOfRooms > 5) {
            throw new IllegalArgumentException("Can book minimum 1 and maximum 5 rooms only");
        }

        // 1️⃣ Try booking on the same floor first
        List<Room> sameFloorBooking = trySameFloorBooking(numberOfRooms);
        if (!sameFloorBooking.isEmpty()) {
            markOccupied(sameFloorBooking);
            return sameFloorBooking;
        }

        // 2️⃣ Else try optimal multi-floor booking
        List<Room> multiFloorBooking = tryMultiFloorBooking(numberOfRooms);
        markOccupied(multiFloorBooking);
        return multiFloorBooking;
    }

    // ---------------- SAME FLOOR BOOKING ----------------

    private List<Room> trySameFloorBooking(int numberOfRooms) {

        List<Room> availableRooms = hotel.getAvailableRooms();
        List<Room> bestSelection = new ArrayList<>();
        int minTravelTime = Integer.MAX_VALUE;

        for (int floor = 1; floor <= 10; floor++) {

            List<Room> floorRooms = new ArrayList<>();
            for (Room room : availableRooms) {
                if (room.getFloor() == floor) {
                    floorRooms.add(room);
                }
            }

            if (floorRooms.size() < numberOfRooms) {
                continue;
            }

            // Sort by position (left to right)
            floorRooms.sort(Comparator.comparingInt(Room::getPosition));

            // Sliding window to find best contiguous set
            for (int i = 0; i <= floorRooms.size() - numberOfRooms; i++) {
                List<Room> candidate = floorRooms.subList(i, i + numberOfRooms);
                int travelTime = TravelTimeUtil.calculateTotalTravelTime(candidate);

                if (travelTime < minTravelTime) {
                    minTravelTime = travelTime;
                    bestSelection = new ArrayList<>(candidate);
                }
            }
        }

        return bestSelection;
    }

    // ---------------- MULTI FLOOR BOOKING ----------------

    private List<Room> tryMultiFloorBooking(int numberOfRooms) {

        List<Room> availableRooms = hotel.getAvailableRooms();
        List<List<Room>> combinations = new ArrayList<>();
        generateCombinations(availableRooms, numberOfRooms, 0, new ArrayList<>(), combinations);

        List<Room> bestSelection = new ArrayList<>();
        int minTravelTime = Integer.MAX_VALUE;

        for (List<Room> combo : combinations) {
            int travelTime = TravelTimeUtil.calculateTotalTravelTime(combo);
            if (travelTime < minTravelTime) {
                minTravelTime = travelTime;
                bestSelection = combo;
            }
        }

        return bestSelection;
    }

    // ---------------- HELPER METHODS ----------------

    private void markOccupied(List<Room> rooms) {
        for (Room room : rooms) {
            room.occupy();
        }
    }

    private void generateCombinations(
            List<Room> rooms,
            int roomsNeeded,
            int index,
            List<Room> current,
            List<List<Room>> result) {

        if (current.size() == roomsNeeded) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (index >= rooms.size()) {
            return;
        }

        // Include current room
        current.add(rooms.get(index));
        generateCombinations(rooms, roomsNeeded, index + 1, current, result);

        // Exclude current room
        current.remove(current.size() - 1);
        generateCombinations(rooms, roomsNeeded, index + 1, current, result);
    }
}
