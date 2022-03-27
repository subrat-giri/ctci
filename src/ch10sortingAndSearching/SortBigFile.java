package ch10sortingAndSearching;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SortBigFile {
    public static final int chunkSize = 10000000;
    public static final String tFile = "temp-file-";
    public static boolean sort(File file) throws FileNotFoundException {
        int chuckId = 1;
        int[] array = new int[chunkSize];
        List<String> files = new ArrayList<>();
        long count = 0;
        try (Scanner scn = new Scanner(file);) {
            do {
                Path filePath = Paths.get(file.getParent(), tFile + chuckId++ + ".txt");
                int i = 0;
                try (PrintWriter out = new PrintWriter(filePath.toString());) {
                    while (scn.hasNext() && i < chunkSize) {
                        array[i] = scn.nextInt();
                        i++;
                        count++;
                    }
                    Arrays.sort(array, 0, i);
                    for (int j = 0; j < i; j++) {
                        out.println(array[j]);
                    }
                }
                files.add(filePath.toString());
            } while (scn.hasNext() && count <= 60000000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
        Scanner[] scanners = new Scanner[files.size()];
        for (int i = 0; i <files.size(); i++) {
            scanners[i] = new Scanner(new File(files.get(i)));
        }
        int[] mins = new int[files.size()];
        for (int i = 0; i < files.size(); i++) {
            mins[i] = scanners[i].hasNext() ? scanners[i].nextInt() : Integer.MAX_VALUE;
        }
        try (PrintWriter pw = new PrintWriter(Paths.get(file.getParent(), "sorted-data.txt").toString());) {
            for (long i = 0; i < count; i++) {
                int minFile = 0, minVal = mins[0];
                for (int j = 1; j < files.size(); j++) {
                    if (mins[j] < minVal) {
                        minVal = mins[j];
                        minFile = j;
                    }
                }
                pw.println(minVal);
                mins[minFile] = scanners[minFile].hasNext() ? scanners[minFile].nextInt() : Integer.MAX_VALUE;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't sort");
            throw e;
        }

        for (int i = 0; i < files.size(); i++) {
            scanners[i].close();
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try {
            sort(new File("C:\\Users\\subrat\\Documents\\externalSortData1.txt"));
//            PrintWriter pw = new PrintWriter("C:\\Users\\subrat\\Documents\\externalSortData1.txt");
//            Random rnd = new Random();
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                pw.println(rnd.nextInt(Integer.MAX_VALUE));
//            }
//            pw.close();

//            Scanner scn = new Scanner(new File("C:\\Users\\subrat\\Documents\\externalSortData1.txt" ));
//            int count = 0;
//            while (scn.hasNextLine()) {
//                count++;
//            }
//            System.out.println(count);
//            scn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
