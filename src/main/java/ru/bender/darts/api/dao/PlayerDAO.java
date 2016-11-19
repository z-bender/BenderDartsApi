package ru.bender.darts.api.dao;

import ru.bender.darts.api.interfaces.Player;

import java.util.List;

public interface PlayerDAO {

    int insert(Player player);

    void update(Player player);

    Player getPlayer(int ID);

    List<Player> getPlayerList();

}
