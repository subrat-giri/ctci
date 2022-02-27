package ch7ObjectOrientedDesign.callCenter;

public class Respondent extends Employee {
    public Respondent(String empID, String name, String address) {
        super(empID, name, address, Type.RESPONDENT);
    }
}
