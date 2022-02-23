package ch5BitManupulation;

public class DrawLine {
    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int x1ArrayIndex = (y * width +x1) / 8;
        int x2ArrayIndex = (y * width + x2) / 8;
        if (x1ArrayIndex == x2ArrayIndex) {
            screen[x1ArrayIndex] = (byte) (~(-1 << (8 - x1 % 8)) & (-1 << (8 - x2 % 8 - 1)));
        } else {
            int byteAtx1Index =  ~(-1 << (8 - x1 % 8));
            int byteAtx2Index = -1 << (8 - x2 % 8 - 1);
            screen[x1ArrayIndex] = (byte) byteAtx1Index;
            screen[x2ArrayIndex] = (byte) byteAtx2Index;
            for (int i = x1ArrayIndex + 1; i < x2ArrayIndex; i++) {
                screen[i] = -1;
            }
        }
        for (int i = 0; i < 800; i++) {
            if (i % 10 == 0) System.out.println();
            int num = screen[i];
            int div = 128;
            while (div > 0) {
                System.out.print((num & div) == 0 ? "0" : "1");
                div /= 2;
            }
            System.out.print("|");
        }

    }

    public static void main(String[] args) {
        byte[] screen = new byte[800];
        drawLine(screen, 80, 32, 79, 0);
        drawLine(screen, 80, 26, 30, 0);
    }
}
