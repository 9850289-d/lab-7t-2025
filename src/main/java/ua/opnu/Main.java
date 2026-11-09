package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 1: Predicate (прості числа) ===");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("Число 7 просте? " + isPrime.test(7));
        System.out.println("Число 10 просте? " + isPrime.test(10));


        System.out.println("\n=== ЗАВДАННЯ 2: Фільтрація масиву студентів ===");
        Student[] students = {
                new Student("Шевченко", "Тарас", "КН-1", new int[]{90, 85, 92}),
                new Student("Франко", "Іван", "КН-1", new int[]{50, 75, 80}), // має борг (50)
                new Student("Українка", "Леся", "КН-2", new int[]{100, 95, 98}),
                new Student("Сковорода", "Григорій", "КН-2", new int[]{45, 55, 60}) // має борги
        };

        // Предикат: студент НЕ МАЄ заборгованостей (всі оцінки >= 60)
        Predicate<Student> hasNoDebts = s -> {
            for (int mark : s.getMarks()) {
                if (mark < 60) return false;
            }
            return true;
        };
        Student[] passedStudents = filterStudents(students, hasNoDebts);
        System.out.println("Студенти без боргів: " + Arrays.toString(passedStudents));


        System.out.println("\n=== ЗАВДАННЯ 3: Фільтрація за двома умовами ===");
        // Другий предикат: студент з групи КН-1
        Predicate<Student> isGroupKN1 = s -> s.getGroup().equals("КН-1");
        // Фільтруємо: без боргів І з групи КН-1
        Student[] kn1Passed = filterTwoConditions(students, hasNoDebts, isGroupKN1);
        System.out.println("Студенти КН-1 без боргів: " + Arrays.toString(kn1Passed));


        System.out.println("\n=== ЗАВДАННЯ 4: Consumer (Прізвище + Ім'я) ===");
        Consumer<Student> printFullName = s -> System.out.println(s.getLastName() + " " + s.getName());
        forEachStudent(students, printFullName);


        System.out.println("\n=== ЗАВДАННЯ 5: Predicate + Consumer ===");
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printSquare = n -> System.out.println("Квадрат числа " + n + " = " + (n * n));
        System.out.println("Виконуємо Consumer (друк квадрата) тільки для парних чисел (Predicate):");
        processIf(numbers, isEven, printSquare);


        System.out.println("\n=== ЗАВДАННЯ 6: Function (2^n) ===");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] powers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            powers[i] = powerOfTwo.apply(numbers[i]);
        }
        System.out.println("Степені двійки від 1 до 10: " + Arrays.toString(powers));


        System.out.println("\n=== ЗАВДАННЯ 7: Function (stringify) ===");
        int[] digits = {0, 3, 5, 1, 9, 2};
        Function<Integer, String> digitToString = n -> {
            String[] words = {"нуль", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять"};
            return (n >= 0 && n <= 9) ? words[n] : "невідомо";
        };
        String[] stringified = stringify(digits, digitToString);
        System.out.println("Вхідний масив: " + Arrays.toString(digits));
        System.out.println("Результат stringify: " + Arrays.toString(stringified));
    }


    // --- Допоміжні методи для роботи з масивами ---

    // Для Завдання 2
    public static Student[] filterStudents(Student[] input, Predicate<Student> p) {
        Student[] result = new Student[input.length];
        int counter = 0;
        for (Student s : input) {
            if (p.test(s)) {
                result[counter++] = s;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    // Для Завдання 3
    public static Student[] filterTwoConditions(Student[] input, Predicate<Student> p1, Predicate<Student> p2) {
        Student[] result = new Student[input.length];
        int counter = 0;
        for (Student s : input) {
            if (p1.test(s) && p2.test(s)) {
                result[counter++] = s;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    // Для Завдання 4
    public static void forEachStudent(Student[] input, Consumer<Student> action) {
        for (Student s : input) {
            action.accept(s);
        }
    }

    // Для Завдання 5
    public static void processIf(int[] input, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i : input) {
            if (p.test(i)) {
                c.accept(i);
            }
        }
    }

    // Для Завдання 7
    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }
}