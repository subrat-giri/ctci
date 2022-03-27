package ch9systemDesignAndScalability.socialNetwork;

import java.util.*;

public class SocialNetwork {
    private Map<Integer, Person> idToPersonMap;

    public SocialNetwork() {
        idToPersonMap = new HashMap<>();
    }

    public void addPerson(Person person) {
        idToPersonMap.put(person.getPersonID(), person);
    }

    public void addFriendShip(int id1, int id2) {
        idToPersonMap.get(id1).addFriend(id2);
        idToPersonMap.get(id2).addFriend(id1);
    }

    private static class PathNode {
        Person person;
        PathNode previous;
        PathNode(Person person, PathNode prev) {
            this.person = person;
            this.previous = prev;
        }
        List<Integer> getCompletePath(boolean fromSource) {
            PathNode node = previous;
            LinkedList<Integer> pathList = new LinkedList<>();
            while (node != null) {
                if (fromSource) {
                    pathList.addFirst(node.person.getPersonID());
                } else {
                    pathList.addLast(node.person.getPersonID());
                }
                node = node.previous;
            }
            return pathList;
        }
    }

    private static class BFSData {
        Map<Integer, PathNode> visited = new HashMap<>();
        Queue<Integer> toVisit = new LinkedList<>();

        BFSData(Person person) {
            toVisit.add(person.getPersonID());
            visited.put(person.getPersonID(), new PathNode(person, null));
        }

        public boolean isFinished() {
            return toVisit.isEmpty();
        }
    }

    public List<Integer> biDirectionalBFS(int sourceID, int destinationID) {

        BFSData source =  new BFSData(idToPersonMap.get(sourceID));
        BFSData destination =  new BFSData(idToPersonMap.get(destinationID));

        while (!(source.isFinished() || destination.isFinished())) {
            // Find collision from source side
            Person person = searchLevel(source, destination);
            if (person != null) {
                return mergePath(source, destination, person);
            }

            person = searchLevel(destination, source);
            if (person != null) {
                return mergePath(destination, source , person);
            }
        }
        return Collections.emptyList();
    }

    private List<Integer> mergePath(BFSData source, BFSData destination, Person person) {
        List<Integer> sourcePath = source.visited.get(person.getPersonID()).getCompletePath(true);
        List<Integer> destinationPath = destination.visited.get(person.getPersonID()).getCompletePath(false);

        destinationPath.remove(person.getPersonID());
        sourcePath.addAll(destinationPath);
        return sourcePath;
    }

    private Person searchLevel(BFSData primary, BFSData secondary) {
        int count = primary.toVisit.size();
        while(count--  > 0) {
            Integer id = primary.toVisit.poll();
            Person person = idToPersonMap.get(id);
            if (secondary.visited.containsKey(id)) {
                return person;
            }
            for (Integer i : person.getFriends()) {
                if (!primary.visited.containsKey(i)) {
                    PathNode next = new PathNode(idToPersonMap.get(i), primary.visited.get(id));
                    primary.toVisit.add(i);
                    primary.visited.put(i, next);
                }
            }
        }
        return null;
    }
}
