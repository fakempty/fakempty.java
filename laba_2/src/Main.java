import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання 1 ===");
        System.out.print("Введіть дату у форматі ДД.ММ.РРРР: ");
        String dateInput = scanner.nextLine();
        task1ConvertDate(dateInput);

        System.out.println("\n=== Завдання 2 ===");
        task2OrderQueue();

        scanner.close();
    }


    public static void task1ConvertDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            LocalDate date = LocalDate.parse(input, formatter);
            System.out.println("Успіх! Дата розпізнана: " + date);
        } catch (DateTimeParseException e) {
            System.err.println("Помилка: Рядок '" + input + "' має недійсний формат дати.");
            System.err.println("Використовуйте формат ДД.ММ.РРРР (наприклад, 15.05.2024)");
        }
    }

    public static void task2OrderQueue() {
        OrderManager manager = new OrderManager();

        manager.addOrder("Замовлення #1: Смартфон");
        manager.addOrder("Замовлення #2: Навушники");

        try {
            manager.processOrder();
            manager.processOrder();

            System.out.println("Спроба обробити замовлення з порожньої черги...");
            manager.processOrder();

        } catch (NoSuchElementException e) {
            System.out.println("Виняток перехоплено: " + e.getMessage());
        }
    }
}


class OrderManager {
    private Queue<String> orders = new LinkedList<>();

    public void addOrder(String order) {
        orders.add(order);
        System.out.println("Додано: " + order);
    }

    public void processOrder() {
        if (orders.isEmpty()) {
            throw new NoSuchElementException("Неможливо обробити замовлення: черга порожня!");
        }
        String removed = orders.poll();
        System.out.println("Оброблено та видалено: " + removed);
    }
}