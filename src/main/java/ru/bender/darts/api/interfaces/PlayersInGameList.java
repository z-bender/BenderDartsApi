package ru.bender.darts.api.interfaces;

import java.util.ArrayList;

public interface PlayersInGameList {
    void addPlayer(PlayerInGame player);

    void resetListToNewGame();

    /*TODO - хранить прошлые состояния players - посмотреть System.arraycopy
        * копируем players после step и храним его в очереди
        * */
    void rollback();

    void refreshPositions();

    // TODO: для UI лишнее
    PlayerInGame getNextPlayerForStep();

    ArrayList<PlayerInGame> getPlayersClone();

    ArrayList<PlayerInGame> getPlayersClone(CompareBy compareBy);

    ArrayList<PlayerInGame> getPlayers();

    void setPlayers(ArrayList<PlayerInGame> players);

    enum CompareBy {Number}
}
