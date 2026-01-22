package org.hotel.util;

import org.hotel.model.Room;

import java.util.List;

public class TravelTimeUtil {

    public static int calculateTotalTravelTime(List<Room> rooms) {

        if (rooms == null || rooms.size() <= 1) {
            return 0;
        }

        int minFloor = Integer.MAX_VALUE;
        int maxFloor = Integer.MIN_VALUE;
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;

        for (Room room : rooms) {
            minFloor = Math.min(minFloor, room.getFloor());
            maxFloor = Math.max(maxFloor, room.getFloor());
            minPos = Math.min(minPos, room.getPosition());
            maxPos = Math.max(maxPos, room.getPosition());
        }

        int verticalTime = (maxFloor - minFloor) * 2;
        int horizontalTime = (maxPos - minPos);

        return verticalTime + horizontalTime;
    }
}

