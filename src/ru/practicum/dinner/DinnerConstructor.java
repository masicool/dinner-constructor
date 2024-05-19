package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishesList;

    DinnerConstructor() {
        dishesList = new HashMap<>();
    }

    /**
     * Добавление типа блюда и наименования блюда
     *
     * @param dishType тип блюда
     * @param dishName наименование блюда
     */
    public void addDish(String dishType, String dishName) {
        ArrayList<String> namesDish;

        // проверим, если уже такой тип блюда
        if (dishesList.containsKey(dishType)) {
            namesDish = dishesList.get(dishType); // такой тип есть, получим список наименований блюд
        } else { // типа блюда нет, значит для него нет и списка наименований блюд, создадим пустой список
            namesDish = new ArrayList<>();
        }

        namesDish.add(dishName); // добавим наименование блюда для заданного типа блюда
        dishesList.put(dishType, namesDish);
    }

    /**
     * Проверка наличия типа блюда и его наименования
     *
     * @param dishType тип блюда
     * @param dishName наименование блюда
     * @return true - есть, false - нет
     */
    public boolean checkDishNameInDishType(String dishType, String dishName) {
        return dishesList.containsKey(dishType) && dishesList.get(dishType).contains(dishName);
    }

    /**
     * Получение списка всех типов и наименований блюд в виде строк,
     * каждый тип блюда на новой строке (для вывода на печать)
     *
     * @return - возврат списка в виде строки
     */
    public String getAllDishesToStr() {
        StringBuilder strToReturn = new StringBuilder();
        for (String dishType : dishesList.keySet()) {
            strToReturn.append("\n").append(dishType).append(" = ");
            strToReturn.append(dishesList.get(dishType).toString());
        }
        return strToReturn.toString();
    }

    /**
     * Получение наименований блюд по типу блюда
     *
     * @param dishType тип блюда
     * @return список наименований блюд
     */
    public ArrayList<String> getNamesOfTypeDish(String dishType) {
        return dishesList.get(dishType);
    }
}
