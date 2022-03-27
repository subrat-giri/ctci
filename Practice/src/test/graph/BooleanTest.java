package test.graph;

public class BooleanTest {

    public static void change(Boolean bool) {
        bool = false;
    }
    public static void main(String[] args) {
        Boolean bool = true;
        change(bool);
        System.out.println(bool);
    }
}