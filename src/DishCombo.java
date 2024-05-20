/**
 * Класс для реализации функционала генерации комбо-наборов блюд
 */

import java.util.ArrayList;
import java.util.Random;

public class DishCombo {
    private final ArrayList<ArrayList<String>> dishNamesOfTypes; // список наименований блюд по типам
    private final Random rnd; // для генерации случайных чисел

    /**
     * В конструкторе создаем объект класса Random и сохраняем локально ссылку на
     * список наименований блюд каждого типа
     *
     * @param dishNamesOfTypes списки с наименованиями блюд
     */
    DishCombo(ArrayList<ArrayList<String>> dishNamesOfTypes) {
        this.dishNamesOfTypes = dishNamesOfTypes;
        rnd = new Random();
    }

    /**
     * Возврат очередной случайной комбинации (набора) блюд
     *
     * @return набор блюд
     */
    public String[] getNextCombo() {
        String[] dishCombo = new String[dishNamesOfTypes.size()]; // массив для хранения набора блюд
        for (int i = 0; i < dishNamesOfTypes.size(); i++) {
            ArrayList<String> dishNamesOfType = dishNamesOfTypes.get(i);
            int randomIndex = rnd.nextInt(dishNamesOfType.size());
            dishCombo[i] = dishNamesOfType.get(randomIndex);
        }

        return dishCombo;
    }
}
