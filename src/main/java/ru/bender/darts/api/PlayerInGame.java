package ru.bender.darts.api;

import java.util.ArrayList;

/**
 * Created by bender on 28.09.2016.
 */
public class PlayerInGame extends Player implements Comparable<PlayerInGame> {

    public PlayerInGame(String name, Game game) {
        super(name);
        this.game = game;
        resetResults();
    }

    // очков до финиша
    private short pointsToEnd;
    // количество бросков
    private short shotsCount;
    // место игрока
    private short position;
    // порядковый номер игрока
    private short number;
    // завершил удвоением/утроением
    private boolean isCompletedWithDoubling;
    // ссылка на игру
    private Game game;

    //------------------- open methods ---------------------------//

    /**
     * Закончил игру?
     *
     * @return да/нет
     */
    public boolean isEnd() {
        return pointsToEnd == 0;
    }

    /**
     * Сбрасываем результат игрока, для новой игры
     */
    void resetResults() {
        setPointsToEnd(game.getPointsToEnd());
        shotsCount = 0;
        isCompletedWithDoubling = false;
    }


    /**
     * Правила сравнения (сортировки) результатов двух игроков.
     * Сравниваются:
     * - Количество очков до завершения
     * - Количество бросков
     * - Закрытие удвоением/утроением
     * - Порядковый номер
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(PlayerInGame o) {
        if (this.pointsToEnd < o.pointsToEnd)
            return 1;
        if (this.pointsToEnd > o.pointsToEnd)
            return -1;
        if (this.shotsCount < o.shotsCount)
            return 1;
        if (this.shotsCount > o.shotsCount)
            return -1;
        if(this.isCompletedWithDoubling != o.isCompletedWithDoubling) {
            if (this.isCompletedWithDoubling)
                return 1;
            else
                return -1;
        }
        if(this.number < o.number)
            return 1;
        if(this.number > o.number)
            return -1;
        return 0;
    }

    //-------------------- Getters/Setters ------------------------//


    short getNumber() {
        return number;
    }

    public short getPointsToEnd() {
        return pointsToEnd;
    }

    // TODO: проверять из какого метода вызван. Если !ru.bender.darts.api.Game.step() - тогда Exception. Подумать где ещё может вызываться (например, reset).
    // TODO: продумать подобную проверку для других методов
    void setPointsToEnd(short pointsToEnd) {
        this.pointsToEnd = pointsToEnd;
    }

    /**
     * Прибавить количество бросков
     *
     * @param shotsCountToAdd - сделанное количество бросков за ход
     */
    void addShotsCount(short shotsCountToAdd) {
        this.shotsCount += shotsCountToAdd;
    }

    void setNumber(short number) {
        this.number = number;
    }

    void setPosition(ArrayList<PlayerInGame> players) {
        this.position = (short)players.indexOf(this);
    }

    //-------------------- private methods ---------------------------//

}
