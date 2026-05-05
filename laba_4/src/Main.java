import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream; // Цей імпорт виправляє помилку "Stream"

public class Main {

    // Допоміжні класи
    static class Employee {
        String name;
        double salary;
        Employee(String name, double salary) { this.name = name; this.salary = salary; }
        @Override
        public String toString() { return name + " (" + salary + ")"; }
    }

    static class Person {
        String name;
        List<Person> friends;
        Person(String name, List<Person> friends) { this.name = name; this.friends = friends; }
    }

    static class Transaction {
        double amount;
        String category;
        Transaction(double amount, String category) { this.amount = amount; this.category = category; }
        public String getCategory() { return category; }
        public double getAmount() { return amount; }
    }

    static class Product {
        String name;
        double price;
        Product(String name, double price) { this.name = name; this.price = price; }
        public String getName() { return name; }
        public double getPrice() { return price; }
    }

    public static void main(String[] args) {
        // --- Task 1 ---
        System.out.println("--- Task 1 ---");
        List<String> strings = Arrays.asList("Apple", "X-Scanner", "X-Ray", "X-Files-Extra", "Box");
        String res1 = strings.stream()
                .filter(s -> s.startsWith("X") && s.length() > 5)
                .findFirst()
                .orElse("Default");
        System.out.println(res1);

        // --- Task 2 ---
        System.out.println("\n--- Task 2 ---");
        List<Optional<Integer>> optionals = Arrays.asList(Optional.of(1), Optional.empty(), Optional.of(10));
        List<Integer> res2 = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(res2);

        // --- Task 3 ---
        System.out.println("\n--- Task 3 ---");
        List<String> names = Arrays.asList("Anna", "Oleksandr", "Ivan", "Maximilian");
        Optional<String> res3 = names.stream()
                .max(Comparator.comparingInt(String::length));
        res3.ifPresent(System.out::println);

        // --- Task 4 ---
        System.out.println("\n--- Task 4 ---");
        List<Employee> employees = Arrays.asList(
                new Employee("Ivan", 2500), new Employee("Petro", 2800),
                new Employee("Anna", 4000), new Employee("Maria", 4500),
                new Employee("Oleg", 6000), new Employee("Igor", 7000)
        );
        Map<String, Optional<Employee>> res4 = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.salary < 3000 ? "< 3000" : e.salary <= 5000 ? "3000-5000" : "> 5000",
                        Collectors.maxBy(Comparator.comparingDouble(e -> e.salary))
                ));
        res4.forEach((k, v) -> System.out.println(k + ": " + v.orElse(null)));

        // --- Task 5 ---
        System.out.println("\n--- Task 5 ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> res5 = numbers.stream()
                .filter(n -> n % 2 != 0)
                .reduce((a, b) -> a * b);
        res5.ifPresent(System.out::println);


        // --- Task 6 ---
        System.out.println("\n--- Task 6 ---");
        Person p1 = new Person("Oleg", Collections.emptyList());
        Person p2 = new Person("Anna", Collections.emptyList());
        Person mainP = new Person("Me", Arrays.asList(p1, p2));

        List<String> res6 = Stream.of(mainP)
                .filter(p -> p.friends != null)
                .flatMap(p -> p.friends.stream())
                .map(f -> f.name.toUpperCase())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(res6);

        // --- Task 7 ---
        System.out.println("\n--- Task 7 ---");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(100, "Food"), new Transaction(200, "Tech"), new Transaction(50, "Food")
        );
        Map<String, Double> res7 = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
        System.out.println(res7);

        // --- Task 8 ---
        System.out.println("\n--- Task 8 ---");
        List<Product> products = Arrays.asList(
                new Product("Phone", 500), new Product("Laptop", 1200), new Product("Watch", 300), new Product("PC", 1500)
        );
        Optional<String> res8 = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .skip(1)
                .findFirst()
                .map(Product::getName);
        res8.ifPresent(System.out::println);

        // --- Task 9 ---
        System.out.println("\n--- Task 9 ---");
        Map<Integer, Optional<String>> productMap = new HashMap<>();
        productMap.put(1, Optional.of("Laptop"));
        productMap.put(2, Optional.empty());
        productMap.put(3, Optional.of("Mouse"));
        List<String> res9 = productMap.values().stream()
                .flatMap(Optional::stream)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(res9);

        // --- Task 10 ---
        System.out.println("\n--- Task 10 ---");
        Map<String, List<Double>> cityTemps = new HashMap<>();
        cityTemps.put("Kyiv", Arrays.asList(10.0, 15.0, 12.0));
        cityTemps.put("Lviv", Arrays.asList(18.0, 20.0, 22.0));
        cityTemps.put("Odesa", Arrays.asList(25.0, 28.0));

        Optional<String> res10 = cityTemps.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0)))
                .map(Map.Entry::getKey);
        res10.ifPresent(city -> System.out.println("City with max avg temp: " + city));
    }
}