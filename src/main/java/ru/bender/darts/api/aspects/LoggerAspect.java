package ru.bender.darts.api.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

    @AfterReturning("execution(* ru.bender.darts.api.impl.PlayerInGameImpl.setPointsToEnd(..)) && " +
            //"target(ru.bender.darts.api.interfaces.PlayerInGame) &&" +
            "args(pointsToEnd)")
    public void logPlayerPointsToEnd(short pointsToEnd) {
        System.out.println("Игроку осталось выбить очков: " + pointsToEnd);
    }
}
