package ch7ObjectOrientedDesign.parkingLot;

public class Car extends Vehicle {
    Car(String plate) {
        super(plate);
        this.type = Type.CAR;
    }
}
