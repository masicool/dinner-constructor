package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Collections;

public class DishCombo {
    private final ArrayList<String[]> listDishCombo; // список комбинаций блюд
    private int totalSizeListDishCombo; // количество всевозможных комбинаций блюд
    private int nextNumberCombo; // номер следующего набора

    /**
     * В конструкторе создаем список всех возможных комбинации блюд
     *
     * @param dishNamesOfTypes списки с названиями блюд
     */
    DishCombo(ArrayList<ArrayList<String>> dishNamesOfTypes) {
        // определим количество всех комбинаций
        totalSizeListDishCombo = 1;
        for (ArrayList<String> dishNames : dishNamesOfTypes) {
            totalSizeListDishCombo *= dishNames.size();
        }

        // создадим пустой список всех комбинаций блюд нужного размера
        int numberTypesOfDish = dishNamesOfTypes.size(); // кол-во типов блюд выведем в отельную переменную
        listDishCombo = new ArrayList<>();
        for (int i = 0; i < totalSizeListDishCombo; i++) {
            listDishCombo.add(new String[numberTypesOfDish]);
        }

        // заполним список всеми комбинациями блюд (нативный способ, без рекурсий и методов коллекций)
        int indexCol = 0; // номер колонки списка комбинаций блюд
        int countRepeatItem = totalSizeListDishCombo; // количество повторов наименований блюд
        for (ArrayList<String> dishNames : dishNamesOfTypes) { // проходим по каждому списку наименований блюд
            countRepeatItem = countRepeatItem / dishNames.size(); // с каждой колонкой, кол-во повторов уменьшается
            int indexRow = 0; // номер строки в списке комбинаций блюд
            while (indexRow < totalSizeListDishCombo) { // каждый раз проходим по всей длине списка комбинаций
                for (String dishName : dishNames) {
                    for (int i = 0; i < countRepeatItem; i++) { // повторяем наименование нужно кол-во раз
                        listDishCombo.get(indexRow++)[indexCol] = dishName;
                    }
                }
            }
            indexCol++;
        }
        nextNumberCombo = 0;
    }

    /**
     * Перемешивание списка комбинаций (наборов) блюд в произвольном порядке
     */
    public void shuffle() {
        Collections.shuffle(listDishCombo);
    }

    /**
     * Получение количества всех комбинаций (наборов) блюд
     *
     * @return количество всех комбинаций блюд
     */
    public int getNumberOfCombination() {
        return totalSizeListDishCombo;
    }

    public String[] getNextCombo() {
        if (nextNumberCombo >= totalSizeListDishCombo) {
            nextNumberCombo = 0;
        }
        return listDishCombo.get(nextNumberCombo++);
    }
}
