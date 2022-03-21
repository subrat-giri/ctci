package ch7ObjectOrientedDesign.parkingLot;

public class ParkingSpot {
    private Vehicle.Type type ;
    private int level;
    private String number;
    private boolean isOccupied;

    public ParkingSpot(Vehicle.Type type, int level, String number) {
        this.type = type;
        this.level = level;
        this.number = number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
