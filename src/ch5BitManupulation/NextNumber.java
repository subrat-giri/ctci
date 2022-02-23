package ch5BitManupulation;

public class NextNumber {
    public static int nextSmallestNumber(int n) {
        if (n == Integer.MAX_VALUE || n <= 0) {
            throw new IllegalArgumentException("Can't return number above this");
        }
        int temp = n;
        int setBitCount = 0;
        int clearedBitCount = 0;
        while (temp > 0) {
            if ((temp & 1) == 1) {
                if ((temp & 2) == 0) {
                    clearedBitCount++;
                    setBitCount++;
                    break;
                } else {
                    setBitCount++;
                }
            } else {
                clearedBitCount++;
            }
            temp >>= 1;
        }
        int clearBits = n & (-1 << (clearedBitCount + setBitCount));
        return clearBits | (1 << (clearedBitCount + setBitCount - 1)) | ~(-1 << (setBitCount - 1));
    }

    public static int prevLargestNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Can't return number above this");
        }
        int temp = n;
        int setBitCount = 0;
        int clearedBitCount = 0;
        while (temp > 0) {
            if ((temp & 1) == 0) {
                clearedBitCount++;
                if ((temp & 2) != 0) {
                    setBitCount++;
                    break;
                }
            } else {
                setBitCount++;
            }
            temp >>= 1;
        }
        int clearBits = n & (-1 << (clearedBitCount + setBitCount));
        return clearBits | ((~(-1 << setBitCount)) << (clearedBitCount - 1));
    }

    public static void main(String[] args) {
        System.out.println(nextSmallestNumber(73));
        System.out.println(prevLargestNumber(73));
    }
}
