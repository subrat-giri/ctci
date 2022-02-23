package ch5BitManupulation;

import java.nio.charset.Charset;
import java.util.Scanner;

public class Insertion {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int M = scn.nextInt();
        int j = scn.nextInt();
        int i = scn.nextInt();

        int clearedN = N & ((-1 << j + 1) | (- 1 >>> (Integer.SIZE - i)));
        int shiftedM = M << i;

        System.out.println(Integer.toBinaryString(clearedN | shiftedM));
    }
}
