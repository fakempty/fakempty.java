import java.util.*;

// --- 1. КЛАС СТУДЕНТ (До завдання №1) ---
class Student {
    private String name;
    public Student(String name) { this.name = name; }
    @Override
    public String toString() { return "Студент: " + name; }
}

// --- 3. GENERIC КЛАС КОРОБКА (Завдання №3) ---
class Box<T> {
    private T item;
    public void put(T item) { this.item = item; }
    public T take() { return item; }
}

// --- 5. GENERIC ПАРА (Завдання №5) ---
class Pair<K, V> {
    K first;
    V second;
    public Pair(K first, V second) { this.first = first; this.second = second; }

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.first.equals(p2.first) && p1.second.equals(p2.second);
    }
}

// --- 6. ФІГУРИ (Завдання №6) ---
abstract class Shape { abstract double getArea(); }
class Circle extends Shape {
    double r;
    Circle(double r) { this.r = r; }
    double getArea() { return Math.PI * r * r; }
}

// --- 8. ТВАРИНИ (Завдання №8) ---
abstract class Animal { abstract void makeSound(); }
class Dog extends Animal { void makeSound() { System.out.println("Гав!"); } }
class Cat extends Animal { void makeSound() { System.out.println("Мяу!"); } }
class Labrador extends Dog { void makeSound() { System.out.println("Лабрадор каже: Гав-гав!"); } }

// --- ГОЛОВНИЙ КЛАС ДЛЯ ПЕРЕВІРКИ ---
public class Main {

    // Завдання 2: Унікальні елементи
    public static <T> Collection<T> getUnique(List<T> list) {
        return new HashSet<>(list);
    }

    // Завдання 2: Підрахунок входжень
    public static <T> Map<T, Integer> countOccurrences(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        for (T obj : list) map.put(obj, map.getOrDefault(obj, 0) + 1);
        return map;
    }

    // Завдання 4: Пошук максимуму (Generic Method)
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T element : array) if (element.compareTo(max) > 0) max = element;
        return max;
    }

    // Завдання 6: Upper-bounded wildcard (List<? extends Shape>)
    public static double calculateTotalArea(List<? extends Shape> shapes) {
        double total = 0;
        for (Shape s : shapes) total += s.getArea();
        return total;
    }

    // Завдання 7: Lower-bounded wildcard (List<? super Integer>)
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) list.add(i);
    }

    // Завдання 8: Робота з притулком
    public static void addDogsToList(List<? super Dog> list, Dog dog) {
        list.add(dog);
    }

    public static void main(String[] args) {
        System.out.println("--- 1. Реєстр студентів ---");
        Map<Integer, Student> registry = new HashMap<>();
        registry.put(101, new Student("Олексій"));
        registry.put(102, new Student("Марія"));
        System.out.println("Пошук 101: " + registry.get(101));

        System.out.println("\n--- 2. Унікальність та підрахунок ---");
        List<String> items = Arrays.asList("Apple", "Banana", "Apple", "Orange");
        System.out.println("Унікальні: " + getUnique(items));
        System.out.println("Кількість: " + countOccurrences(items));

        System.out.println("\n--- 3. Generic Box ---");
        Box<String> stringBox = new Box<>();
        stringBox.put("Секретний документ");
        System.out.println("В коробці: " + stringBox.take());

        System.out.println("\n--- 4. Пошук Максимуму ---");
        Integer[] intArray = {1, 45, 2, 10};
        String[] strArray = {"Z", "A", "M"};
        System.out.println("Max Int: " + findMax(intArray));
        System.out.println("Max String: " + findMax(strArray));

        System.out.println("\n--- 5. Порівняння Pair ---");
        Pair<String, Integer> p1 = new Pair<>("ID", 1);
        Pair<String, Integer> p2 = new Pair<>("ID", 1);
        System.out.println("Пари однакові: " + Pair.compare(p1, p2));

        System.out.println("\n--- 6. Площа (Wildcards) ---");
        List<Circle> circles = Arrays.asList(new Circle(2), new Circle(3));
        System.out.println("Загальна площа: " + calculateTotalArea(circles));

        System.out.println("\n--- 8. Притулок тварин ---");
        List<Animal> shelter = new ArrayList<>();
        addDogsToList(shelter, new Dog());
        addDogsToList(shelter, new Labrador());
        shelter.add(new Cat());

        for (Animal a : shelter) {
            a.makeSound();
        }
    }
}