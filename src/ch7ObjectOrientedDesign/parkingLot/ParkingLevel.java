package ch7ObjectOrientedDesign.parkingLot;

import java.util.List;

public class ParkingLevel {
    private int level;
    private int totalSpots;
    private List<ParkingSpot> spots;

    public ParkingLevel(int level, int totalSpots, List<ParkingSpot> spots) {
        this.level = level;
        this.totalSpots = totalSpots;
        this.spots = spots;
    }

    public boolean isAvailable(Vehicle.Type type) {
        return false;
    }
}
