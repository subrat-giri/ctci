package ch7ObjectOrientedDesign.callCenter;

import java.util.concurrent.BlockingQueue;

public class CallDispatcher implements Runnable {
    BlockingQueue<CallCenterCall> queue;

    public CallDispatcher(BlockingQueue<CallCenterCall> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while(true) {
                CallCenterCall callToAttendNext = queue.take();

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
