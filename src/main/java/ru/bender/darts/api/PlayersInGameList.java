package ru.bender.darts.api;

import java.util.*;

/**
 * Created by bender on 28.09.2016.
 */
public class PlayersInGameList {

    public PlayersInGameList(List<PlayerInGame> players) {
        this.players = (ArrayList<PlayerInGame>) players;
        this.queueOfPlayersForStep = new PriorityQueue<>();
        resetListToNewGame();
    }

    public PlayersInGameList() {
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
    public void addPlayer(PlayerInGame player) {
        players.add(player);
        refreshPositions();
        // TODO: добавить в очередь?
    }

    /**
     * Сбросить результаты игроков и выдать рандомные номера
     */
    public void resetListToNewGame(){
        resetPlayersResult();
        shufflePlayers();
        addAllPlayersToQueue();
    }

    /*TODO - хранить прошлые состояния players - посмотреть System.arraycopy
    * копируем players после step и храним его в очереди
    * */
    public void rollback(){}

    /**
     * Обновляем места игроков, по их результатам
     */
    public void refreshPositions() {
        Collections.sort(players);
        for (PlayerInGame player : players) {
            player.setPosition(players);
        }
    }

    /**
     * Получить следующего игрока для хода
     *
     * @return
     */
    public PlayerInGame getNextPlayerForStep() {
        PlayerInGame nextPlayer = queueOfPlayersForStep.poll();
        if (!nextPlayer.isEnd()) {
            queueOfPlayersForStep.offer(nextPlayer);
        }
        return nextPlayer;
    }


    //-------------------- private methods ---------------------------//

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
     * Сбросить результаты игроков
     */
    private void resetPlayersResult() {
        for (PlayerInGame player : players) {
            player.resetResults();
        }
    }

    /**
     * Добавить всех игроков в очередь для хода
     */
    private void addAllPlayersToQueue() {
        queueOfPlayersForStep = new PriorityQueue<>();
        for (PlayerInGame player : players) {
            queueOfPlayersForStep.offer(player);
        }
    }

    //-------------------- Getters/Setters ------------------------//


    public enum CompareBy {Number}

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
    public ArrayList<PlayerInGame> getPlayers() {
        return (ArrayList<PlayerInGame>) players.clone();
    }

    public ArrayList<PlayerInGame> getPlayers(CompareBy compareBy){
        ArrayList<PlayerInGame> playersClone = getPlayers();
        playersClone.sort(getComparator(compareBy));
        return playersClone;
    }



}
