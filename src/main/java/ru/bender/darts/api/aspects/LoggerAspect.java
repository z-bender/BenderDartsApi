package ru.bender.darts.api.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

/*
    @AfterReturning("execution(* ru.bender.darts.api.interfaces.PlayerInGame.setPointsToEnd(..)) && " +
            //"target(ru.bender.darts.api.interfaces.PlayerInGame) &&" +
            "args(player)")
    public void logPlayerPointsToEnd(short player) {
        System.out.println(player + " - осталось ");
    }
*/

//FIXME: не подхватывается step
//    @AfterReturning("execution(* ru.bender.darts.api.impl.PlayerInGameImpl.setPointsToEnd(..))")
    @After("execution(* *.step(..))")
//    @After("target(ru.bender.darts.api.interfaces.Game)")
    public void logPlayerPointsToEnd() {
        System.out.println("test");
    }
}
