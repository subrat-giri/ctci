package ch7ObjectOrientedDesign.parkingLot;

public class MotorCycle extends Vehicle {
    MotorCycle(String plate) {
        super(plate);
        this.type = Type.MOTORCYCLE;
    }
    public Type getType() {
        return this.type;
    }
}
