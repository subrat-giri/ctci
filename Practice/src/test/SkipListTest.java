package test;

import ch7ObjectOrientedDesign.SkipList;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList<Integer, String> skip_list = new SkipList<>(Integer.MIN_VALUE, Integer.MAX_VALUE);
        skip_list.skip_insert(8 , "8jity");
        skip_list.skip_insert(5 , "5jity");
        skip_list.skip_insert(7 , "7ity");
        skip_list.skip_insert(2 , "2jity");
        skip_list.skip_insert(4 , "4jity");
        skip_list.skip_insert(10 , "10jity");

        skip_list.print_skip_list();

        skip_list.skip_insert(8 , "8888jity");
        skip_list.skip_insert(6 , "6jity");
        skip_list.print_skip_list();
    }
}
