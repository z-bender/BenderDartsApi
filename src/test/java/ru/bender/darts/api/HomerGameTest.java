package ru.bender.darts.api;

import ru.bender.darts.api.impl.HomerGame;
import ru.bender.darts.api.impl.PlayerImpl;
import ru.bender.darts.api.impl.PlayerInGameImpl;
import ru.bender.darts.api.interfaces.ShotsCountUI;

/**
 * Created by user on 29.09.2016.
 */
class HomerGameTest implements ShotsCountUI {
    public static void main(String[] args) {
        HomerGame homerGame = new HomerGame();
        homerGame.playersList.addPlayer(new PlayerInGameImpl(new PlayerImpl("василий"), homerGame.getPointsToEnd()));
        homerGame.playersList.addPlayer(new PlayerInGameImpl(new PlayerImpl("юрий павлович"), homerGame.getPointsToEnd()));
        homerGame.playersList.addPlayer(new PlayerInGameImpl(new PlayerImpl("алёшка"), homerGame.getPointsToEnd()));

        homerGame.play(new HomerGameTest());
    }

    @Override
    public short getShotsCount() {
        return 2;
    }
}
