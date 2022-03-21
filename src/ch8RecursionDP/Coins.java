package ch8RecursionDP;

public class Coins {
    public static int numberOfWays(int cents, int limit) {
        if (cents == 0 || cents == 1) {
            return 1;
        }
        int total = 0;
        if (cents >= 25 && limit >= 25) {
            total += numberOfWays(cents -  25, 25);
        }
        if (cents >= 10 && 10 <= limit) {
            total += numberOfWays(cents -  10, 10);
        }
        if (cents >= 5 && limit >= 5) {
            total += numberOfWays(cents -  5, 5);
        }
        total += numberOfWays(cents -  1, 1);
        return total;
    }

    public static int findWays(int amount, int[] denoms, int index, int[][] map) {
        if (index >= denoms.length - 1) {
            map[amount][index] = 1;
            return 1;
        }
        int ways = 0;
        int denom = denoms[index];
        for (int i = 0; i * denom <= amount; i++) {
            int w, remainingAmount = amount - i * denom;
            ways += ((w = map[remainingAmount][index + 1]) !=-1) ? w : findWays(remainingAmount, denoms, index + 1, map);
        }
        map[amount][index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int[] denoms = {25, 10, 5, 1};
        int amount = 100;
        System.out.println(numberOfWays(amount, 25));
        int[][] map = new int[amount + 1][denoms.length];
        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < denoms.length; j++) {
                map[i][j] = -1;
            }
        }
        System.out.println(findWays(amount, denoms, 0, map));

//        for (int i = 0; i < amount + 1; i++) {
//            for (int j = 0; j < denoms.length; j++) {
//                System.out.print(map[i][j] + ", ");
//            }
//            System.out.println();
//        }
    }
}
