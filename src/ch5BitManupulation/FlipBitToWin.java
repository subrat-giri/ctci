package ch5BitManupulation;

import java.util.Scanner;

public class FlipBitToWin {
    public static int longestSequenceLength(int a) {
        int comb1Count = 0;
        boolean isComb1Turn = true;
        int comb2Count = 0;
        int largestLengthCurr = 0;
        boolean isZeroEncountered = false ;
        while (a > 0) {
            if (a % 2 == 1) {
                if (isComb1Turn) {
                    comb1Count++;
                } else {
                    comb2Count++;
                }
                isZeroEncountered = false;
            } else {
                if (isZeroEncountered) {
                    //Iteration over
                    int count = comb1Count + comb2Count + 1;
                    if (count > largestLengthCurr) {
                        largestLengthCurr = count;
                    }
                    isComb1Turn = true;
                    comb1Count = 0;
                    comb2Count = 0;
                } else {
                    if (comb1Count > 0 ) {
                        if (comb2Count > 0) {
                            int totalCount = comb1Count + comb2Count + 1;
                            if (totalCount > largestLengthCurr) {
                                largestLengthCurr = totalCount;
                            }
                            comb1Count = comb2Count;
                            comb2Count = 0;
                        }
                        isComb1Turn = false;
                    }
                }
                isZeroEncountered = true;
            }
            a = a/2;
        }
        int totalCount = comb1Count + comb2Count + 1;
        if (totalCount > largestLengthCurr) {
            largestLengthCurr = totalCount;
        }
        return largestLengthCurr;
    }

    public static int longestSequenceLength2(int n) {
        int max = 1;
        int currCount = 0;
        int preCount = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                currCount++;
            } else {
                preCount = (n & 2) == 0 ? 0 : currCount;
                currCount = 0;
            }
            max = Math.max(max, preCount + currCount + 1);
            n >>>= 1;
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(longestSequenceLength(n));
        System.out.println(longestSequenceLength2(n));
    }
}