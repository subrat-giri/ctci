package ch7ObjectOrientedDesign.parkingLot;

public class Bus extends Vehicle {
    Bus(String plate) {
        super(plate);
        this.type = Type.BUS;
    }
}
