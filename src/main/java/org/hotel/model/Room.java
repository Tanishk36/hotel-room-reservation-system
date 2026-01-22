package org.hotel.model;

public class Room {

    private int roomNumber;
    private int floor;
    private int position; // distance from lift (1 = nearest)
    private boolean occupied;

    public Room(int roomNumber, int floor, int position) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.position = position;
        this.occupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getPosition() {
        return position;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
    }

    @Override
    public String toString() {
        return roomNumber + (occupied ? "(X)" : "(O)");
    }
}
