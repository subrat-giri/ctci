package ch7ObjectOrientedDesign.callCenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CallCenter {
    public static final int BOUND = 1000;
    private BlockingQueue<CallCenterCall> callQueue;
    CallCenter(BlockingQueue<CallCenterCall> callQueue) {
        this.callQueue = callQueue;
    }
    public void dispatchCall(CallCenterCall call) {
        callQueue.add(call);
    }

    public static void main(String[] args) {
        BlockingQueue<CallCenterCall> callQueue = new LinkedBlockingQueue<>(BOUND);
        CallCenter center = new CallCenter(callQueue);
        new Thread(new CallDispatcher(callQueue)).start();
        center.dispatchCall(new CallCenterCall("87867"));
    }
}
