package ru.bender.darts.api;

/**
 * Created by user on 29.09.2016.
 */
class HomerGameTest implements ShotsCountUI{
    public static void main(String[] args) {
        HomerGame homerGame = new HomerGame();
        homerGame.playersList.addPlayer(new PlayerInGame(new Player("василий"), homerGame));
        homerGame.playersList.addPlayer(new PlayerInGame(new Player("юрий павлович"), homerGame));
        homerGame.playersList.addPlayer(new PlayerInGame(new Player("алёшка"), homerGame));

        homerGame.play(new HomerGameTest());
    }

    @Override
    public short getShotsCount() {
        return 2;
    }
}
