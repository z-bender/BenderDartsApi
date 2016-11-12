package ru.bender.darts.api.interfaces;

import ru.bender.darts.api.exceptions.UnrealPointsException;

/**
 * Created by bender on 09.11.16.
 */
public interface Game {

    void play(LastShotsCounter lastShotsCounter);

    boolean step(PlayerInGame player, short points, LastShotsCounter lastShotsCounter) throws UnrealPointsException;

    void setPlayersList(PlayersInGameList playersList);

    PlayersInGameList getPlayersList();

    void setDartsCount(short dartsCount);

    short getDartsCount();

    void setPointsToEnd(short pointsToEnd);

    short getPointsToEnd();

}
