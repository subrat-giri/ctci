package ch8RecursionDP;

import java.util.Arrays;
import java.util.Comparator;

public class StackOfBoxes {
    static class Box {
        int height;
        int depth;
        int width;
        Box(int height, int width, int depth) {
            this.height = height;
            this.width = width;
            this.depth = depth;
        }
    }

    public static boolean compare(Box a, Box b) {
        return a.height > b.height && a.depth > b.depth && a.width > b.width;
    }

    public static int calculateMaxHeight(Box[] boxes, int bottomIndex) {
        if (bottomIndex == boxes.length - 1) {
            return boxes[bottomIndex].height;
        }
        int maxHeight = boxes[bottomIndex].height;
        for (int i = bottomIndex + 1; i < boxes.length; i++) {
            if (compare(boxes[bottomIndex], boxes[i])) {
                int height = boxes[bottomIndex].height + calculateMaxHeight(boxes, i);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        return maxHeight;
    }

    public static int calculateMaxHeight(Box[] boxes) {
        Arrays.sort(boxes, Comparator.comparingInt(a -> a.height));
        int maxHeight = 0;
        for (int i = 0; i < boxes.length; i++) {
            int height = calculateMaxHeight(boxes, i);
            maxHeight = Math.max(height, maxHeight);
        }
        return maxHeight;
    }

    public static void main(String[] args) {

    }
}
