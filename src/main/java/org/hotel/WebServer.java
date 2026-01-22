package org.hotel;

import com.google.gson.Gson;
import org.hotel.model.Room;
import org.hotel.service.BookingService;
import org.hotel.service.Hotel;

import static spark.Spark.*;

import java.util.List;

public class WebServer {

    public static void main(String[] args) {

        port(4567);

        Hotel hotel = new Hotel();
        BookingService bookingService = new BookingService(hotel);
        Gson gson = new Gson();

        // Serve frontend
        staticFiles.location("/public");

        // View rooms
        get("/rooms", (req, res) -> {
            res.type("application/json");
            return gson.toJson(hotel.getAllRooms());
        });

        // Book rooms
        post("/book/:count", (req, res) -> {
            int count = Integer.parseInt(req.params(":count"));
            List<Room> booked = bookingService.bookRooms(count);
            return gson.toJson(booked);
        });

        // Random occupancy
        post("/random", (req, res) -> {
            hotel.randomOccupancy();
            return "Random occupancy applied";
        });

        // Reset
        post("/reset", (req, res) -> {
            hotel.resetAllRooms();
            return "Reset successful";
        });
    }
}
