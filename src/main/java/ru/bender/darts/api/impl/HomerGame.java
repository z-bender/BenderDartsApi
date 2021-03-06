package ru.bender.darts.api.impl;

import org.springframework.stereotype.Component;
import ru.bender.darts.api.exceptions.UnrealPointsException;
import ru.bender.darts.api.interfaces.LastShotsCounter;
import ru.bender.darts.api.interfaces.PlayerInGame;

@Component
public class HomerGame extends AbstractGame {

    //------------------- Constants ------------------------------//

    public static final short DEFAULT_POINTS_TO_END = 300;


    //------------------- Constructors ---------------------------//

    public HomerGame(short pointsToEnd) {
        super(pointsToEnd);
    }

    public HomerGame() {
        this(DEFAULT_POINTS_TO_END);
    }


    //------------------- open methods ---------------------------//

    /**
     * Игрок выполняет ход
     *
     * @param player       - игрок
     * @param points       - количество выбитых очков
     * @param lastShotsCounter - Интерфейс для UI-элемента, в котором будет вводится количество бросков игрока
     * @return - игрок закончил игру?
     */
    @Override
    public boolean step(PlayerInGame player, short points, LastShotsCounter lastShotsCounter) throws UnrealPointsException {
        if (!checkPoints(points)) {
            throw new UnrealPointsException("Ошибка хода! " + points + " очков нельзя набрать за один ход");
        }
        if (player.isEnd())
            return player.isEnd();
        short currentPointsToEnd = player.getPointsToEnd();
        currentPointsToEnd = (short) Math.abs(currentPointsToEnd - points);
        //TODO: isCompletedWithDoubling - добавить закрытие удвоением
        player.setPointsToEnd(currentPointsToEnd);
        //TODO: если isEnd, то запросить у пользователя количество бросков и переписать countOfShots (в этом случае можно отказаться от перегруза метода и параметра countOfShots
        short shotsCount = (player.isEnd()) ? lastShotsCounter.getLastShotsCount() : dartsCount;
        player.addShots(shotsCount);
        //TODO: подумать может ли использоваться в другом месте. Если да, то может быть стоит перенести в сеттер игрока? Наверное не стоит... ещё будет вызываться в сбросе очков для новой игры (наверное не надо там вызывать)
        playersList.refreshPositions();
        return player.isEnd();
    }

    /**
     * TODO: усложнить логику (добавить количество бросков?)
     * проверка количества набранных очков за ход на корректность.
     *
     * @param points - количество очков
     * @return
     */
    private boolean checkPoints(short points) {
        return !(points > 180);
    }
}
