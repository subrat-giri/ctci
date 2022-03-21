package ch8RecursionDP;

import java.util.ArrayList;
import java.util.List;

public class Parens {
    public static List<String> getAllValidCombinations(int num) {
        if (num == 1) {
            return List.of("()");
        }
        List<String> allCombos = new ArrayList<>();
        List<String> validCombos = getAllValidCombinations(num - 1);
        for (String str : validCombos) {
            allCombos.add("(" + str + ")");
            String before = "()" + str;
            String after = str + "()";
            allCombos.add(after);
            if (!before.equals(after)) {
                allCombos.add(before);
            }
        }
        return allCombos;
    }

    public static void main(String[] args) {
        System.out.println(getAllValidCombinations(4));
    }
}
