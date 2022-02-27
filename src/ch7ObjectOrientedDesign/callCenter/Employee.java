package ch7ObjectOrientedDesign.callCenter;

public abstract class Employee {
    public enum Type {
        RESPONDENT, MANAGER, DIRECTOR
    }
    private final String empID;
    private final String name;
    private String address;
    private Type type;
    public Employee(String empID, String name, String address, Type type) {
        this.empID = empID;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getEmpID() {
        return empID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
