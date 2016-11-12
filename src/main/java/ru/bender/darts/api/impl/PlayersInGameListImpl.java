package ru.bender.darts.api.impl;

import ru.bender.darts.api.exceptions.DartsApiRuntimeException;
import ru.bender.darts.api.interfaces.PlayerInGame;
import ru.bender.darts.api.interfaces.PlayersInGameList;

import java.util.*;

/**
 * Created by bender on 28.09.2016.
 */
public class PlayersInGameListImpl implements PlayersInGameList {

    public PlayersInGameListImpl(List<PlayerInGame> players) {
        this.players = (ArrayList<PlayerInGame>) players;
        this.queueOfPlayersForStep = new PriorityQueue<>();
        resetListToNewGame();
    }

    public PlayersInGameListImpl() {
        this(new ArrayList<>());
    }



    //----------------------- fields ------------------------------------//

    // Список игроков
    private ArrayList<PlayerInGame> players;
    // Очередь для хода TODO: удалить после реализации UI
    private PriorityQueue<PlayerInGame> queueOfPlayersForStep;


    //--------------------- public methods ------------------------------//
    //-------------------------------------------------------------------//

    /**
     * Добавить игрока
     *
     * @param player - игрок
     */
    @Override
    public void addPlayer(PlayerInGame player) {
        players.add(player);
        refreshPositions();
        // TODO: добавить в очередь?
    }

    /**
     * Сбросить результаты игроков и выдать рандомные номера
     */
    @Override
    public void resetListToNewGame(){
        resetPlayersResult();
        shufflePlayers();
        addAllPlayersToQueue();
    }

    /**
     * Сбросить результаты игроков
     */
    private void resetPlayersResult() {
        players.forEach(player -> player.resetResults());
    }

    /**
     * Задаем рандомные номера для игроков
     */
    private void shufflePlayers(){
        Collections.shuffle(players);
        short number = 0;
        for (PlayerInGame player : players) {
            player.setNumber(number++);
        }
    }

    /**
     * Добавить всех игроков в очередь для хода
     */
    //TODO при UI не нужно. Удалить или вынести из api
    private void addAllPlayersToQueue() {
        queueOfPlayersForStep = new PriorityQueue<>();
        players.forEach(player -> queueOfPlayersForStep.offer(player));
    }


    /*TODO - хранить прошлые состояния players - посмотреть System.arraycopy
    * копируем players после step и храним его в очереди
    * */
    @Override
    public void rollback(){}

    /**
     * Обновляем места игроков, по их результатам
     */
    @Override
    public void refreshPositions() {
        Collections.sort(players);
        for (PlayerInGame player : players) {
            player.updatePosition(players);
        }
    }

    /**
     * Получить следующего игрока для хода
     *
     * @return
     */
    // TODO: для UI лишнее
    @Override
    public PlayerInGame getNextPlayerForStep() {
        PlayerInGame nextPlayer = queueOfPlayersForStep.poll();
        if (!nextPlayer.isEnd()) {
            queueOfPlayersForStep.offer(nextPlayer);
        }
        return nextPlayer;
    }

    //-------------------- Getters/Setters ------------------------//


    private Comparator<PlayerInGame> getComparator(CompareBy compareBy){
        Comparator<PlayerInGame> comparator = null;
        switch (compareBy) {
            case Number: comparator = (player1, player2) -> player1.getNumber() < player2.getNumber() ? 1 : -1;
        }
        if (comparator == null) {
            throw new DartsApiRuntimeException("Needs add comparator for " + compareBy.toString());
        }
        return comparator;
    }

    /**
     * Возвращает копию списка игроков отсортированных по номеру в игре
     *
     * @return
     */
    @Override
    public ArrayList<PlayerInGame> getPlayersClone() {
        return (ArrayList<PlayerInGame>) players.clone();
    }

    @Override
    public ArrayList<PlayerInGame> getPlayersClone(CompareBy compareBy){
        ArrayList<PlayerInGame> playersClone = getPlayersClone();
        playersClone.sort(getComparator(compareBy));
        return playersClone;
    }

    @Override
    public ArrayList<PlayerInGame> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(ArrayList<PlayerInGame> players) {
        this.players = players;
    }
}
