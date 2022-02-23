package ch5BitManupulation;

public class PairWiseSwap {
    public static int pairWiseSwap(int n) {
        int evenBits = 0xAAAAAAAA  & n;
        int oddBits = 0x55555555 & n;
        return oddBits << 1 | evenBits >>> 1;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(pairWiseSwap(Integer.valueOf("11000110", 2))));
    }
}
