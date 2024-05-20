/**
 * Спринт 3. Финальное задание "Конструктор обеда"
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> addNewDish();
                case "2" -> generateDishCombo();
                case "3" -> {
                    System.out.println("Список всех типов и наименований блюд:");
                    System.out.println(dc.getAllDishesToStr());
                }
                case "4" -> {
                    return;
                }
                default -> System.out.println("Такой команды нет!");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Показать все типы и наименования блюд");
        System.out.println("4 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // проверка правильности ввода наименований
        if (dishType.isBlank() || dishName.isBlank()) {
            System.out.println("Название не должно быть пустым. Блюдо не добавлено!");
            return;
        }

        // проверим, было ли уже это же блюдо с заданным типом
        if (dc.checkDishNameInDishType(dishType, dishName)) {
            System.out.println("Это блюдо с этим типом уже есть!");
            return;
        }

        // добавим новое блюдо
        dc.addDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = 0;
        boolean isCorrectInput = true;
        try {
            numberOfCombos = scanner.nextInt();
        } catch (InputMismatchException e) {
            isCorrectInput = false;
        }
        if (!isCorrectInput || numberOfCombos <= 0) {
            System.out.println("Вы должны ввести положительное число больше нуля!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        if (nextItem.isEmpty()) {
            System.out.println("Вы не ввели ни одного типа блюда!");
            return;
        }

        // сформируем списки с названиями блюд по каждому введенному типу блюда
        ArrayList<ArrayList<String>> dishNamesOfTypes = new ArrayList<>();
        while (!nextItem.isEmpty()) {
            ArrayList<String> namesDish = dc.getNamesOfTypeDish(nextItem);
            if (namesDish != null) {
                dishNamesOfTypes.add(namesDish);
            } else {
                System.out.println("Такого типа блюда нет, введите другой тип.");
            }
            nextItem = scanner.nextLine();
        }

        if (dishNamesOfTypes.isEmpty()) {
            System.out.println("Вы ни чего не ввели!");
            return;
        }

        // создадим экземпляр класса, передав списки с названиями блюд в конструктор
        DishCombo dishCombo = new DishCombo(dishNamesOfTypes);

        // выведем заданное пользователем количество комбинаций (наборов) блюд
        for (int i = 1; i <= numberOfCombos; i++) {
            System.out.print("Комбо " + i + " ");
            System.out.println(Arrays.toString(dishCombo.getNextCombo()));
        }

    }
}
