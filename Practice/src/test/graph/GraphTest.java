package test.graph;

import graph.Graph;

import java.util.Arrays;
import java.util.Iterator;

public class GraphTest {
    public static void main(String[] args) {
        Graph<String> friendsNetwork = new Graph<>(11, new String[]{"Subrat",
                "Anand", "Abhishek", "Manohar", "Rishabh", "Nitish", "Niharika", "Aniket", "Rina", "Suraj", "Abhijeet"});
        friendsNetwork.connect(0, 1, 2,3,4,5);
        friendsNetwork.connect(1, 0, 2,3,4, 10);
        friendsNetwork.connect(2, 0,1,3,4);
        friendsNetwork.connect(3, 0,1,2,4);
        friendsNetwork.connect(4, 0,1,2,3);
        friendsNetwork.connect(5, 0, 6,7,8);
        friendsNetwork.connect(6, 5);
        friendsNetwork.connect(7, 5,8);
        friendsNetwork.connect(8, 5,7);
        friendsNetwork.connect(9);

//        Iterator<String> itr1 = friendsNetwork.getBFSIterator();
//        while (itr1.hasNext()) {
//            System.out.print(itr1.next() + " ");
//        }
//        System.out.println();
//        Iterator<String> itr2 = friendsNetwork.getBFSIterator(8);
//        while (itr2.hasNext()) {
//            System.out.print(itr2.next() + " ");
//        }

        Iterator<String> itr1 = friendsNetwork.getDFSIterator();
        while (itr1.hasNext()) {
            System.out.print(itr1.next() + " ");
        }
        System.out.println();
        Iterator<String> itr2 = friendsNetwork.getDFSIterator(8);
        while (itr2.hasNext()) {
            System.out.print(itr2.next() + " ");
        }
    }

}
