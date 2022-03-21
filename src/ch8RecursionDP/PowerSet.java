package ch8RecursionDP;

import java.util.*;

public class PowerSet {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int[] set = new int[num];
        for (int i = 0; i < num; i++) {
            set[i] = scn.nextInt();
        }
        List<Set<Integer>> listOfSets = new ArrayList<>();
        printAllSet(set, listOfSets, 0);
        System.out.println(listOfSets);
    }

    private static void printAllSet(int[] set, List<Set<Integer>> listOfSets, int index) {
        if (index >= set.length) {
            listOfSets.add(new HashSet<>());
            return;
        }
        printAllSet(set, listOfSets, index + 1);
        for(ListIterator<Set<Integer>> itr = listOfSets.listIterator(); itr.hasNext(); ) {
            Set<Integer> copy = new HashSet<>(itr.next());
            copy.add(set[index]);
            itr.add(copy);
        }
    }
}
