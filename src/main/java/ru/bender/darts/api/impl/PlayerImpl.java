package ru.bender.darts.api.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.bender.darts.api.interfaces.Player;

@Component
@Lazy
@Scope("prototype")
public class PlayerImpl implements Player {

    private int ID;
    // имя
    private String name;
    // лучший результат за ход TODO
    private short bestResultOfStep;

    // лучший результат количества бросков TODO
    private short bestResultOfCountShots;

    public PlayerImpl() {

    }

    public PlayerImpl(String name) {
        this.name = name;
    }

    //-------------------- Getters/Setters ------------------------//

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public short getBestResultOfStep() {
        return bestResultOfStep;
    }


    @Override
    public short getBestResultOfCountShots() {
        return bestResultOfCountShots;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBestResultOfStep(short bestResultOfStep) {
        this.bestResultOfStep = bestResultOfStep;
    }

    @Override
    public void setBestResultOfCountShots(short bestResultOfCountShots) {
        this.bestResultOfCountShots = bestResultOfCountShots;
    }
}
