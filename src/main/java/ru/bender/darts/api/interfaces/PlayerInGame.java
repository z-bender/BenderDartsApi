package ru.bender.darts.api.interfaces;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PlayerInGame extends Comparable<PlayerInGame> {
    boolean isEnd();

    @Override
    int compareTo(PlayerInGame o);

    public void resetResults();

    void addShots(short shotsCountToAdd);

    void updatePosition(List<PlayerInGame> players);

    String getPlayerName();

    short getNumber();

    void setNumber(short number);

    short getPointsToEnd();

    void setPointsToEnd(short pointsToEnd);

    Player getPlayer();

    @Autowired
    void setPlayer(Player player);

    short getShotsCount();

    void setShotsCount(short shotsCount);

    short getPosition();

    void setPosition(short position);

    boolean isCompletedWithDoubling();

    void setCompletedWithDoubling(boolean completedWithDoubling);

    short getInitialPointToEnd();

    void setInitialPointToEnd(short initialPointToEnd);
}
