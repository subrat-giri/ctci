package ch8RecursionDP;

import java.util.Random;
import java.util.Scanner;

public class RobotsInGrid {

    public static void printPath(int[][] grid) {
        int r = grid.length;
        int c = r > 0 ? grid[0].length : 0;
        if (grid[r - 1][c - 1] == -1) {
            System.out.println("Not possible.");
            return;
        }
        boolean[][] reachable = new boolean[r][c];

        for (int i = r - 1; i >= 0 ; i--) {
            for (int j = c - 1; j >= 0 ; j--) {
                if (i == r -1 && j == c - 1) {
                    reachable[i][j] = true;
                    continue;
                }
                boolean isReachableFromRight = j + 1 < c && reachable[i][j + 1];
                boolean isReachableFromDown = i + 1 < c && reachable[i + 1][j];
                if (grid[i][j] == 0 && (isReachableFromDown || isReachableFromRight)) {
                    reachable[i][j] = true;
                }
            }
        }
        System.out.println(reachable[0][0]);

        //printing arbitrary path
        Random rand = new Random();
        int i = 0, j = 0;
        while (i < r && j < c) {
            System.out.println("(" + i + ", " + j + ") ");
            boolean isReachableFromRight = j + 1 < c && reachable[i][j + 1];
            boolean isReachableFromBelow = i + 1 < r && reachable[i + 1][j];
            if (isReachableFromBelow && isReachableFromRight) {
                if (rand.nextBoolean()) {
                    i = i + 1;
                } else {
                    j = j + 1;
                }
            } else if (isReachableFromBelow) {
                i++;
            } else {
                j++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int r = scn.nextInt();
        int c = scn.nextInt();
        int[][] grid = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = scn.nextInt();
            }
        }
        printPath(grid);
    }
}
