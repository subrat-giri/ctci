package ch7ObjectOrientedDesign.parkingLot;

public abstract class Vehicle {
    protected Type type;
    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public enum Type {
        MOTORCYCLE, CAR, BUS
    }
}
