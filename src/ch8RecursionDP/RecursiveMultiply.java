package ch8RecursionDP;

import java.util.Scanner;

public class RecursiveMultiply {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int x = scn.nextInt();
        int y = scn.nextInt();
        System.out.println(recursiveMultiply(x, y));
    }

    private static int recursiveMultiply(int x, int y) {
        if (y == 1) {
            return x;
        }
        int result = 0;
        if ((y & 1) == 1) {
             result += x;
        }
        result += recursiveMultiply(x << 1, y >> 1);
        System.out.println("X:" + x + " Y:" + y + " Result: " + result);
        return result;
    }
}
