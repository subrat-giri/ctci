package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestFileSystem {
    public static void main(String[] args) {
//        FileSystem fs = new FileSystem();
//        System.out.println(fs.ls("/"));
//        fs.mkdir("/a/b/c");
//        fs.addContentToFile("/a/b/c/d", "hello");
//        System.out.println(fs.ls("/"));
//        System.out.println(fs.readContentFromFile("/a/b/c/d"));
        System.out.println(Thread.currentThread().getId());
        new Thread(() -> {
            System.out.println(Thread.currentThread().getId());
        }).start();

        try (BufferedReader reader = new BufferedReader(new FileReader("/kjd/kjdk"))) {
            int maxCount = reader.lines().mapToInt(String::length).max().orElse(0);
        } catch (IOException e) {

        }

    }
}
