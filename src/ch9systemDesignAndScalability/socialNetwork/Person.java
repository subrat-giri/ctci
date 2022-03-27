package ch9systemDesignAndScalability.socialNetwork;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int personID;
    private String info;
    private List<Integer> friends;

    public Person(int personID, String info) {
        this.personID = personID;
        this.info = info;
        friends = new ArrayList<>();
    }

    public int getPersonID() {
        return personID;
    }

    public void addFriend(int personID) {
        friends.add(personID);
    }

    public List<Integer> getFriends() {
        return friends;
    }
}
