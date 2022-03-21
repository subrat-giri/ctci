package ch8RecursionDP;

import java.util.*;

public class PermutationWithoutDups  {
    public static List<String> getPermutations(String str) {
        if (str.length() == 0) {
            return Collections.emptyList();
        }
        if (str.length() == 1) {
            return new ArrayList<>(List.of(str));
        }
        List<String> allPermutations  = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char startsWith = str.charAt(i);
            String rest = i == 0 ? str.substring(1) :
                    (i == str.length() - 1 ? str.substring(0, i) : str.substring(0, i) + str.substring(i + 1));
            List<String> restPermutations = getPermutations(rest);
            for (String s : restPermutations) {
                allPermutations.add(startsWith + s);
            }
        }
        return allPermutations;
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(getPermutations(str));
    }
}
