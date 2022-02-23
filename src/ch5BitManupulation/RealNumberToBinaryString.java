package ch5BitManupulation;

public class RealNumberToBinaryString {
    public static String toString1(double n) {
        if (n <= 0 || n >= 1) {
            return "ERROR";
        }
        StringBuilder str = new StringBuilder();
        str.append(".");
        int digitCount = 1;
        while (n > 0) {
            if (digitCount >= 32) {
                return "ERROR";
            }
            double update = n * 2;
            if (update >= 1) {
                str.append(1);
                update = update - 1;
            } else {
                str.append(0);
            }
            n = update;
            digitCount++;
        }
        return str.toString();
    }
    public static String toString2(double n) {
        if (n <= 0 || n >= 1) {
            return "ERROR";
        }
        StringBuilder str = new StringBuilder();
        str.append(".");
        double base = 0.5;
        int digitCount = 1;
        while (n > 0 && digitCount <= 32) {
            if (n >= base) {
                str.append(1);
                n -= base;
            } else {
                str.append(0);

            }
            digitCount++;
            base = base / 2;
        }
        if (n > 0) {
            return "ERROR";
        } else {
            return str.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(toString1(0.75));
        System.out.println(toString2(0.75));
    }
}
