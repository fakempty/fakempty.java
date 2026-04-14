import java.util.*;

// --- 1. Реєстр студентів ---
class Student {
    private String name;
    public Student(String name) { this.name = name; }
    @Override
    public String toString() { return "Student: " + name; }
}

// --- 3. Generic Box ---
class Box<T> {
    private T item;
    public void put(T item) { this.item = item; }
    public T take() { return item; }
}

// --- 5. Generic Pair ---
class Pair<K, V> {
    K first;
    V second;
    public Pair(K first, V second) { this.first = first; this.second = second; }
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.first.equals(p2.first) && p1.second.equals(p2.second);
    }
}

// --- 6. Upper-bounded wildcard (Shapes) ---
abstract class Shape { abstract double getArea(); }
class Circle extends Shape {
    double r;
    Circle(double r) { this.r = r; }
    double getArea() { return Math.PI * r * r; }
}
class Rectangle extends Shape {
    double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    double getArea() { return w * h; }
}

// --- 8. Animal Hierarchy ---
abstract class Animal { abstract void makeSound(); }
class Dog extends Animal { void makeSound() { System.out.println("Woof!"); } }
class Cat extends Animal { void makeSound() { System.out.println("Meow!"); } }
class Labrador extends Dog { void makeSound() { System.out.println("Labrador woof!"); } }

public class Main {

    // 2. Сховище унікальних елементів
    public static <T> Collection<T> getUnique(List<T> list) {
        return new HashSet<>(list);
    }
    public static <T> Map<T, Integer> countOccurrences(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        for (T obj : list) map.put(obj, map.getOrDefault(obj, 0) + 1);
        return map;
    }

    // 4. Generic findMax
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T element : array) if (element.compareTo(max) > 0) max = element;
        return max;
    }

    // 6. Upper-bounded wildcard method
    public static double calculateTotalArea(List<? extends Shape> shapes) {
        double total = 0;
        for (Shape s : shapes) total += s.getArea();
        return total;
    }

    // 7. Lower-bounded wildcard
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) list.add(i);
    }

    // 8. Animal methods
    public static void addDogsToList(List<? super Dog> list, Dog dog) {
        list.add(dog);
    }
    public static void printAnimalSounds(List<? extends Animal> animals) {
        for (Animal a : animals) a.makeSound();
    }

    public static void main(String[] args) {
        // --- ЗАВДАННЯ 1 ---
        Map<Integer, Student> registry = new HashMap<>();
        registry.put(1, new Student("Olexiy"));
        System.out.println("Task 1: " + registry.get(1));

        // --- ЗАВДАННЯ 2 ---
        List<String> items = Arrays.asList("A", "B", "A", "C");
        System.out.println("Task 2 (Unique): " + getUnique(items));
        System.out.println("Task 2 (Counts): " + countOccurrences(items));

        // --- ЗАВДАННЯ 3 ---
        Box<Integer> box = new Box<>();
        box.put(123);
        System.out.println("Task 3: " + box.take());

        // --- ЗАВДАННЯ 4 ---
        Integer[] nums = {1, 10, 5};
        System.out.println("Task 4: Max is " + findMax(nums));

        // --- ЗАВДАННЯ 5 ---
        Pair<String, Integer> p1 = new Pair<>("test", 10);
        Pair<String, Integer> p2 = new Pair<>("test", 10);
        System.out.println("Task 5: Same? " + Pair.compare(p1, p2));

        // --- ЗАВДАННЯ 6 ---
        List<Shape> shapes = Arrays.asList(new Circle(5), new Rectangle(2, 4));
        System.out.println("Task 6: Total Area = " + calculateTotalArea(shapes));

        // --- ЗАВДАННЯ 7 ---
        List<Number> numList = new ArrayList<>();
        addNumbers(numList);
        System.out.println("Task 7: " + numList);

        // --- ЗАВДАННЯ 8 ---
        System.out.println("Task 8 (Animal Shelter):");
        List<Animal> shelter = new ArrayList<>();
        addDogsToList(shelter, new Dog());
        addDogsToList(shelter, new Labrador());
        shelter.add(new Cat());
        printAnimalSounds(shelter);
    }
}