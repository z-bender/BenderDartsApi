package ru.bender.darts.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.bender.darts.api.aspects.LoggerAspect;
import ru.bender.darts.api.impl.HomerGame;
import ru.bender.darts.api.impl.PlayerImpl;
import ru.bender.darts.api.impl.PlayerInGameImpl;
import ru.bender.darts.api.interfaces.Game;
import ru.bender.darts.api.interfaces.Player;
import ru.bender.darts.api.interfaces.PlayerInGame;

@Configuration
@ComponentScan(basePackages = "ru.bender.darts.api.impl")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Game homerGame() {
        return new HomerGame();
    }

    @Bean
    public Player player1() {
        return new PlayerImpl("Вася");
    }

    @Bean
    public PlayerInGame player1InGame(){
        return new PlayerInGameImpl(player1(), homerGame().getPointsToEnd());
    }

    @Bean
    public Player player2() {
        return new PlayerImpl("Вася");
    }

    @Bean
    public PlayerInGame player2InGame(){
        return new PlayerInGameImpl(player2(), homerGame().getPointsToEnd());
    }

    @Bean
    public LoggerAspect loggerAspect() {
        return new LoggerAspect();
    }
}
