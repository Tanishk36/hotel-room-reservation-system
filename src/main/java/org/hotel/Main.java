package org.hotel;

import org.hotel.model.Room;
import org.hotel.service.BookingService;
import org.hotel.service.Hotel;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Initialize hotel and booking service
        Hotel hotel = new Hotel();
        BookingService bookingService = new BookingService(hotel);

        // 🔹 Random occupancy simulation (manual for now)
        hotel.getAllRooms().get(0).occupy();   // 101
        hotel.getAllRooms().get(1).occupy();   // 102
        hotel.getAllRooms().get(10).occupy();  // 201

        // 🔹 Try booking rooms
        int roomsToBook = 3;
        System.out.println("Booking " + roomsToBook + " rooms...");

        List<Room> bookedRooms = bookingService.bookRooms(roomsToBook);

        System.out.println("Rooms booked:");
        for (Room room : bookedRooms) {
            System.out.println("Room " + room.getRoomNumber()
                    + " | Floor: " + room.getFloor()
                    + " | Position: " + room.getPosition());
        }
        System.out.println("\nApplying random occupancy...");
        hotel.randomOccupancy();

        System.out.println("Booking 2 rooms after random occupancy:");
        List<Room> randomBooking = bookingService.bookRooms(2);

        for (Room room : randomBooking) {
            System.out.println("Room " + room.getRoomNumber());
        }

        System.out.println("\nResetting hotel...");
        hotel.resetAllRooms();
        System.out.println("All rooms reset successfully.");

    }
}
