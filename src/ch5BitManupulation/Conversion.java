package ch5BitManupulation;

import java.util.Scanner;

public class Conversion {
    public static int numberOfBitsToFlip(int n, int m) {
        int counter = 0;
        while (n > 0 || m > 0) {
            if ((n & 1) != (m & 1)) {
                counter++;
            }
            n >>>= 1;
            m >>>= 1;
        }
        return counter;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(numberOfBitsToFlip(n, m));
    }
}
