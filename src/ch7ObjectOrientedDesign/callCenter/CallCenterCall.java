package ch7ObjectOrientedDesign.callCenter;

import java.util.LinkedList;
import java.util.List;

public class CallCenterCall {
    private String number;
    private boolean isAttended = false;
    private boolean isServed = false;
    private List<Employee> atteneded = new LinkedList<>();

    CallCenterCall(String number) {
        this.number = number;
    }
}
