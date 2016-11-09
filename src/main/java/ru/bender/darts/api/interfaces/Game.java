package ru.bender.darts.api.interfaces;

import ru.bender.darts.api.exceptions.UnrealPointsException;
import ru.bender.darts.api.impl.PlayerInGame;

/**
 * Created by bender on 09.11.16.
 */
public interface Game {

    void play(ShotsCountUI shotsCountUI);

    boolean step(PlayerInGame player, short points, ShotsCountUI shotsCountUI) throws UnrealPointsException;

    short getPointsToEnd();
}
