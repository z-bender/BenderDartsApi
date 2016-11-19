package ru.bender.darts.api.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bender.darts.api.dao.PlayerDAO;
import ru.bender.darts.api.impl.AbstractGame;
import ru.bender.darts.api.impl.HomerGame;
import ru.bender.darts.api.interfaces.PlayerInGame;

public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //I'm for test
        PlayerDAO playerDAO = (PlayerDAO) context.getBean("playerSQLiteDAO");

        AbstractGame game = (HomerGame) context.getBean("homerGame");
        game.getPlayersList().addPlayer((PlayerInGame) context.getBean("player1InGame"));
        game.getPlayersList().addPlayer((PlayerInGame) context.getBean("player2InGame"));

        game.play((() -> (short)2));
    }
}
