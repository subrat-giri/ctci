package chp3stacksAndQueues;

import java.util.LinkedList;

public class AnimalShelter {
    private int order;
    private LinkedList<Dog> dogShelter;
    private LinkedList<Cat> catShelter;

    public AnimalShelter() {
        this.order = 0;
        this.dogShelter = new LinkedList<>();
        this.catShelter = new LinkedList<>();
    }

    public static class Animal {
        String name;
        long order;

        public Animal(String name, long order) {
            Animal.this.name = name;
            Animal.this.order = order;
        }

        public String getName() {
            return name;
        }
    }
    private static class Dog extends Animal {
        Dog(String name, long order) {
            super(name, order);
        }
    }
    private static class Cat extends Animal {
        Cat(String name, long order) {
            super(name, order);
        }
    }

    public void enqueueDog(String name) {
        dogShelter.add(new Dog(name, order++));
    }

    public void enqueueCat(String name) {
        catShelter.add(new Cat(name, order++));
    }

    public Animal dequeueAny() {
        if (dogShelter.peekFirst() == null) {
            return catShelter.removeFirst();
        }
        if (catShelter.peekFirst() == null) {
            return dogShelter.removeFirst();
        }
        return dogShelter.peekFirst().order < catShelter.peekFirst().order
                ? dogShelter.removeFirst()
                : catShelter.removeFirst();
    }

    public Animal dequeueDog() {
        return dogShelter.remove();
    }

    public Animal dequeueCat() {
        return catShelter.removeFirst();
    }

}
