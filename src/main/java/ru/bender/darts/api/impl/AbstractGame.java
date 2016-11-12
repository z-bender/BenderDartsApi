package ru.bender.darts.api.impl;

import ru.bender.darts.api.exceptions.UnrealPointsException;
import ru.bender.darts.api.helper.Helper;
import ru.bender.darts.api.interfaces.Game;
import ru.bender.darts.api.interfaces.PlayerInGame;
import ru.bender.darts.api.interfaces.PlayersInGameList;
import ru.bender.darts.api.interfaces.LastShotsCounter;

/**
 * Created by bender on 27.09.2016.
 */
public abstract class AbstractGame implements Game{

    //------------------- Constants ------------------------------//

    public static final short DEFAULT_POINTS_TO_END = 300;

    public static final short DEFAULT_DARTS_COUNT = 3;

    //------------------- Constructors ---------------------------//

    public AbstractGame(short pointsToEnd) {
        this.pointsToEnd = pointsToEnd;
        this.dartsCount = DEFAULT_DARTS_COUNT;
        playersList = new PlayersInGameListImpl();
    }

    public AbstractGame() {
        this(DEFAULT_POINTS_TO_END);
    }


    //------------------- Fields ---------------------------------//

    // Список игроков
    public PlayersInGameList playersList;
    // Количество дротиков
    protected final short dartsCount;
    // Количество очков для победы
    protected final short pointsToEnd;

    //-------------------- Open methods ---------------------------//

    //TODO
    @Override
    public void play(LastShotsCounter lastShotsCounter) {

        playersList.resetListToNewGame();
        while (true) {
            PlayerInGame player = playersList.getNextPlayerForStep();
            if (player == null) {
                break;
            }
            try {
                this.step(player, Helper.getUserInputShort("Бросает " + player.getPlayerName() + ". Введи результат: "), lastShotsCounter);
            } catch (UnrealPointsException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Выполнить ход игрока
     *
     * @param player - игрок
     * @param points - количество выбитых очков
     * @param lastShotsCounter - Интерфейс для UI-элемента, в котором будет вводится количество бросков игрока
     * @return - игрок закончил игру?
     */
    @Override
    public abstract boolean step(PlayerInGame player, short points, LastShotsCounter lastShotsCounter) throws UnrealPointsException;


    //-------------------- Getters/Setters ------------------------//


    @Override
    public void setPlayersList(PlayersInGameList playersList) {
        this.playersList = playersList;
    }

    @Override
    public PlayersInGameList getPlayersList() {
        return playersList;
    }

    @Override
    public short getDartsCount() {
        return dartsCount;
    }

    @Override
    public short getPointsToEnd() {
        return pointsToEnd;
    }
}
