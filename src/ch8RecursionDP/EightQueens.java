package ch8RecursionDP;

import java.util.Arrays;

public class EightQueens {
    public static boolean isIntersecting(int[] map, int x, int y) {
        //test for row
        if (map[x] != -1) {
            return true;
        }

        //test for diagonal intersection
        for (int i = 0; i < map.length; i++) {
            if (map[i] == y) {
                return true;
            }
            if (map[i] != -1) {
                int deltaX = Math.abs(x - i);
                int deltaY = Math.abs(y - map[i]);
                if (deltaX == deltaY) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int findWays(int[] map, int y) {
        if (y >= map.length) {
            return 1;
        }
        int way = 0;
        for (int x = 0; x < map.length; x++) {
            if (!isIntersecting(map, x, y)) {
                map[x] = y;
                way += findWays(map, y + 1);
                map[x] = -1;
            }
        }
        return way;
    }

    public static void main(String[] args) {
        int[] map = new int[8];
        Arrays.fill(map, -1);
        System.out.println(findWays(map, 0));
    }
}
