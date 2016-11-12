package ru.bender.darts.api.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.bender.darts.api.interfaces.Player;
import ru.bender.darts.api.interfaces.PlayerInGame;

import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class PlayerInGameImpl implements PlayerInGame {

    public PlayerInGameImpl(Player player, short initialPointToEnd) {
        this.player = player;
        this.initialPointToEnd = initialPointToEnd;
        resetResults();
    }

    private Player player;

    // порядковый номер игрока
    private short number;
    // очков до финиша
    private short pointsToEnd;
    // количество бросков
    private short shotsCount;
    // место игрока
    private short position;
    // завершил удвоением/утроением
    private boolean isCompletedWithDoubling;
    //
    private short initialPointToEnd;
    //------------------- open methods ---------------------------//

    /**
     * Закончил игру?
     *
     * @return да/нет
     */
    @Override
    public boolean isEnd() {
        return pointsToEnd == 0;
    }

    /**
     * Сбрасываем результат игрока, для новой игры
     */
    @Override
    public void resetResults() {
        setPointsToEnd(initialPointToEnd);
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
        if (this.pointsToEnd < o.getPointsToEnd())
            return 1;
        if (this.pointsToEnd > o.getPointsToEnd())
            return -1;
        if (this.shotsCount < o.getPointsToEnd())
            return 1;
        if (this.shotsCount > o.getPointsToEnd())
            return -1;
        if(this.isCompletedWithDoubling != o.isCompletedWithDoubling()) {
            if (this.isCompletedWithDoubling)
                return 1;
            else
                return -1;
        }
        if(this.number < o.getNumber())
            return 1;
        if(this.number > o.getNumber())
            return -1;
        return 0;
    }


    @Override
    public void addShots(short shotsCountToAdd) {
        this.shotsCount += shotsCountToAdd;
    }

    @Override
    public void updatePosition(List<PlayerInGame> players) {
        this.position = (short)players.indexOf(this);
    }

    @Override
    public String getPlayerName() {
        return player.getName();
    }

    //-------------------- Getters/Setters ------------------------//

    @Override
    public short getNumber() {
        return number;
    }

    @Override
    public void setNumber(short number) {
        this.number = number;
    }

    @Override
    public short getPointsToEnd() {
        return pointsToEnd;
    }

    @Override
    public void setPointsToEnd(short pointsToEnd) {
        this.pointsToEnd = pointsToEnd;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public short getShotsCount() {
        return shotsCount;
    }

    @Override
    public void setShotsCount(short shotsCount) {
        this.shotsCount = shotsCount;
    }

    @Override
    public short getPosition() {
        return position;
    }

    @Override
    public void setPosition(short position) {
        this.position = position;
    }

    @Override
    public boolean isCompletedWithDoubling() {
        return isCompletedWithDoubling;
    }

    @Override
    public void setCompletedWithDoubling(boolean completedWithDoubling) {
        isCompletedWithDoubling = completedWithDoubling;
    }

    @Override
    public short getInitialPointToEnd() {
        return initialPointToEnd;
    }

    @Override
    public void setInitialPointToEnd(short initialPointToEnd) {
        this.initialPointToEnd = initialPointToEnd;
    }
}
