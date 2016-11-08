package ru.bender.darts.api;

import java.util.Scanner;

/**
 * Created by user on 30.09.2016.
 */
class Helper {

    /**
     * Получить введенное пользователем число
     * TODO: I for console tests. Delete me after.
     *
     * @param output - выводимая строка
     * @return введенное число
     */
    public static short getUserInputShort(String output) {
        Scanner reader = new Scanner(System.in);
        System.out.println(output);
        return (short)reader.nextInt();
    }
}
