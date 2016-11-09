package ru.bender.darts.api.impl;

import ru.bender.darts.api.helper.Helper;
import ru.bender.darts.api.interfaces.Game;
import ru.bender.darts.api.interfaces.ShotsCountUI;
import ru.bender.darts.api.exceptions.UnrealPointsException;

/**
 * Created by bender on 27.09.2016.
 */
public abstract class AbstractGame implements Game{

    //------------------- Constants ------------------------------//

    public static final short DEFAULT_DARTS_COUNT = 3;

    //------------------- Constructors ---------------------------//

    public AbstractGame(short pointsToEnd) {
        this.pointsToEnd = pointsToEnd;
        this.dartsCount = DEFAULT_DARTS_COUNT;
        playersList = new PlayersInGameList();
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
    public void play(ShotsCountUI shotsCountUI) {

        playersList.resetListToNewGame();
        while (true) {
            PlayerInGame player = playersList.getNextPlayerForStep();
            if (player == null) {
                break;
            }
            try {
                this.step(player, Helper.getUserInputShort("Бросает " + player.getName() + ". Введи результат: "), shotsCountUI);
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
     * @param shotsCountUI - Интерфейс для UI-элемента, в котором будет вводится количество бросков игрока
     * @return - игрок закончил игру?
     */
    @Override
    public abstract boolean step(PlayerInGame player, short points, ShotsCountUI shotsCountUI) throws UnrealPointsException;


    //-------------------- Getters/Setters ------------------------//


    @Override
    public short getPointsToEnd() {
        return pointsToEnd;
    }
}
