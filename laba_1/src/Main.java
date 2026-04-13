import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Завдання №1 ===");
        task1_6();

        System.out.println("\n=== Завдання №2 ===");
        task2_6(scanner);

        System.out.println("\n=== Завдання №3 ===");
        task3_6();

        System.out.println("\n=== Завдання №4 ===");
        task4_6();

        System.out.println("\n=== Завдання №5 ===");
        task5_6();
    }


    public static void task1_6() {
        byte b = 110; // Максимальне значення byte - 127
        System.out.println("Початкове значення byte: " + b);

        b = (byte) (b + 5);

        System.out.println("Результат після додавання 5: " + b);
    }


    public static void task2_6(Scanner scanner) {
        System.out.print("Введіть оцінку студента: ");
        int grade = scanner.nextInt();

        if (grade > 50) {
            System.out.println("Результат: Здано");
        } else {
            System.out.println("Результат: Відрахований");
        }
    }


    public static void task3_6() {
        int number = 17;
        System.out.println("Аналізуємо число: " + number);


        switch (number % 2) {
            case 0:
                System.out.println("Це ПАРНЕ число.");
                break;
            case 1:
            case -1:
                System.out.println("Це НЕПАРНЕ число.");
                break;
        }
    }


    public static void task4_6() {
        int i = 1;
        System.out.println("Парні числа в діапазоні [1-49]:");
        while (i <= 49) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
            i++;
        }
        System.out.println();
    }


    public static void task5_6() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Масив до сортування: " + Arrays.toString(arr));

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("Масив після Bubble Sort: " + Arrays.toString(arr));
    }
}