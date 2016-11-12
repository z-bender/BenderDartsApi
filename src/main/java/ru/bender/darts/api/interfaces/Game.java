package ru.bender.darts.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bender.darts.api.exceptions.UnrealPointsException;

/**
 * Created by bender on 09.11.16.
 */
public interface Game {

    void play(LastShotsCounter lastShotsCounter);

    boolean step(PlayerInGame player, short points, LastShotsCounter lastShotsCounter) throws UnrealPointsException;

    @Autowired
    void setPlayersList(PlayersInGameList playersList);

    PlayersInGameList getPlayersList();

    short getDartsCount();

    short getPointsToEnd();

}
