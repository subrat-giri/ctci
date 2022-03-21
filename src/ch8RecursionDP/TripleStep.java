package ch8RecursionDP;

import java.math.BigInteger;
import java.util.Scanner;

public class TripleStep {
    public static long getWaysIteratively(int n) {
        long[] mem = new long[n + 1];
        mem[0] = 1;
        mem[1] = 1;
        mem[2] = 2;
        for (int i = 3; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2] + mem[i - 3];
        }
        return mem[n];
    }

    public static BigInteger getWaysForLargeNumber(int n) {
        BigInteger[] ways = new BigInteger[n + 1];
        ways[0] = new BigInteger("1");
        ways[1] = new BigInteger("1");
        ways[2] = new BigInteger("2");
        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i - 1].add(ways[i - 2]).add(ways[i - 3]);
        }
        return ways[n];
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        if (n < 0) {
            throw new IllegalArgumentException("Negative input invalid");
        }
        System.out.println(getWaysIteratively(n));
        System.out.println(getWaysForLargeNumber(n));
    }
}
