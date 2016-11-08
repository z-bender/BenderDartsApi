package ru.bender.darts.api;

/**
 * Created by bender on 27.09.2016.
 */
public abstract class Game {

    //------------------- Constants ------------------------------//

    public static final short DEFAULT_DARTS_COUNT = 3;

    //------------------- Constructors ---------------------------//

    public Game(short pointsToEnd) {
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
    public abstract boolean step(PlayerInGame player, short points, ShotsCountUI shotsCountUI) throws UnrealPointsException;


    //-------------------- Getters/Setters ------------------------//


    short getPointsToEnd() {
        return pointsToEnd;
    }
}
